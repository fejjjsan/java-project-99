FROM gradle:8.6-jdk17

COPY . .

RUN gradle clean build

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production","build/libs/java-project-99-0.0.1-SNAPSHOT.jar"]