package com.example.safecarrier.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class EncryptedData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "data_id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String imageData;

    @Column(columnDefinition = "LONGTEXT")
    private String videoData;

    @Column(columnDefinition = "LONGTEXT")
    private String textData;

    @Column
    private String videoUrl;

    @Column
    @CreatedDate
    private LocalDateTime createdTime;

    @Column
    private Integer dtype; //1: image 2: video 3: text

    @Column
    private String fileName;

    @Builder
    public EncryptedData(String image, String video, String text, Integer dtype,String fileName,String url){
        this.imageData=image;
        this.videoData=video;
        this.textData=text;
        this.dtype=dtype;
        this.createdTime=LocalDateTime.now();
        this.fileName=fileName;
        this.videoUrl=url;
    }

}
