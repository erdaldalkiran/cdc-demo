package com.erdaldalkiran.cdcdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "counter_event")
public class CounterEvent {

    @Id
    @SequenceGenerator(name = "counter_event_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "counter_event_id_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "prev_count")
    private Long prevCount;

    @Column(name = "new_count")
    private Long newCount;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "version")
    private Long version;

    public CounterEvent(Long prevCount, Long newCount, Long updatedAt, Long version) {
        this.prevCount = prevCount;
        this.newCount = newCount;
        this.updatedAt = updatedAt;
        this.version = version;
    }
}
