FROM tomcat:9.0
ADD target/RADAR.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]