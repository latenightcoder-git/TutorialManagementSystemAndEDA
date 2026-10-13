package com.example.service;

import com.example.dto.TutorialDto;
import java.util.List;
import java.util.Optional;

public interface TutorialService {
    TutorialDto saveTutorial(TutorialDto tutorialDto);
    List<TutorialDto> getAllTutorials();
    Optional<TutorialDto> getTutorialById(Long id);
    Optional<TutorialDto> updateTutorial(Long id, TutorialDto tutorialDto);
    String deleteTutorial(Long id);
    // Custom Methods.
    List<TutorialDto> searchByTitle(String title);
    // This generates a SQL query like: WHERE LOWER(title) LIKE LOWER('%keyword%')
    List<TutorialDto> findByTitleContainingIgnoreCase(String title);
    List<TutorialDto> searchByPublished(Boolean published);
    List<TutorialDto> searchByCreator(String creator);
}
