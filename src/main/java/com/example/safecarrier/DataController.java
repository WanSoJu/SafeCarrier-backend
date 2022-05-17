package com.example.safecarrier;

import com.example.safecarrier.dto.AllResponse;
import com.example.safecarrier.dto.DetailResponse;
import com.example.safecarrier.dto.UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data")
public class DataController {
    private final DataService dataService;

    @PostMapping("")
    public ResponseEntity<Long> uploadEncryptedData(@RequestBody UploadDto uploadDto){
        Long linkId = dataService.saveUploadedData(uploadDto);
        if(linkId==null){
            return new ResponseEntity<>(linkId, HttpStatus.BAD_REQUEST); //400
        }
        return new ResponseEntity<>(linkId, HttpStatus.OK); //200
    }

    @GetMapping("/{lid}")
    public ResponseEntity<DetailResponse> getDataByLink(@PathVariable String lid){
        DetailResponse encryptedData = dataService.getDataByLid(lid);
        if(encryptedData==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); //존재하지 않는 링크에 대한 요청 or 권한이 마감된 요청, 204
        }
        return new ResponseEntity<>(encryptedData,HttpStatus.OK); //200
    }

    //복호화 성공을 알리는 API
    @GetMapping("/read/{lid}")
    public ResponseEntity<Integer> getLeftReadCount(@PathVariable String lid){
        Integer leftCount = dataService.checkReadCountByLid(lid);
        return new ResponseEntity<>(leftCount,HttpStatus.OK); //남은 조회 수 반환 (0이면 이번 조회 이후로 더이상 조회 불가 -> 데이터베이스에서 삭제함)
    }

    @GetMapping("")
    public ResponseEntity<List<AllResponse>> getAllSendData(@RequestParam String id){
        return new ResponseEntity<>(dataService.getAllDataResponses(id),HttpStatus.OK);
    }
}
