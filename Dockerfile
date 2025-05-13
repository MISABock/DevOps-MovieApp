FROM openjdk:21-jdk-slim

# Docker CLI + Node.js installieren
RUN apt-get update && apt-get install -y \
    curl \
    gnupg \
    ca-certificates \
    apt-transport-https \
    software-properties-common \
    lsb-release \
    sudo \
    docker.io \
 && curl -sL https://deb.nodesource.com/setup_20.x | bash - \
 && apt-get install -y nodejs \
 && apt-get clean

# Arbeitsverzeichnis setzen
WORKDIR /usr/src/app

# Projektdateien kopieren
COPY . .

# Frontend bauen
RUN cd frontend \
  && npm install \
  && npm run build \
  && mkdir -p backend/app/src/main/resources/static \
  && cp -r build/. backend/app/src/main/resources/static/

# Backend bauen
RUN cd backend \
  && chmod +x gradlew \
  && ./gradlew bootJar

# Port für Spring Boot App
EXPOSE 8090

# Startbefehl
CMD ["java", "-jar", "/usr/src/app/backend/app/build/libs/app.jar"]
