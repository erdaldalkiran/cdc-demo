#!/bin/bash
x=1
while [ $x -le 1000 ]
do
  curl -X PATCH "http://localhost:8080/api/counter/1/increase"
  echo "."
  x=$(( $x + 1 ))
done