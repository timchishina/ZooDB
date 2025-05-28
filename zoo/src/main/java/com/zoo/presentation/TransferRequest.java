package com.zoo.presentation;

import java.util.UUID;

public class TransferRequest {

    private UUID animalId;
    private UUID fromEnclosureId;
    private UUID toEnclosureId;

    public TransferRequest() {}

    public TransferRequest(UUID animalId, UUID fromEnclosureId, UUID toEnclosureId) {
        this.animalId = animalId;
        this.fromEnclosureId = fromEnclosureId;
        this.toEnclosureId = toEnclosureId;
    }

    public UUID getAnimalId() {
        return animalId;
    }

    public void setAnimalId(UUID animalId) {
        this.animalId = animalId;
    }

    public UUID getFromEnclosureId() {
        return fromEnclosureId;
    }

    public void setFromEnclosureId(UUID fromEnclosureId) {
        this.fromEnclosureId = fromEnclosureId;
    }

    public UUID getToEnclosureId() {
        return toEnclosureId;
    }

    public void setToEnclosureId(UUID toEnclosureId) {
        this.toEnclosureId = toEnclosureId;
    }
}
