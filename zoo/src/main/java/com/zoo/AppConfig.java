package com.zoo;

import com.zoo.application.port.in.ManageAnimalUseCase;
import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.application.port.out.FeedingScheduleRepository;
import com.zoo.application.service.FeedingOrganizationService;
import com.zoo.application.service.ManageAnimalService;
import com.zoo.application.service.ZooStatisticsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ManageAnimalUseCase manageAnimalUseCase(AnimalRepository animalRepository) {
        return new ManageAnimalService(animalRepository);
    }

    @Bean
    public FeedingOrganizationService feedingOrganizationService(FeedingScheduleRepository scheduleRepo, AnimalRepository animalRepo) {
        return new FeedingOrganizationService(scheduleRepo, animalRepo);
    }

    @Bean
    public ZooStatisticsService zooStatisticsService(AnimalRepository animalRepository, EnclosureRepository enclosureRepository) {
        return new ZooStatisticsService(animalRepository, enclosureRepository);
    }
}
