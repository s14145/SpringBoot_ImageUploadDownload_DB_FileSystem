package com.image.repository;

import com.image.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("FileDataRepository")
public interface FileDataRepository extends JpaRepository<FileData, Long> {
    Optional<FileData> findByName(String fileName);
}
