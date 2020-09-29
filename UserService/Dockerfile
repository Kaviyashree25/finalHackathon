FROM openjdk:11-jre
WORKDIR usr/src
ENV MONGO_DATABASE=userDb
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
ADD ./target/UserService-0.0.1-SNAPSHOT.jar /usr/src/UserService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "UserService-0.0.1-SNAPSHOT.jar"]