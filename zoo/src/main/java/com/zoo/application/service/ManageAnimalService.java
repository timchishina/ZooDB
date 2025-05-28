package com.zoo.application.service;

import com.zoo.application.port.in.ManageAnimalUseCase;
import com.zoo.application.port.out.AnimalRepository;
import com.zoo.domain.model.Animal;

import java.util.List;
import java.util.UUID;

public class ManageAnimalService implements ManageAnimalUseCase {

    private final AnimalRepository animalRepository;

    public ManageAnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(UUID animalId) {
        animalRepository.deleteById(animalId);
    }

    @Override
    public List<Animal> listAnimals() {
        return animalRepository.findAll();
    }
}
