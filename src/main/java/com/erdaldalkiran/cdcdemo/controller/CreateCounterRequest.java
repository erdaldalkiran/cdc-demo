package com.erdaldalkiran.cdcdemo.controller;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.Instant;

@Data
public class CreateCounterRequest {
    @Positive
    private long id;

    @Min(value = 0)
    private long initialCount;

    public Counter toCounter() {
        return new Counter(id, initialCount, Instant.now().toEpochMilli(), 1L);
    }
}
