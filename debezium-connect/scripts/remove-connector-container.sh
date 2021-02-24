#!/bin/bash
docker ps | grep connect | awk '{print $1}' | xargs -I % bash -c 'docker container rm -f %'