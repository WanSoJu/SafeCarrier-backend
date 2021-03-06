package com.example.safecarrier.repository;

import com.example.safecarrier.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long> {
    Link findByLid(String lid);
}
