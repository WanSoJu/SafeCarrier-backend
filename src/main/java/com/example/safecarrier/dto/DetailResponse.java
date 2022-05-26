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
    private String dataType;

    @Builder
    public DetailResponse(String name, byte[] data,String dataType){
        this.fileName=name;
        this.encryptedData=data;
        this.dataType=dataType;
    }
}
