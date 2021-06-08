FROM debezium/connect:1.4.1.Final

USER root
RUN yum -y install https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm 
RUN yum -y install jq
RUN yum -y install curl
RUN yum -y install net-tools

USER kafka

RUN curl https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.15.0/jmx_prometheus_javaagent-0.15.0.jar > /kafka/jmx_prometheus_javaagent.jar
COPY log4j.properties $KAFKA_HOME/config/log4j.properties
COPY jmx.yml $KAFKA_HOME/config/jmx.yml
COPY health-check.sh $KAFKA_HOME/config/health-check.sh

USER root
RUN chmod +x $KAFKA_HOME/config/health-check.sh

USER kafka