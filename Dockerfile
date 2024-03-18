FROM gradle:8.6-jdk20

WORKDIR /java-project-99

COPY /java-project-99 .

RUN gradle clean installDist

CMD gradle run