FROM  markhobson/maven-chrome:latest

WORKDIR /automation

COPY    . /automation

RUN     mvn clean test -Dtestng=testng