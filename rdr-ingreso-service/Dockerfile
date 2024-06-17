FROM openjdk:17
ARG JAR_FILE=./target/*.jar
COPY ${JAR_FILE} rdr-ingreso-service-service.jar
ENTRYPOINT ["java","-jar","/rdr-ingreso-service-service.jar"]