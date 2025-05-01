FROM eclipse-temurin:21-jdk-alpine

WORKDIR /opt/app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src ./src

RUN mvn dependency:go-offline -B

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]