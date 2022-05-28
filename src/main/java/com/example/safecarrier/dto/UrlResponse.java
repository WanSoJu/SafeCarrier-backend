package com.example.safecarrier.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlResponse {
    private String url;
    public UrlResponse(String url){
        this.url=url;
    }
}
