FROM openjdk:17-jdk

WORKDIR /api

COPY target/to-do-list-0.0.1-SNAPSHOT.jar to-do-list.jar

ENTRYPOINT ["java", "-jar", "to-do-list.jar"]
