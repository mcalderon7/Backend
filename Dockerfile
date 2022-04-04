FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD src/main/resources/back-end-0.0.1-SNAPSHOT.jar back-end.jar
ENTRYPOINT ["java","-jar","back-end.jar"]