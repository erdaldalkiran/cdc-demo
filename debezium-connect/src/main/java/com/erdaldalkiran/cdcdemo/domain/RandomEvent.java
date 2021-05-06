package com.erdaldalkiran.cdcdemo.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class RandomEvent {

    private Long id;

    private String text;

    private Long updatedAt;

    private Long version;

    private UUID eventId;

    public RandomEvent(Long id, String text, Long updatedAt, Long version, UUID eventId) {
        this.id = id;
        this.text = text;
        this.updatedAt = updatedAt;
        this.version = version;
        this.eventId = eventId;
    }
}
