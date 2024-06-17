FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} ingreso-service.jar
ENTRYPOINT ["java","-jar","/ingreso-service.jar"]