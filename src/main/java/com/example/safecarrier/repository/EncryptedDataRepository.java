package com.example.safecarrier.repository;

import com.example.safecarrier.domain.EncryptedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptedDataRepository extends JpaRepository<EncryptedData, Long> {
}
