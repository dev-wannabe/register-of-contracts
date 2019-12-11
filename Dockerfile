FROM openjdk:8-jdk-alpine
ADD target/registerOfContracts-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
RUN  java -jar registerOfContracts-0.0.1-SNAPSHOT.jar