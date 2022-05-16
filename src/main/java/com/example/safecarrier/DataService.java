package com.example.safecarrier;

import com.example.safecarrier.domain.EncryptedData;
import com.example.safecarrier.domain.Link;
import com.example.safecarrier.domain.ReadCount;
import com.example.safecarrier.dto.UploadDto;
import com.example.safecarrier.repository.EncryptedDataRepository;
import com.example.safecarrier.repository.LinkRepository;
import com.example.safecarrier.repository.ReadCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataService {
    private final EncryptedDataRepository dataRepository;
    private final LinkRepository linkRepository;
    private final ReadCountRepository readRepository;

    public Long saveUploadedData(UploadDto uploadDto){
        ReadCount readCount = ReadCount.builder()
                .limit(uploadDto.getReadLimit()).build();

        EncryptedData encryptedData;
        byte[] encrypted= uploadDto.getEncryptedData();
        String dataType = uploadDto.getDataType();
        switch (dataType){
            case "IMAGE":
                encryptedData=EncryptedData.builder().image(encrypted).dtype(1).build();
                break;
            case "TEXT":
                encryptedData=EncryptedData.builder().text(encrypted).dtype(3).build();
                break;
            case "VIDEO":
                encryptedData=EncryptedData.builder().video(encrypted).dtype(2).build();
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

}
