FROM openjdk:11-jre
WORKDIR usr/src
ADD ./target/netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar /usr/src/netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "netflix-eureka-naming-server-0.0.1-SNAPSHOT.jar"]
