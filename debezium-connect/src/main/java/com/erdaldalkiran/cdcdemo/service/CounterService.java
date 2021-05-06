package com.erdaldalkiran.cdcdemo.service;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import com.erdaldalkiran.cdcdemo.domain.CounterEvent;
import com.erdaldalkiran.cdcdemo.domain.OutboxEvent;
import com.erdaldalkiran.cdcdemo.domain.RandomEvent;
import com.erdaldalkiran.cdcdemo.domain.RandomOutboxEvent;
import com.erdaldalkiran.cdcdemo.repository.CounterRepository;
import com.erdaldalkiran.cdcdemo.repository.OutboxEventRepository;
import com.erdaldalkiran.cdcdemo.repository.RandomOutboxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.erdaldalkiran.cdcdemo.controller.WebInterceptor.CORRELATION_ID;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class CounterService {
    private final CounterRepository repository;
    private final OutboxEventRepository eventRepository;
    private final RandomOutboxEventRepository randomEventRepository;
    private final static ObjectMapper mapper = new ObjectMapper();

    public Counter create(Counter counter) {
        return repository.save(counter);
    }

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 10), maxAttempts = 10)
    public void increase(long id) throws JsonProcessingException {
        var counterMaybe = repository.findById(id);

        if (counterMaybe.isEmpty()) {
            return;
        }

        var counter = counterMaybe.get();
        counter.increase();
        repository.save(counter);

        var eventId = UUID.randomUUID();
        var event = new CounterEvent(
            counter.getId(),
            counter.getCount() - 1,
            counter.getCount(),
            counter.getUpdatedAt(),
            counter.getVersion(),
            eventId);

        var correlationId = MDC.get(CORRELATION_ID);
        var data = mapper.writeValueAsBytes(event);
        var outboxEvent = new OutboxEvent(eventId, event, data, correlationId);
        eventRepository.save(outboxEvent);

        var randomEvent = new RandomEvent(
            counter.getId(),
            counter.getCount().toString(),
            counter.getUpdatedAt(),
            counter.getVersion(),
            UUID.randomUUID());

        var randomOutboxEvent = new RandomOutboxEvent(eventId, randomEvent, mapper.writeValueAsBytes(randomEvent), correlationId);
        randomEventRepository.save(randomOutboxEvent);
    }
}
