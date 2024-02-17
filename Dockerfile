FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y wget && \
    apt-get clean

WORKDIR /app
COPY . .

RUN apt-get install -y maven

RUN wget -O adoptopenjdk.tar.gz https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21%2B1/OpenJDK21U-jdk_x64_linux_hotspot_21_1.tar.gz && \
    tar -zxvf adoptopenjdk.tar.gz && \
    mv jdk* /opt/openjdk-21 && \
    rm adoptopenjdk.tar.gz

ENV JAVA_HOME /opt/openjdk-21
ENV PATH $JAVA_HOME/bin:$PATH

RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

WORKDIR /app
COPY --from=build /app/target/literium-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]