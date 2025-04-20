package com.zoo.domain.model;

import com.zoo.domain.valueobject.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Animal {
    private final UUID id;
    private final String name;
    private final AnimalType type;
    private final LocalDate birthDate;
    private final Gender gender;
    private final String favoriteFood;
    private HealthStatus healthStatus;
    private boolean hungry = true;
    private LocalDateTime lastFedTime;

    public Animal(UUID id, String name, AnimalType type, LocalDate birthDate, Gender gender, String favoriteFood) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.gender = gender;
        this.favoriteFood = favoriteFood;
        this.healthStatus = HealthStatus.HEALTHY;
        this.hungry = true;
        this.lastFedTime = null;
    }

    public void feed(String food) {
        if (!food.equalsIgnoreCase(favoriteFood)) {
            System.out.println(name + " не ест " + food);
            return;
        }
    
        if (!hungry) {
            System.out.println(name + " не голоден.");
            return;
        }
    
        hungry = false;
        lastFedTime = LocalDateTime.now();
        System.out.println(name + " покушал " + food + " в " + lastFedTime);
    }
    
    public void setHungry() {
        this.hungry = true;
    }
    public boolean isHungry() {
        return hungry;
    }

    public void heal() {
        this.healthStatus = HealthStatus.HEALTHY;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public UUID getId() {
        return id;
    }

    public AnimalType getType() {
        return type;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public String getName() {
        return name;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender(){
        return gender;
    }
}

