package com.erdaldalkiran.cdcdemo.repository;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, Long> {
}
