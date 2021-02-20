package com.erdaldalkiran.cdcdemoconsumer;


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

    private Long id;

    private Long prevCount;

    private Long newCount;

    private Long updatedAt;

    private Long version;
}