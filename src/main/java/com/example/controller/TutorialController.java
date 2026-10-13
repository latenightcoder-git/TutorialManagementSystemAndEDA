package com.example.controller;

import com.example.dto.TutorialDto;
import com.example.service.TutorialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class TutorialController {
    private final TutorialService tutorialService;

    // 1. Save a Tutorial
    // Creates a new tutorial and returns the saved tutorial with HTTP 201 CREATED status.
    @PostMapping
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody TutorialDto tutorialDto) {
        return new ResponseEntity<>(tutorialService.saveTutorial(tutorialDto), HttpStatus.CREATED);
    }

    // 2. Get all Tutorials
    // This method retrieves all tutorials and creates an HTTP 200 OK response containing those tutorials.
    @GetMapping("/all")
    public ResponseEntity<List<TutorialDto>> getAllTutorials() {
        return ResponseEntity.ok(tutorialService.getAllTutorials());
    }

    // 3. Get a tutorial by ID
    // This method retrieves a tutorial by its ID. If the tutorial exists, it returns HTTP 200 OK with the tutorial data. Otherwise, it returns HTTP 404 Not Found.
    @GetMapping("/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable Long id) {
        return tutorialService.getTutorialById(id)
//                .map(tutorial -> ResponseEntity.ok(tutorial))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4. Update an existing Tutorial
    // If the tutorial exists, it gets updated, it returns HTTP 200 OK with the tutorial data. Otherwise, it returns HTTP 404 Not Found.
    @PutMapping("/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(@PathVariable Long id, @RequestBody TutorialDto tutorialDto){
        return tutorialService.updateTutorial(id, tutorialDto)
//                .map(tutorial -> ResponseEntity.ok(tutorial))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // 5. Delete an existing Tutorial
    // Delete a tutorial with particular id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTutorial(@PathVariable Long id){
        return new ResponseEntity<>(tutorialService.deleteTutorial(id),HttpStatus.OK);
    }

    // 6. Search tutorials by title
    @GetMapping("/title")
    public ResponseEntity<List<TutorialDto>> getByTitle(@RequestParam String title){
        return ResponseEntity.ok(tutorialService.searchByTitle(title));
    }


    // 7. Search tutorials by title containing particular words
    @GetMapping("/titlewithwords")
    public ResponseEntity<List<TutorialDto>> getByTitleContainingWords(@RequestParam String word){
        return new ResponseEntity<>(tutorialService.findByTitleContainingIgnoreCase(word),HttpStatus.OK);
    }

    // 8. Search tutorials by publishing status
    @GetMapping("/published")
    public ResponseEntity<List<TutorialDto>> getByPublished(@RequestParam Boolean published){
        return ResponseEntity.ok(tutorialService.searchByPublished(published));
    }

    // 9. Search tutorials by creator
    @GetMapping("/creator")
    public ResponseEntity<List<TutorialDto>> getByCreator(@RequestParam String creator){
        return ResponseEntity.ok(tutorialService.searchByCreator(creator));
    }
}


