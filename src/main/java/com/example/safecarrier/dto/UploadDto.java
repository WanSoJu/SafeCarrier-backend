package com.example.safecarrier.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadDto {
    private byte[] encryptedData;
    private String dataType;
    private Integer readLimit;
    private String appLink;
    private String lid;
    private String fileName;
}
