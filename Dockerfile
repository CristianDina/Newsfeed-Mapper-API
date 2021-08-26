FROM openjdk:8
COPY target/Newsfeed-Mapper-API-0.0.1-SNAPSHOT.jar newsfeed.jar
ENTRYPOINT ["java","-jar","/newsfeed.jar"]