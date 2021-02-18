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
        "table.include.list": "public.counter_event",
        "column.exclude.list": "public.counter_event.id",
        "message.key.columns": "public.counter_event:counter_id",
        "tombstones.on.delete": false,
        "heartbeat.interval.ms": 5000,
        "heartbeat.topics.prefix": "__debezium-heartbeat",
        "transforms": "filter, unwrap",
        "transforms.filter.type": "io.debezium.transforms.Filter",
        "transforms.filter.language": "jsr223.groovy",
        "transforms.filter.condition": "value.op == '\''c'\''",
        "transforms.filter.topic.regex": "counter.public.counter_event",
        "transforms.unwrap.type": "io.debezium.transforms.ExtractNewRecordState",
        "transforms.unwrap.topic.regex": "counter.public.counter_event"
    }
}'
