# Utilise une image JDK légère
FROM openjdk:17-jdk-slim

# Met à jour les paquets et installe curl
RUN apt-get update && apt-get install -y curl

# Définir le répertoire de travail
WORKDIR /app

# Copier le JAR construit
COPY target/*.jar app.jar

# Copier le fichier de configuration local
COPY src/main/resources/application.yml application.yml

# Exposer le port config-server
EXPOSE 8888

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]