package com.example.safecarrier.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class ReadCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "read_id")
    private Long id;

    @Column
    private Integer readLimit; //최대 조회 가능 횟수

    @Column
    private Integer readCount=0; //해당 링크의 데이터가 조회된 횟수

    @Builder
    public ReadCount(Integer limit){
        this.readLimit=limit;
    }


}
