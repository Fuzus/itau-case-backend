FROM maven:3.9.3-eclipse-temurin-17 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/case-backend-jr-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "case-backend-jr-0.0.1-SNAPSHOT.jar"]