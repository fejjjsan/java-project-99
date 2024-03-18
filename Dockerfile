FROM gradle:8.0-jdk17

WORKDIR /.

COPY /.

RUN gradle clean installDist

CMD gradle run