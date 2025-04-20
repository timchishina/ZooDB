package domain.model;

import com.zoo.domain.valueobject.*;
import com.zoo.domain.model.Animal;
import com.zoo.domain.model.Enclosure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EnclosureTest {

    private Enclosure enclosure;
    private Animal lion;
    private Animal elephant;

    @BeforeEach
    void setUp() {
        enclosure = new Enclosure(
                UUID.randomUUID(),
                EnclosureType.CARNIVORE,
                2, 
                100.0 
        );

        lion = new Animal(
                UUID.randomUUID(),
                "Лев",
                AnimalType.CARNIVORE,
                LocalDate.of(2017, 5, 1),
                Gender.MALE,
                "мясо"
        );

        elephant = new Animal(
                UUID.randomUUID(),
                "Слон",
                AnimalType.HERBIVORE,
                LocalDate.of(2015, 3, 12),
                Gender.FEMALE,
                "бананы"
        );
    }

    @Test
    void addCompatibleAnimal() {
        boolean added = enclosure.addAnimal(lion);
        assertTrue(added);
        assertEquals(1, enclosure.getCurrentCount());
    }

    @Test
    void addIncompatibleAnimal() {
        boolean added = enclosure.addAnimal(elephant);
        assertFalse(added);
        assertEquals(0, enclosure.getCurrentCount());
    }

    @Test
    void addAnimalWhenFull() {
        enclosure.addAnimal(lion);
        enclosure.addAnimal(new Animal(
                UUID.randomUUID(),
                "Тигр",
                AnimalType.CARNIVORE,
                LocalDate.of(2016, 2, 10),
                Gender.MALE,
                "мясо"
        ));
        boolean added = enclosure.addAnimal(
                new Animal(UUID.randomUUID(), "Гепард", AnimalType.CARNIVORE,
                        LocalDate.of(2018, 6, 1), Gender.FEMALE, "мясо")
        );
        assertFalse(added);
        assertTrue(enclosure.isFull());
    }

    @Test
    void removeAnimal() {
        enclosure.addAnimal(lion);
        boolean removed = enclosure.removeAnimal(lion);
        assertTrue(removed);
        assertEquals(0, enclosure.getCurrentCount());
    }

    @Test
    void cleanUpdateLastCleanedTime() {
        assertNull(enclosure.getLastCleanedTime());
        enclosure.clean();
        assertNotNull(enclosure.getLastCleanedTime());
    }
}
