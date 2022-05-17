package com.example.safecarrier.dto;

import com.example.safecarrier.domain.EncryptedData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DetailResponse {
    private String fileName;
    private byte[] encryptedData;

    @Builder
    public DetailResponse(String name, byte[] data){
        this.fileName=name;
        this.encryptedData=data;
    }
}
