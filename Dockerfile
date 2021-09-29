FROM openjdk:11 AS local
WORKDIR /app
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "app.jar"]


FROM openjdk:11 AS builder
WORKDIR /app
COPY . /app
RUN ./gradlew clean build -x test


FROM builder AS development
WORKDIR /app
CMD ["java", "-jar", "build/libs/TransactionManager-0.0.1-SNAPSHOT.jar"]

FROM openjdk:11-jre-slim AS production
WORKDIR /app
COPY --from=builder /app/build/libs/TransactionManager-0.0.1-SNAPSHOT.jar /app/TransactionManager-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "TransactionManager-0.0.1-SNAPSHOT.jar"]
