package com.example.safecarrier.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UploadDto {
    private byte[] encryptedData;
    private String dataType;
    private Integer readLimit;
    private String appLink;
    private String lid;
    private String fileName;


    public UploadDto(byte[] encryptedData, String dataType, Integer readLimit, String appLink, String lid, String fileName) {
        this.encryptedData = encryptedData;
        this.dataType = dataType;
        this.readLimit = readLimit;
        this.appLink = appLink;
        this.lid = lid;
        this.fileName = fileName;
    }
}
