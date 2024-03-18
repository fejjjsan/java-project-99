FROM gradle:8.0-jdk17

WORKDIR /java-project-99

COPY /java-project-99 .

RUN gradle clean installDist

CMD gradle run