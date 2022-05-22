package com.example.safecarrier.dto;

import com.example.safecarrier.domain.Link;
import com.example.safecarrier.domain.ReadCount;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class AllResponse {
    private Integer leftCount;
    private Long linkId;
    private String lid;
    private String fileName;

    public AllResponse(Integer leftCount, Long linkId, String lid, String fileName) {
        this.leftCount = leftCount;
        this.linkId = linkId;
        this.lid = lid;
        this.fileName = fileName;
    }

    public static AllResponse generateResponse(Link link){
        ReadCount readCount = link.getReadCount();
        return AllResponse.builder()
                .leftCount(readCount.getReadLimit()-readCount.getReadCount())
                .linkId(link.getId())
                .lid(link.getLid())
                .fileName(link.getData().getFileName())
                .build();

    }
}
