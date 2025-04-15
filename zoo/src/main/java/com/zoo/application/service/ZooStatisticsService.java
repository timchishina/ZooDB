package com.zoo.application.service;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.domain.model.Animal;
import com.zoo.domain.model.Enclosure;
import com.zoo.domain.valueobject.AnimalType;
import com.zoo.domain.valueobject.HealthStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZooStatisticsService {

    private final AnimalRepository animalRepository;
    private final EnclosureRepository enclosureRepository;

    public ZooStatisticsService(AnimalRepository animalRepository, EnclosureRepository enclosureRepository) {
        this.animalRepository = animalRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public Map<String, Object> getZooStatistics() {
        Map<String, Object> stats = new HashMap<>();

        List<Animal> animals = animalRepository.findAll();
        List<Enclosure> enclosures = enclosureRepository.findAll();

        int totalAnimals = animals.size();
        int totalEnclosures = enclosures.size();
        int freeEnclosures = (int) enclosures.stream().filter(e -> !e.isFull()).count();

        long sickAnimals = animals.stream()
                .filter(animal -> animal.getHealthStatus() == HealthStatus.SICK)
                .count();

        long hungryAnimals = animals.stream()
                .filter(Animal::isHungry)
                .count();

        Map<AnimalType, Long> byType = new HashMap<>();
        for (AnimalType type : AnimalType.values()) {
            long count = animals.stream()
                    .filter(animal -> animal.getType() == type)
                    .count();
            byType.put(type, count);
        }

        stats.put("totalAnimals", totalAnimals);
        stats.put("totalEnclosures", totalEnclosures);
        stats.put("freeEnclosures", freeEnclosures);
        stats.put("sickAnimals", sickAnimals);
        stats.put("hungryAnimals", hungryAnimals);
        stats.put("animalsByType", byType);

        return stats;
    }
}
