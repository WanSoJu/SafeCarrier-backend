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


}
