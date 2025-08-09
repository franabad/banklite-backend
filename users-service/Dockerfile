# Dockerfile en la carpeta raíz del microservicio
# 1. Partimos de una imagen base con JDK
FROM eclipse-temurin:17-jdk-alpine

# 2. Copiamos el .jar generado por Maven/Gradle a la imagen
COPY target/users-service-0.0.1-SNAPSHOT.jar app-users.jar

# 3. Indicamos el comando que se ejecutará cuando arranque el contenedor
ENTRYPOINT ["java","-jar","/app.jar"]