FROM openjdk:8-jre-alpine
ADD target/ /opt/
WORKDIR /opt
EXPOSE 9192
ENTRYPOINT exec java $JAVA_OPTS -jar springactuatorsample.jar
