package com.erdaldalkiran.cdcdemo.repository;

import com.erdaldalkiran.cdcdemo.domain.OutboxEvent;
import com.erdaldalkiran.cdcdemo.domain.RandomOutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RandomOutboxEventRepository extends JpaRepository<RandomOutboxEvent, UUID> {
}
