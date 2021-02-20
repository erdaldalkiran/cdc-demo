#!/bin/bash
bombardier -c 20 -n 200 -m PATCH http://localhost:8080/api/counter/1/increase &
p1=$!
echo $p1
bombardier -c 20 -n 200 -m PATCH http://localhost:8080/api/counter/2/increase &
p2=$!
echo $p2
bombardier -c 20 -n 200 -m PATCH http://localhost:8080/api/counter/3/increase
p3=$!
echo $p3
wait $p1 $p2 $p3