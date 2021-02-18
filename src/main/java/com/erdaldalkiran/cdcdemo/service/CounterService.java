package com.erdaldalkiran.cdcdemo.service;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import com.erdaldalkiran.cdcdemo.domain.CounterEvent;
import com.erdaldalkiran.cdcdemo.repository.CounterEventRepository;
import com.erdaldalkiran.cdcdemo.repository.CounterRepository;
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
    private final CounterEventRepository eventRepository;

    public Counter create(Counter counter) {
        return repository.save(counter);
    }

    @Retryable(value = Exception.class, backoff = @Backoff(delay = 10), maxAttempts = 10)
    public void increase(long id) {
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

        eventRepository.save(event);
        eventRepository.delete(event);
    }
}
