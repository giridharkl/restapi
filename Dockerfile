FROM openjdk:8-jdk-alpine

WORKDIR /home/apps

COPY target/*.jar /home/apps/rest-api.jar

ENTRYPOINT ["java", "-jar","/home/apps/rest-api.jar"]