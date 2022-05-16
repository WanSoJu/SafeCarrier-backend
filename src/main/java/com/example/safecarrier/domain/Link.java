package com.example.safecarrier.domain;

import lombok.Builder;
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

    @Column
    private String lid; //링크의 고유 식별자

    @OneToOne
    @JoinColumn(name = "data_id")
    private EncryptedData data;

    @OneToOne
    @JoinColumn(name = "read_id")
    private ReadCount readCount;

    @Builder
    public Link(String link, String lid, EncryptedData data, ReadCount readCount){
        this.link=link;
        this.lid=lid;
        this.data=data;
        this.readCount=readCount;
        this.createdTime=LocalDateTime.now();
    }


}
