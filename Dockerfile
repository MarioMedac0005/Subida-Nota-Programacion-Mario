# Etapa 1: build con Maven
FROM maven:3.8.6-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: runtime
FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Copiamos el JAR a la raíz del contenedor (no en /app)
COPY --from=builder /build/target/*.jar /app.jar

EXPOSE 8080

CMD sh -c "\
    echo 'Esperando a que MySQL…'; \
    while ! nc -z db 3306; do sleep 1; done; \
    echo 'MySQL listo. Arrancando app…'; \
    java -jar /app.jar \
"
