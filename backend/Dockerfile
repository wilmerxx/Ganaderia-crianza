FROM openjdk:17-alpine
RUN mkdir -p target
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} backend.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/backend.jar"]
