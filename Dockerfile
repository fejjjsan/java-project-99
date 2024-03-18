FROM gradle:latest-jdk20

WORKDIR /.

COPY . .

RUN gradle clean installDist

CMD gradle run