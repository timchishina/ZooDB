package com.zoo.application.port.out;

import com.zoo.domain.model.Enclosure;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

public interface EnclosureRepository {
    Optional<Enclosure> findById(UUID id);
    void save(Enclosure enclosure);
    void deleteById(UUID id);
    List<Enclosure> findAll();
}
