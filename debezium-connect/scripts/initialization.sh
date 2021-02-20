#!/bin/bash
x=1
while [ $x -le 3 ]
do
  curl -X POST "http://localhost:8080/api/counter" -H  "accept: */*" -H  "Content-Type: application/json" -d "{\"id\":$x,\"initialCount\":0}"
  echo ""
  x=$(( $x + 1 ))
done