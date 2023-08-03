FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/rest_api_springboot-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "rest_api_springboot-0.0.1-SNAPSHOT.jar"]