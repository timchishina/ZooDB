package com.zoo.infrastructure.repository;

import com.zoo.application.port.out.FeedingScheduleRepository;
import com.zoo.domain.model.FeedingSchedule;

import java.util.*;

public class InMemoryFeedingScheduleRepository implements FeedingScheduleRepository {

    private final Map<UUID, FeedingSchedule> storage = new HashMap<>();

    @Override
    public void save(FeedingSchedule schedule) {
        storage.put(schedule.getId(), schedule);
    }

    @Override
    public Optional<FeedingSchedule> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<FeedingSchedule> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
