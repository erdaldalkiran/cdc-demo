#!/bin/bash
bombardier -c 20 -n 1000 -m PATCH http://localhost:8080/api/counter/1/increase