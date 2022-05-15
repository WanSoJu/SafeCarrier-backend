package com.example.safecarrier.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String link;

    @Column
    @CreatedDate
    private LocalDateTime createdTime;

    @OneToOne
    @JoinColumn(name = "data_id")
    private EncryptedData data;

    @OneToOne
    @JoinColumn(name = "read_id")
    private ReadCount readCount;


}
