#!/bin/sh
URL=$(netstat -anp | awk '{ print $4} ' | grep 8083 | tail -1)
CONNECTORS=$(curl -fs $URL/connectors)
CONNECTOR_COUNT=$(echo "$CONNECTORS" | jq length)
echo "CONNECTORS: ""$CONNECTORS"
echo "CONNECTOR_COUNT: ""$CONNECTOR_COUNT"
if [ "$CONNECTOR_COUNT" -lt "1" ]; then
  echo "not required"
  exit 0
fi
for CONNECTOR in $(echo "$CONNECTORS" | jq -c -r ".[]"); do
  STATUS=$(curl -fs "$URL/connectors/$CONNECTOR/status")
  echo "STATUS RESULT: ""$STATUS"
  MAIN_STATUS=$(echo "$STATUS" | jq -r .connector.state)
  echo "MAIN_STATUS: ""$MAIN_STATUS"
  [ "$MAIN_STATUS" = "FAILED" ] && exit 1

  echo "STATUS:""$STATUS"

  TASKS=$(echo "$STATUS" | jq -c ".tasks")
  echo "TASKS:""$TASKS"

  for TASK in $(echo "$TASKS" | jq -r ".[] | .state"); do
    echo "TASK:""$TASK"
    [ "$TASK" = "FAILED" ] && exit 1
  done
done
exit 0
