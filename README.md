
# Spring Boot Actuator

## Subprojects

* [Spring Boot Actuator](spring-boot-actuator/)
* [Spring Boot Admin Server](spring-boot-admin-server/)

---

## Run

1. Start fake STMP server
	```
	docker run -d --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog
	```

2. Start Boot Admin server
	```
	cd <PROJECT_ROOT_FOLDER>/spring-boot-admin-server
	mvnw clean spring-boot:run
	```

3. Start application
	```
	cd <PROJECT_ROOT_FOLDER>/spring-boot-actuator
	mvnw clean spring-boot:run
	```

---

## TODOs

* integrate security - `IN PROGRESS`
* integrate sleuth for tracing
* implement micrometer with prometheus and grafana

---

## Links

### Actuator
* http://www.baeldung.com/spring-boot-actuators
* http://www.baeldung.com/spring-boot-shutdown
* https://spring.io/blog/2017/08/22/introducing-actuator-endpoints-in-spring-boot-2-0
* https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html
* https://docs.spring.io/spring-boot/docs/current/reference/html/howto-build.html#howto-git-info
* http://www.briansjavablog.com/2017/09/health-checks-metric-s-more-with-spring.html
* https://www.infoq.com/presentations/spring-boot-actuator
* https://www.youtube.com/watch?v=hieLEsp5cTk

### Micrometer
* https://www.baeldung.com/micrometer
* https://dzone.com/articles/using-micrometer-with-spring-boot-2

### Collaterals
* http://www.baeldung.com/spring-email
* https://aboullaite.me/spring-boot-monitoring-prometheus-grafana/
* http://blog.monkey.codes/actuator-and-prometheus/

### Samples
* https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-actuator
* https://github.com/aboullaite/spring-boot-prometheus
* https://github.com/monkey-codes/spring-boot-restful
* https://gist.github.com/monkey-codes/65a3464537ca2b786215b47fe06104da

### Grafana
* https://grafana.com/dashboards
* https://ops.tips/blog/initialize-grafana-with-preconfigured-dashboards/
* https://github.com/cirocosta/sample-grafana/blob/master/grafana/Dockerfile
* http://docs.grafana.org/v5.0/administration/provisioning/#dashboards

### Prometheus
* https://github.com/vegasbrianc/prometheus/blob/master/docker-stack.yml
* https://prometheus.io/docs/prometheus/latest/getting_started/
* https://prometheus.io/docs/visualization/grafana/
* https://logz.io/blog/prometheus-monitoring/

### Monitoring
* https://finestructure.co/blog/2016/5/16/monitoring-with-prometheus-grafana-docker-part-1
