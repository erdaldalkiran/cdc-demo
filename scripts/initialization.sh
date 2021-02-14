#!/bin/bash
curl -X POST "http://localhost:8080/api/counter" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"id\":1,\"initialCount\":0}"
