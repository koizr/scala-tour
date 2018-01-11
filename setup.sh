# setup scala-tour environment.
wd=`echo $(cd $(dirname "${0}") && pwd)`
docker build -t scala-tour:1.0 $wd
docker run -d --name scala_tour -v $wd:/var/work/scala-tour/ scala-tour:1.0

