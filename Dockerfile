FROM openjdk:17-jdk-slim

# Instalamos netcat para poder esperar a que la BD esté lista
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Creamos el directorio de trabajo
WORKDIR /app

# Copiamos el JAR generado
COPY target/*.jar app.jar

# Exponemos el puerto de la app
EXPOSE 8080

# El ENTRYPOINT lo dejamos vacío porque lo sobreescribimos en docker-compose.yml
CMD ["java", "-jar", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]

