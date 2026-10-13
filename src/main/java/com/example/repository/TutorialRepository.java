package com.example.repository;

import com.example.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    // Custom Query Methods
    List<Tutorial> findByTitle(String title);
    List<Tutorial> findByPublished(Boolean published);
    List<Tutorial> findByCreator(String creator);
    List<Tutorial> findByTitleContainingIgnoreCase(String title);
}
