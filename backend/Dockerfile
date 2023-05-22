ARG MVN_VERSION=3.6.3
ARG JDK_VERSION=8

FROM maven:${MVN_VERSION}-jdk-${JDK_VERSION}  AS builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN mvn clean package

FROM openjdk:8
RUN mkdir /app
COPY --from=builder /app/target/*.jar /app/backend_app.jar
ENTRYPOINT ["java","-jar","/app/backend_app.jar"]
