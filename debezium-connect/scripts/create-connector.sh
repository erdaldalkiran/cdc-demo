#!/bin/bash
curl --location --request POST 'http://localhost:8083/connectors' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "counter-event-connector",
    "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "database.hostname": "db",
        "plugin.name": "pgoutput",
        "database.port": "5432",
        "database.user": "postgres",
        "database.password": "pg",
        "database.dbname": "counter",
        "database.server.name": "counter",
        "table.include.list": "public.outbox_event",
        "slot.name": "deb_connect_slot",
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
        "transforms.outbox.route.topic.replacement": "ciko.lata.any.greek.god.name.${routedByValue}"
    }
}'
