package domain.model;

import com.zoo.domain.valueobject.*;
import com.zoo.domain.model.Animal;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    Animal animal = new Animal(
            UUID.randomUUID(),
            "Лев",
            AnimalType.CARNIVORE,
            LocalDate.of(2015, 5, 1),
            Gender.MALE,
            "мясо"
    );

    @Test
    void animalIsHungry() {
        assertTrue(animal.isHungry());
    }

    @Test
    void feedingWithWrongFood() {
        animal.feed("трава");
        assertTrue(animal.isHungry());
    }

    @Test
    void feedingWithCorrectFood() {
        animal.feed("мясо");
        assertFalse(animal.isHungry());
    }

    @Test
    void animalHungryAgain() {
        animal.feed("мясо");
        animal.setHungry();
        assertTrue(animal.isHungry());
    }

    @Test
    void animalSickAndHealed() {
        animal.setHealthStatus(HealthStatus.SICK);
        assertEquals(HealthStatus.SICK, animal.getHealthStatus());
        animal.heal();
        assertEquals(HealthStatus.HEALTHY, animal.getHealthStatus());
    }
}
