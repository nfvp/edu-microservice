From tomcat:8.0.51-jre8-alpine
MAINTAINER rguedes@deloitte.pt

COPY ./build/libs/GetSomething-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/something-build.war

RUN docker rmi something-build:0.0.1
RUN docker image build -t something-build:0.0.1 .
RUN docker container run -p 8888:8080 -d something-build:0.0.1
RUN docker ps -a

EXPOSE 8080

CMD ["catalina.sh","run"]