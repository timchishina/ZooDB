package com.zoo.presentation.controller;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.domain.model.Animal;
import com.zoo.domain.model.Enclosure;
import com.zoo.presentation.TransferRequest;
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

    @PostMapping("/{id}/clean")
    public boolean cleanEnclosure(@PathVariable UUID id) {
        Optional<Enclosure> opt = enclosureRepository.findById(id);
        if (opt.isPresent()) {
            Enclosure enclosure = opt.get();
            enclosure.clean();
            enclosureRepository.save(enclosure);
            return true;
        }
        return false;
    }

    @PostMapping("/transfer")
    public boolean transferAnimal(@RequestBody TransferRequest request) {
        Optional<Enclosure> from = enclosureRepository.findById(request.getFromEnclosureId());
        Optional<Enclosure> to = enclosureRepository.findById(request.getToEnclosureId());
        Optional<Animal> animal = animalRepository.findById(request.getAnimalId());

        if (from.isPresent() && to.isPresent() && animal.isPresent()) {
            boolean removed = from.get().removeAnimal(animal.get());
            boolean added = to.get().addAnimal(animal.get());
            if (removed && added) {
                enclosureRepository.save(from.get());
                enclosureRepository.save(to.get());
                return true;
            }
        }
        return false;
    }

}
