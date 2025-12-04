FROM maven:3.9.11-amazoncorretto-24
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY src/main/resources/appsettings.docker.properties src/main/resources/appsettings.properties
RUN mvn clean package
CMD ["java", "-jar", "target/Projekt_nprogramistyczne-1.0-SNAPSHOT.jar"]