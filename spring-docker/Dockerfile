FROM openjdk:alpine
ADD lab5-soa.jar app.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "/app.jar"]