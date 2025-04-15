package com.zoo.application.port.in;

import com.zoo.domain.model.Animal;

import java.util.List;
import java.util.UUID;

public interface ManageAnimalUseCase {

    void addAnimal(Animal animal);

    void deleteAnimal(UUID animalId);

    List<Animal> listAnimals();
}
