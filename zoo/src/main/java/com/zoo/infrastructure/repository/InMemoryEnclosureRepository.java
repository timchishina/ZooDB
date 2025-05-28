package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.domain.model.Enclosure;

import java.util.*;

public class InMemoryEnclosureRepository implements EnclosureRepository {

    private final Map<UUID, Enclosure> storage = new HashMap<>();

    @Override
    public void save(Enclosure enclosure) {
        storage.put(enclosure.getId(), enclosure);
    }

    @Override
    public Optional<Enclosure> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }

    @Override
    public List<Enclosure> findAll() {
        return new ArrayList<>(storage.values());
    }
}
