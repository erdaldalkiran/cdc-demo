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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "outbox_event")
public class OutboxEvent {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "aggregatetype")
    private String aggregateType;

    @Column(name = "aggregateid")
    private String aggregateId;

    @Column(name = "type")
    private String type;

    @Column(name = "payload", columnDefinition = "bytea")
    private byte[] payload;

    @Column(name = "correlation_id")
    private String correlationId;

    @Column(name = "timestamp")
    private Long timestamp;

    public OutboxEvent(UUID id, CounterEvent event, byte[] payload, String correlationId) {
        this.id = id;
        this.aggregateType = event.getClass().getSimpleName();
        this.type = event.getClass().getSimpleName();
        this.aggregateId = event.getId().toString();
        this.payload = payload;
        this.correlationId = correlationId;
        this.timestamp = event.getUpdatedAt();
    }
}
