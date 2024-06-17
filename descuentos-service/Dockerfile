FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} descuentos-service.jar
ENTRYPOINT ["java","-jar","/descuentos-service.jar"]