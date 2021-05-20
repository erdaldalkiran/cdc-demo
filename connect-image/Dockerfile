FROM debezium/connect:1.4.1.Final
RUN curl https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.15.0/jmx_prometheus_javaagent-0.15.0.jar > /kafka/jmx_prometheus_javaagent.jar
COPY log4j.properties $KAFKA_HOME/config/log4j.properties
COPY jmx.yml /kafka/config/jmx.yml