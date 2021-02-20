# cdc-demo

## required tools
- bombardier  
  go get -u github.com/codesenberg/bombardier


## usage
- docker-compose up -d
- cd debezium-connect
- run project
- run ./scripts/initialization.sh to create initial data
- run ./scripts/create-connector.sh to create connector
- run ./scripts/bombardier.sh to create events
- check http://localhost:9000 to see topic and messages
- also you can run consumer to see messages arrived

## tools
- swagger ui:  
  http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
  
