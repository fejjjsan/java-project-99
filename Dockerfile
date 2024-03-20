FROM gradle:8.6-jdk17

COPY . .

RUN gradle clean build

EXPOSE 8080

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=production","build/libs/java-project-99-0.0.1-SNAPSHOT.jar"]