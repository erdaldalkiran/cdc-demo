package com.erdaldalkiran.cdcdemo.controller;

import com.erdaldalkiran.cdcdemo.domain.Counter;
import com.erdaldalkiran.cdcdemo.service.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/counter")
@RestController
@Validated
public class CounterController {

    private final CounterService service;

    @PostMapping("")
    public Counter create(@Valid @RequestBody CreateCounterRequest request) {
        var counter = request.toCounter();
        return service.create(counter);
    }

    @PatchMapping("/{id}/increase")
    public ResponseEntity increase(@PathVariable @Positive long id) {
        service.increase(id);
        return ResponseEntity.ok().build();
    }
}
