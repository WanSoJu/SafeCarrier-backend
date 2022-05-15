package com.example.safecarrier.repository;

import com.example.safecarrier.domain.ReadCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadCountRepository extends JpaRepository<ReadCount, Long> {
}
