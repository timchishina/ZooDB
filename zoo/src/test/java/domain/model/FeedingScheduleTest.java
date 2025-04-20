package domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.zoo.domain.model.FeedingSchedule;

import java.time.LocalTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FeedingScheduleTest {

    private FeedingSchedule schedule;
    private final UUID animalId = UUID.randomUUID();
    private final LocalTime feedingTime = LocalTime.of(12, 30);
    private final String foodType = "рыба";

    @BeforeEach
    void setUp() {
        schedule = new FeedingSchedule(UUID.randomUUID(), animalId, feedingTime, foodType);
    }

    @Test
    void scheduleIsNotCompleted() {
        assertFalse(schedule.isCompleted());
    }

    @Test
    void markAsCompletedSetsCompleted() {
        schedule.markAsCompleted();
        assertTrue(schedule.isCompleted());
    }

    @Test
    void updateScheduleChanges() {
        LocalTime newTime = LocalTime.of(14, 0);
        String newFood = "мясо";

        schedule.updateSchedule(newTime, newFood);

        assertEquals(newTime, schedule.getFeedingTime());
        assertEquals(newFood, schedule.getFoodType());
    }

    @Test
    void gettersReturn() {
        assertEquals(animalId, schedule.getAnimalId());
        assertEquals(feedingTime, schedule.getFeedingTime());
        assertEquals(foodType, schedule.getFoodType());
        assertNotNull(schedule.getId());
    }
}
