FROM openjdk:17-jdk-alpine
ADD target/sb-docker.jar sb-docker.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "sb-docker.jar"]