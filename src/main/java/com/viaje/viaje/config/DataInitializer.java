package com.viaje.viaje.config;

import com.viaje.viaje.model.Tags;
import com.viaje.viaje.repository.TagsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(TagsRepository tagsRepository) {
        return args -> {
            if (tagsRepository.count() == 0) {
                tagsRepository.save(new Tags(null, "추천"));
                tagsRepository.save(new Tags(null, "액티비티"));
                tagsRepository.save(new Tags(null, "맛집"));
                tagsRepository.save(new Tags(null, "국내"));
                tagsRepository.save(new Tags(null, "해외"));
                tagsRepository.save(new Tags(null, "휴식"));
            }
        };
    }
}