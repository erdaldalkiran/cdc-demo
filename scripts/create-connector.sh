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
        "table.whitelist": "public.counter_event"
    }
}'