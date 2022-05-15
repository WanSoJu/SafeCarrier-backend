package com.example.safecarrier.domain;

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

    @Column
    private Blob imageData;

    @Column
    private Blob videoData;

    @Column(columnDefinition = "TEXT")
    private String textData;

    @Column
    @CreatedDate
    private LocalDateTime createdTime;

}
