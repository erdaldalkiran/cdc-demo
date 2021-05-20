FROM prom/prometheus:v2.26.1
RUN sed -i -e "s/'localhost:9090'/'localhost:9090','connect:9090'/" /etc/prometheus/prometheus.yml
