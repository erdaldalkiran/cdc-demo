package com.erdaldalkiran.cdcdemo.repository;

import com.erdaldalkiran.cdcdemo.domain.CounterEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterEventRepository extends JpaRepository<CounterEvent, Long> {
}
