FROM openjdk:11-jre
WORKDIR usr/src
ENV MONGO_DATABASE = socialbank
ENV MONGO_PORT = 27017
ENV MONGO_HOST = localhost
ADD ./target/PostService-0.0.1-SNAPSHOT.jar /usr/src/PostService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "PostService-0.0.1-SNAPSHOT.jar"]