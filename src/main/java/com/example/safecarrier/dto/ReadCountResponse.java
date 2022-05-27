package com.example.safecarrier.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadCountResponse {
    private Integer leftReadCount;
    private String videoUrl;

    @Builder
    public ReadCountResponse(Integer count, String url){
        this.leftReadCount=count;
        this.videoUrl=url;
    }
}
