From tomcat:9.0.29-jdk13-openjdk-oracle

MAINTAINER rguedes@deloitte.pt

ADD ./tomcat/tomcat-users.xml $CATALINA_HOME/conf
ADD ./tomcat/manager.xml $CATALINA_HOME/conf/Catalina/localhost
COPY ./build/libs/GetSomething-0.0.1-SNAPSHOT.war $CATALINA_HOME/webapps/something.war

EXPOSE 8080

CMD ["catalina.sh", "run"]