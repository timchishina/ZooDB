package com.zoo.application.port.out;

import com.zoo.domain.model.Enclosure;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnclosureRepository {
    Enclosure save(Enclosure enclosure);
    Optional<Enclosure> findById(UUID id);
    void deleteById(UUID id);
    List<Enclosure> findAll();
    List<Enclosure> findByType(String type);
}
