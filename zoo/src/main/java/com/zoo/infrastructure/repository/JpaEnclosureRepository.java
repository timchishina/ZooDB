package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.EnclosureRepository;
import com.zoo.domain.model.Enclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaEnclosureRepository extends EnclosureRepository, JpaRepository<Enclosure, UUID> {

    List<Enclosure> findByType(String type);
}
