FROM java:8
EXPOSE 8085
ADD football-standings/target/football-standings-0.0.1-SNAPSHOT.jar football-standings.jar
ENTRYPOINT ["java","-jar","football-standings.jar"]