FROM gradle:8.0-jdk17

WORKDIR /.

COPY . .

RUN gradle clean installBootDist

CMD gradle bootRun