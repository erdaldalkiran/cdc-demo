package com.erdaldalkiran.cdcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "counter")
public class Counter {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "count")
    private Long count;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "version")
    private Long version;

    public void increase() {
        count++;
        version++;
        updatedAt = Instant.now().toEpochMilli();
    }
}
