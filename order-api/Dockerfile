FROM maven:3.9.7-eclipse-temurin-21 AS build-env
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build-env /app/target/*.jar /app/app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "/app/app.jar"]







