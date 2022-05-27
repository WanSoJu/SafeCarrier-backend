package com.example.safecarrier;

import com.example.safecarrier.domain.EncryptedData;
import com.example.safecarrier.domain.Link;
import com.example.safecarrier.domain.ReadCount;
import com.example.safecarrier.dto.AllResponse;
import com.example.safecarrier.dto.DetailResponse;
import com.example.safecarrier.dto.UploadDto;
import com.example.safecarrier.repository.EncryptedDataRepository;
import com.example.safecarrier.repository.LinkRepository;
import com.example.safecarrier.repository.ReadCountRepository;
import com.example.safecarrier.s3Image.AwsS3Service;
import com.example.safecarrier.s3Image.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DataService {
    private final EncryptedDataRepository dataRepository;
    private final LinkRepository linkRepository;
    private final ReadCountRepository readRepository;
    private final AwsS3Service awsS3Service;

    public static HashMap<String,String> urls=new HashMap<>();

    public Long saveUploadedData(UploadDto uploadDto){
        ReadCount readCount = ReadCount.builder()
                .limit(uploadDto.getReadLimit()).build();

        EncryptedData encryptedData;
        String encrypted= uploadDto.getEncryptedData();
        String dataType = uploadDto.getDataType();
        switch (dataType){
            case "IMAGE":
                encryptedData=EncryptedData.builder().image(encrypted).dtype(1).fileName(uploadDto.getFileName()).build();
                break;
            case "TEXT":
                encryptedData=EncryptedData.builder().text(encrypted).dtype(3).fileName(uploadDto.getFileName()).build();
                break;
            case "VIDEO":
                String name=uploadDto.getFileName();
                String url = urls.get(name);
                urls.remove(name);
                encryptedData=EncryptedData.builder().video(encrypted).dtype(2).fileName(uploadDto.getFileName()).url(url).build();
                break;
            default:
                encryptedData=null;
        }

        if(encryptedData==null){
            return null;
        }

        Link link = Link.builder()
                .link(uploadDto.getAppLink()).lid(uploadDto.getLid())
                .data(encryptedData).readCount(readCount).build();

        readRepository.save(readCount);
        dataRepository.save(encryptedData);
        linkRepository.save(link);
        return link.getId();
    }

    public Link checkLidExistence(String lid){
        return linkRepository.findByLid(lid);
    }

    public DetailResponse getDataByLid(String lid){
        Link link = linkRepository.findByLid(lid);
        if(link==null)
            return null;
        EncryptedData data = link.getData();
        switch (data.getDtype()){
            case 1:
                return new DetailResponse(data.getFileName(),data.getImageData(),"IMAGE");
            case 2:
                return new DetailResponse(data.getFileName(),data.getVideoData(),"VIDEO");
            case 3:
                return new DetailResponse(data.getFileName(),data.getTextData(),"TEXT");
        }
        return null;
    }

    public String findVideoUrl(String lid){
        Link link = linkRepository.findByLid(lid);
        if(link==null)
            return null;
        EncryptedData data = link.getData();
        String videoUrl = data.getVideoUrl();
        if(videoUrl==null)
            return null;
        return videoUrl;
    }


    //readCount 체크하는 부분을 "복호화 성공 수신 API"로 넘겨야함
    //이번 조회 이후 잔여 횟수 반환
    public Integer checkReadCountByLid(String lid){
        Link link = linkRepository.findByLid(lid);
        ReadCount readCount = link.getReadCount();
        Integer readLimit = readCount.getReadLimit();
        Integer count = readCount.getReadCount();
        if(readLimit.equals(count+1)){ //이번에 조회하면 더 이상 조회 불가능한 경우, 바로 삭제
            deleteData(link, readCount);
            return 0;
        }
        return updateReadCount(readCount);
    }

    private void deleteData(Link link,ReadCount readCount){
        EncryptedData data = link.getData();
        linkRepository.delete(link);
        readRepository.delete(readCount);
        dataRepository.delete(data);
    }

    private int updateReadCount(ReadCount readCount){
        Integer count = readCount.updateReadCount();
        readRepository.save(readCount);
        return readCount.getReadLimit()-count; //(이번 조회 이후의) 잔여 조회횟수 반환
    }

    public List<AllResponse> getAllDataResponses(String ids){
        List<Link> links = Arrays.stream(ids.split(","))
                .map(id -> linkRepository.findById(Long.parseLong(id)).orElse(null))
                .collect(Collectors.toList());

        return links.stream()
                .filter(Objects::nonNull)
                .map(AllResponse::generateResponse)
                .collect(Collectors.toList());
    }

    public Link findLinkById(Long linkId){
        Optional<Link> link = linkRepository.findById(linkId);
        return link.orElse(null);
    }


    public String savePostImages(MultipartFile file){
        if(file==null){
            return null;
        }
        String name = file.getOriginalFilename();
        System.out.println("name = " + name);
        String url = awsS3Service.uploadFile(file);
        urls.put(name,url);
        System.out.println("url = " + url);
        return url;
    }
}
