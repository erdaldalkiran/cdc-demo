#!/bin/bash
curl --location --request POST 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "random-event-connector",
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "database.hostname": "db",
        "database.port": "5432",
        "database.user": "postgres",
        "database.password": "pg",
        "database.dbname": "counter",
        "database.server.name": "random",
        "table.include.list": "public.random_event",
        "plugin.name": "pgoutput",
        "publication.name": "deb_connect_random_pub",
        "slot.name": "deb_connect_random_slot",
        "slot.max.retries": 100,
        "slot.retry.delay.ms": "5000",
        "snapshot.mode": "never",
        "tombstones.on.delete": false,
        "heartbeat.interval.ms": 5000,
        "heartbeat.topics.prefix": "__debezium-heartbeat",
        "key.converter": "io.debezium.converters.ByteBufferConverter",
        "key.converter.delegate.converter.type": "org.apache.kafka.connect.json.JsonConverter",
        "key.converter.delegate.converter.type.schemas.enable": "false",
        "value.converter": "io.debezium.converters.ByteBufferConverter",
        "value.converter.delegate.converter.type": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.delegate.converter.type.schemas.enable": "false",
        "transforms": "outbox",
        "transforms.outbox.type": "io.debezium.transforms.outbox.EventRouter",
        "transforms.outbox.route.topic.replacement": "ciko.lata.any.greek.god.name.random-events.0",
        "transforms.outbox.topic.regex": "ciko.lata.any.greek.god.name.random-events.0",
        "transforms.outbox.table.fields.additional.placement": "correlation_id:header:X-CorrelationId,id:header:event-id"
    }
}'
