#!/bin/bash
curl -s -X PUT -H "Content-Type:application/json" \
    http://localhost:8083/admin/loggers/org.apache.kafka.connect.runtime.TransformationChain \
    -d '{"level": "TRACE"}'

curl -s -X PUT -H "Content-Type:application/json" \
    http://localhost:8083/admin/loggers/io.debezium.converters \
    -d '{"level": "TRACE"}'

curl -s -X PUT -H "Content-Type:application/json" \
    http://localhost:8083/admin/loggers/io.debezium.transforms \
    -d '{"level": "TRACE"}'