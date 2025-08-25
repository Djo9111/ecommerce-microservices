# Utilise une image JDK légère
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR construit
COPY target/*.jar app.jar

# Copier le fichier application.yml local
COPY src/main/resources/application.yml application.yml

# Exposer le port config-server
EXPOSE 8888

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
