package com.erdaldalkiran.cdcdemo.service;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import com.erdaldalkiran.cdcdemo.domain.CounterEvent;
import com.erdaldalkiran.cdcdemo.domain.OutboxEvent;
import com.erdaldalkiran.cdcdemo.repository.CounterRepository;
import com.erdaldalkiran.cdcdemo.repository.OutboxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
@RequiredArgsConstructor
public class CounterService {
    private final CounterRepository repository;
    private final OutboxEventRepository eventRepository;
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

        var event = new CounterEvent(
            counter.getId(),
            counter.getCount() - 1,
            counter.getCount(),
            counter.getUpdatedAt(),
            counter.getVersion());

        var data = mapper.writeValueAsBytes(event);
        var outboxEvent = new OutboxEvent(event, data);

        eventRepository.save(outboxEvent);
        //eventRepository.delete(outboxEvent);
    }
}
