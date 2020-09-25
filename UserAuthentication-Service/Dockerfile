FROM openjdk:11-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=socialbank
ENV MYSQL_USER=approot
ENV MYSQL_PASSWORD=apppass
ENV MYSQL_HOST=localhost
ADD ./target/UserAuthentication-Service-0.0.1-SNAPSHOT.jar /usr/src/UserAuthentication-Service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "UserAuthentication-Service-0.0.1-SNAPSHOT.jar"]
