package com.zoo.presentation.controller;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.domain.model.Animal;
import com.zoo.domain.model.Enclosure;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {

    private final EnclosureRepository enclosureRepository;
    private final AnimalRepository animalRepository;

    public EnclosureController(EnclosureRepository enclosureRepository, AnimalRepository animalRepository) {
        this.enclosureRepository = enclosureRepository;
        this.animalRepository = animalRepository;
    }

    @PostMapping
    public void addEnclosure(@RequestBody Enclosure enclosure) {
        enclosureRepository.save(enclosure);
    }

    @PostMapping("/{enclosureId}/add-animal")
    public boolean addAnimalToEnclosure(@PathVariable UUID enclosureId, @RequestBody UUID animalId) {
        Optional<Enclosure> enclosureOpt = enclosureRepository.findById(enclosureId);
        Optional<Animal> animalOpt = animalRepository.findById(animalId);

        if (enclosureOpt.isEmpty() || animalOpt.isEmpty()) {
            return false;
        }

        Enclosure enclosure = enclosureOpt.get();
        Animal animal = animalOpt.get();

        boolean added = enclosure.addAnimal(animal);

        if (added) {
            enclosureRepository.save(enclosure);
        }

        return added;
    }
    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable UUID id) {
        enclosureRepository.deleteById(id);
    }

    @GetMapping
    public List<Enclosure> listEnclosures() {
        return enclosureRepository.findAll();
    }
}
