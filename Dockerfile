From rlguedes/tomcat_base
MAINTAINER rguedes@deloitte.pt

COPY ./build/libs/GetSomething-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/something.war

EXPOSE 8080

CMD ["catalina.sh", "run"]