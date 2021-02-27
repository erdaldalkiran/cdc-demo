package com.erdaldalkiran.cdcdemo.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class CounterEvent {

    private Long id;

    private Long prevCount;

    private Long newCount;

    private Long updatedAt;

    private Long version;

    private UUID eventId;

    public CounterEvent(Long id, Long prevCount, Long newCount, Long updatedAt, Long version, UUID eventId) {
        this.id = id;
        this.prevCount = prevCount;
        this.newCount = newCount;
        this.updatedAt = updatedAt;
        this.version = version;
        this.eventId = eventId;
    }
}
