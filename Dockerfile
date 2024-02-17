FROM adoptopenjdk:21-jdk-hotspot-bionic-slim AS build

RUN apt-get update && \
    apt-get install -y wget maven && \
    apt-get clean && \
    wget -O /opt/openjdk-21.tar.gz https://github.com/adoptium/temurin21-binaries/releases/download/jdk-21%2B1/OpenJDK21U-jdk_x64_linux_hotspot_21_1.tar.gz && \
    tar -zxvf /opt/openjdk-21.tar.gz -C /opt/ && \
    rm /opt/openjdk-21.tar.gz

ENV JAVA_HOME /opt/jdk-21
ENV PATH $JAVA_HOME/bin:$PATH

WORKDIR /app
COPY . .

RUN mvn clean install

FROM adoptopenjdk:21-jdk-hotspot-bionic-slim

EXPOSE 8080

WORKDIR /app
COPY --from=build /app/target/literium-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]