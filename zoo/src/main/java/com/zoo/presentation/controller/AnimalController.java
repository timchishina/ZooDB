package com.zoo.presentation.controller;

import com.zoo.application.port.in.ManageAnimalUseCase;
import com.zoo.domain.model.Animal;
import com.zoo.domain.valueobject.HealthStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

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

    @PatchMapping("/{id}/health")
    public boolean updateHealthStatus(@PathVariable UUID id, @RequestBody String healthStatus) {
        Optional<Animal> opt = animalService.listAnimals().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (opt.isPresent()) {
            Animal animal = opt.get();
            animal.setHealthStatus(HealthStatus.valueOf(healthStatus.toUpperCase()));
            animalService.addAnimal(animal); 
            return true;
        }
        return false;
    }

    @PostMapping("/{id}/feed")
    public boolean feedAnimal(@PathVariable UUID id, @RequestBody String food) {
        Optional<Animal> opt = animalService.listAnimals().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst();

        if (opt.isPresent()) {
            Animal animal = opt.get();
            animal.feed(food);
            animalService.addAnimal(animal);
            return true;
        }
        return false;
    }

}