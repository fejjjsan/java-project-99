FROM gradle:8.6-jdk17

COPY . .

RUN gradle clean build

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production","java-project-99-0.0.1-SNAPSHOT.jar"]