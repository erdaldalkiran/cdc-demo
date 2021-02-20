package com.erdaldalkiran.cdcdemoconsumer;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CounterEvent {
    private final static Gson gsonDeserializer = new Gson();

    private Long id;

    private Long prevCount;

    private Long newCount;

    private Long updatedAt;

    private Long version;

    public CounterEvent(String payload) {
        var event = gsonDeserializer.fromJson(payload, CounterEvent.class);
        this.id = event.id;
        this.newCount = event.newCount;
        this.prevCount = event.prevCount;
        this.updatedAt = event.updatedAt;
        this.version = event.version;
    }
}