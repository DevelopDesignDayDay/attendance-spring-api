FROM adoptopenjdk/openjdk11:alpine-jre

LABEL member1_email="fg9658@gmail.com"
LABEL member1_name="HellP"

LABEL member2_email="myeongsku@gmail.com"
LABEL member2_name="RbertKo"

LABEL version="0.1"
LABEL description="ddd attendance api"

WORKDIR /app
COPY  ./app.jar .
EXPOSE 8080

ENV RUNTIME_MODE=prod

CMD java -jar /app/app.jar
