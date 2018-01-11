FROM ubuntu:16.04

WORKDIR ~/
RUN apt-get update && apt-get -y upgrade \
    && apt-get install -y wget apt-transport-https expect
# install java
RUN apt-get install -y default-jre default-jdk
# install scala
RUN wget https://downloads.lightbend.com/scala/2.12.4/scala-2.12.4.deb \
    && dpkg -i scala-2.12.4.deb
# install sbt
RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list \
    && apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2EE0EA64E40A89B84B2DF73499E82A75642AC823 \
    && apt-get update \
    && apt-get install -y sbt

# create project
RUN mkdir -p /var/work/scala-tour/
RUN cd /var/work/scala-tour/ \
    && expect -c "spawn sbt new scala/scala-seed.g8 expect \"name [Scala Seed Project]:\" send \"scala-tour\n\""

CMD tail -f /dev/null

