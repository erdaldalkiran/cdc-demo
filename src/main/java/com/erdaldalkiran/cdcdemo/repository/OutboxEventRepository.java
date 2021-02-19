package com.erdaldalkiran.cdcdemo.repository;

import com.erdaldalkiran.cdcdemo.domain.CounterEvent;
import com.erdaldalkiran.cdcdemo.domain.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutboxEventRepository extends JpaRepository<OutboxEvent, UUID> {
}
