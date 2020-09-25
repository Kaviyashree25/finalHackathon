FROM openjdk:11-jre
WORKDIR usr/src
ENV MONGO_DATABASE=socialbank
ENV MONGO_PORT=27017
ENV MONGO_HOST=localhost
ADD ./target/BankService-0.0.1-SNAPSHOT.jar /usr/src/BankService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "BankService-0.0.1-SNAPSHOT.jar"]