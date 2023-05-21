FROM openjdk:8 AS builder
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:8
RUN mkdir /app
COPY --from=builder /app/target/*.jar /app/backend_app.jar
ENTRYPOINT ["java","-jar","/app/backend_app.jar"]