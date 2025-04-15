package com.zoo.presentation.controller;

import com.zoo.application.port.in.ManageAnimalUseCase;
import com.zoo.domain.model.Animal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/animals")
public class AnimalController {

    private final ManageAnimalUseCase animalService;

    public AnimalController(ManageAnimalUseCase animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        animalService.addAnimal(animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable UUID id) {
        animalService.deleteAnimal(id);
    }

    @GetMapping
    public List<Animal> listAnimals() {
        return animalService.listAnimals();
    }
}
