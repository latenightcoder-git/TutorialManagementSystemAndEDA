package com.example.config;

import com.example.entity.Tutorial;
import com.example.repository.TutorialRepository;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final TutorialRepository tutorialRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            Tutorial t = new Tutorial(
                    faker.book().title(),
                    faker.lorem().sentence(),
                    faker.bool().bool(),
                    faker.name().fullName()
            );

            tutorialRepository.save(t);
        }

    }
}
