package com.example.service.impl;

import com.example.dto.TutorialDto;
import com.example.entity.Tutorial;
import com.example.repository.TutorialRepository;
import com.example.service.TutorialService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TutorialServiceImpl implements TutorialService {

    private final ModelMapper modelMapper;
    private final TutorialRepository tutorialRepository;

    @Override
    public TutorialDto saveTutorial(TutorialDto tutorialDto) {
//        Tutorial tutorial = modelMapper.map(tutorialDto, Tutorial.class);
//        Tutorial savedTutorial = tutorialRepository.save(tutorial);
//        return modelMapper.map(savedTutorial, TutorialDto.class);
        tutorialDto.setId(null);
        return modelMapper.map(tutorialRepository.save(modelMapper.map(tutorialDto, Tutorial.class)),TutorialDto.class);
    }

    @Override
    public List<TutorialDto> getAllTutorials() {
        return tutorialRepository.findAll()
                .stream()
                .map(tutorial -> modelMapper.map(tutorial, TutorialDto.class))
                .toList();
    }

    @Override
    public Optional<TutorialDto> getTutorialById(Long id) {
        return tutorialRepository.findById(id).map(tutorial->modelMapper.map(tutorial, TutorialDto.class));
    }

    @Override
    public Optional<TutorialDto> updateTutorial(Long id, TutorialDto tutorialDto) {
        Optional<Tutorial> tutorialOptional = tutorialRepository.findById(id);

        if (tutorialOptional.isEmpty()) {
            return Optional.empty();
        }

        Tutorial tutorial = tutorialOptional.get();

        tutorial.setTitle(tutorialDto.getTitle());
        tutorial.setDescription(tutorialDto.getDescription());
        tutorial.setPublished(tutorialDto.getPublished());
        tutorial.setCreator(tutorialDto.getCreator());

        tutorialRepository.save(tutorial);

        return Optional.of(modelMapper.map(tutorial, TutorialDto.class));
    }

    @Override
    public String deleteTutorial(Long id) {
        Tutorial tutorial = tutorialRepository.findById(id).orElseThrow(()->new RuntimeException("Tutorial with id "+id+" not found!"));
        tutorialRepository.delete(tutorial);
        return "Tutorial with id "+id+" has been deleted successfully!!!";
    }

    @Override
    public List<TutorialDto> findByTitleContainingIgnoreCase(String title) {
        return tutorialRepository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(tutorial -> modelMapper.map(tutorial, TutorialDto.class))
                .toList();
    }

    @Override
    public List<TutorialDto> searchByTitle(String title) {
        return tutorialRepository.findByTitle(title)
                .stream()
                .map(tutorial -> modelMapper.map(tutorial, TutorialDto.class))
                .toList();
    }


    @Override
    public List<TutorialDto> searchByPublished(Boolean published) {
        return tutorialRepository.findByPublished(published)
                .stream()
                .map(tutorial -> modelMapper.map(tutorial, TutorialDto.class))
                .toList();
    }

    @Override
    public List<TutorialDto> searchByCreator(String creator) {
        return tutorialRepository.findByCreator(creator)
                .stream()
                .map(tutorial -> modelMapper.map(tutorial, TutorialDto.class))
                .toList();
    }

}
