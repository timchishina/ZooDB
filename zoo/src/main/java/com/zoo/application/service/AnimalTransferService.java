package com.zoo.application.service;

import com.zoo.domain.model.Animal;
import com.zoo.domain.model.Enclosure;
import com.zoo.domain.event.AnimalMovedEvent;
import com.zoo.application.port.out.AnimalRepository;
import com.zoo.application.port.out.EnclosureRepository;

import java.util.Optional;
import java.util.UUID;

public class AnimalTransferService {

    private final EnclosureRepository enclosureRepository;
    private final AnimalRepository animalRepository;

    public AnimalTransferService(EnclosureRepository enclosureRepository, AnimalRepository animalRepository) {
        this.enclosureRepository = enclosureRepository;
        this.animalRepository = animalRepository;
    }

    public Optional<AnimalMovedEvent> transferAnimal(UUID animalId, UUID fromEnclosureId, UUID toEnclosureId) {
        Optional<Enclosure> fromOpt = enclosureRepository.findById(fromEnclosureId);
        Optional<Enclosure> toOpt = enclosureRepository.findById(toEnclosureId);
        if (fromOpt.isEmpty() || toOpt.isEmpty()) return Optional.empty();

        Enclosure from = fromOpt.get();
        Enclosure to = toOpt.get();

        Animal animal = from.getAnimals().stream()
            .filter(a -> a.getId().equals(animalId))
            .findFirst()
            .orElse(null);

        if (animal == null) return Optional.empty();

        if (!to.getType().isCompatible(animal.getType())) return Optional.empty();
        if (to.isFull()) return Optional.empty();

        boolean removed = from.removeAnimal(animal);
        boolean added = to.addAnimal(animal);
        if (!removed || !added) return Optional.empty(); 

        AnimalMovedEvent event = new AnimalMovedEvent(animalId, fromEnclosureId, toEnclosureId);
        return Optional.of(event);
    }
}
