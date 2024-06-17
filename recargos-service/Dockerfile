FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} recargos-service.jar
ENTRYPOINT ["java","-jar","/recargos-service.jar"]