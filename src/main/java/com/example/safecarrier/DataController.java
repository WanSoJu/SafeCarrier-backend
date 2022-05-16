package com.example.safecarrier;

import com.example.safecarrier.domain.EncryptedData;
import com.example.safecarrier.dto.UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<byte[]> getDataByLink(@PathVariable String lid){
        byte[] encryptedData = dataService.getDataByLid(lid);
        if(encryptedData==null){
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT); //존재하지 않는 링크에 대한 요청 or 권한이 마감된 요청, 204
        }
        return new ResponseEntity<>(encryptedData,HttpStatus.OK); //200
    }

    //복호화 성공을 알리는 API
}
