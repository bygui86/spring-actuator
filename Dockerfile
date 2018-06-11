FROM openjdk:8-jre-alpine
ADD build/libs/ /opt/
WORKDIR /opt
EXPOSE 9192
ENTRYPOINT exec java $JAVA_OPTS -jar springactuatorsample-0.1-snapshot.jar
