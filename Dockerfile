FROM openjdk:8
EXPOSE 8085
ADD target/football-standings-0.0.1-SNAPSHOT.jar football-standings.jar
ENTRYPOINT ["java","-jar","football-standings.jar"]