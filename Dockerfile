# Dependencies
FROM maven:3.9.6-eclipse-temurin-21 AS dependencies
WORKDIR /cplerings

COPY pom.xml ./
COPY cplerings-core-api/pom.xml ./cplerings-core-api/
COPY cplerings-core-application/pom.xml ./cplerings-core-application/
COPY cplerings-core-common/pom.xml ./cplerings-core-common/
COPY cplerings-core-domain/pom.xml ./cplerings-core-domain/
COPY cplerings-core-infrastructure/pom.xml ./cplerings-core-infrastructure/

RUN mvn -B -e dependency:go-offline

# Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /cplerings

COPY --from=dependencies /root/.m2 /root/.m2/
COPY --from=dependencies /cplerings/ ./
COPY cplerings-core-api/src ./cplerings-core-api/src/
COPY cplerings-core-application/src ./cplerings-core-application/src/
COPY cplerings-core-common/src ./cplerings-core-common/src/
COPY cplerings-core-domain/src ./cplerings-core-domain/src/
COPY cplerings-core-infrastructure/src ./cplerings-core-infrastructure/src/

RUN mvn -B -e clean install -DskipTests=true

# Run
FROM eclipse-temurin:21-jdk
WORKDIR /cplerings

COPY --from=build /cplerings/cplerings-core-infrastructure/target/*.jar ./cplerings.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/cplerings/cplerings.jar"]