From tomcat:8.0.51-jre8-alpine
MAINTAINER rguedes@deloitte.pt

COPY ./build/libs/GetSomething-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/build-test.war

EXPOSE 8080

CMD ["catalina.sh","run"]