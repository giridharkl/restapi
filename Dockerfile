FROM openjdk:8-jdk-alpine

WORKDIR /apps

COPY target/*.jar /apps/rest-api.jar

#ENTRYPOINT ["java", "-jar","/apps/rest-api.jar"]

ENTRYPOINT ["nohup","java", "-jar","/apps/rest-api.jar","&"]
