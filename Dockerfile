FROM openjdk:11-jdk-oracle
COPY ./build/libs/DiscordBot-1.0-SNAPSHOT-all.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "DiscordBot-1.0-SNAPSHOT-all.jar"]
