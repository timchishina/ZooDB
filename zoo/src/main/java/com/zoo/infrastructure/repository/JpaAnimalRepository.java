package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.AnimalRepository;
import com.zoo.domain.model.Animal;
import com.zoo.domain.valueobject.AnimalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAnimalRepository extends AnimalRepository, JpaRepository<Animal, UUID> {

    List<Animal> findByType(AnimalType type);
    List<Animal> findByHungryTrue();
    Optional<Animal> findByName(String name);
}
