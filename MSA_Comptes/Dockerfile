FROM openjdk:17-jdk-alpine
ADD target/sb-compte-docker.jar sb-compte-docker.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "sb-compte-docker.jar"]