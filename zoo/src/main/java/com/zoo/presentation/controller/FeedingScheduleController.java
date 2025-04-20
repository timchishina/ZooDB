package com.zoo.presentation.controller;

import com.zoo.application.service.FeedingOrganizationService;
import com.zoo.application.port.out.FeedingScheduleRepository;
import com.zoo.domain.model.FeedingSchedule;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/feeding-schedule")
public class FeedingScheduleController {

    private final FeedingScheduleRepository feedingRepo;
    private final FeedingOrganizationService feedingService;

    public FeedingScheduleController(FeedingScheduleRepository feedingRepo, FeedingOrganizationService feedingService) {
        this.feedingRepo = feedingRepo;
        this.feedingService = feedingService;
    }

    @PostMapping
    public void addFeeding(@RequestBody Map<String, String> body) {
        UUID animalId = UUID.fromString(body.get("animalId"));
        LocalTime time = LocalTime.parse(body.get("feedingTime"));
        String foodType = body.get("foodType");

        feedingService.addFeeding(animalId, time, foodType);
    }

    @GetMapping
    public List<FeedingSchedule> listFeedingSchedules() {
        return feedingRepo.findAll();
    }

    @PostMapping("/{id}/complete")
    public boolean completeFeeding(@PathVariable UUID id) {
        return feedingService.markFeedingAsCompleted(id);
    }

    @PatchMapping("/{id}")
    public boolean updateFeeding(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        LocalTime newTime = LocalTime.parse(body.get("feedingTime"));
        String newFood = body.get("foodType");
        return feedingService.updateFeeding(id, newTime, newFood);
    }
}
