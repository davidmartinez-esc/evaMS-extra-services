FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} bonos-aplicados-service.jar
ENTRYPOINT ["java","-jar","/bonos-aplicados-service.jar"]