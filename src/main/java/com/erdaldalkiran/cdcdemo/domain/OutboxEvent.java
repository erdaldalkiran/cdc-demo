package com.erdaldalkiran.cdcdemo.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

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
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OutboxEvent {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "aggregatetype")
    private String aggregateType;

    @Column(name = "aggregateid")
    private long aggregateId;

    @Column(name = "type")
    private String type;

    @Type(type = "jsonb")
    @Column(name = "payload", columnDefinition = "jsonb")
    private CounterEvent payload;

    public OutboxEvent(CounterEvent event) {
        this.id = UUID.randomUUID();
        this.aggregateType = event.getClass().getSimpleName();
        this.type = event.getClass().getSimpleName();
        this.aggregateId = event.getId();
        this.payload = event;
    }
}
