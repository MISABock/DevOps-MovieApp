FROM openjdk:21-jdk-slim

# Node.js installieren
RUN apt-get update && apt-get install -y curl \
    && curl -sL https://deb.nodesource.com/setup_20.x | bash - \
    && apt-get install -y nodejs

WORKDIR /usr/src/app

# Projektdateien kopieren
COPY . .

# Frontend bauen und ins richtige Spring Boot Static-Verzeichnis legen
RUN cd frontend \
  && npm install \
  && npm run build \
  && mkdir -p backend/app/src/main/resources/static \
  && cp -r build/. backend/app/src/main/resources/static/

# Backend bauen
RUN cd backend \
  && chmod +x gradlew \
  && ./gradlew bootJar

EXPOSE 8090

# App starten
CMD ["java", "-jar", "/usr/src/app/backend/app/build/libs/app.jar"]
