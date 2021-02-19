package com.erdaldalkiran.cdcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class CounterEvent {

    private Long id;

    private Long prevCount;

    private Long newCount;

    private Long updatedAt;

    private Long version;

    public CounterEvent(Long id, Long prevCount, Long newCount, Long updatedAt, Long version) {
        this.id = id;
        this.prevCount = prevCount;
        this.newCount = newCount;
        this.updatedAt = updatedAt;
        this.version = version;
    }
}
