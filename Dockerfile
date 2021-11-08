FROM openjdk:11
COPY target/testProj-1.8.jar /usr/test.jar
COPY config.yml /usr/config.yml

ENTRYPOINT ["java","-jar","/usr/test.jar","server", "/usr/config.yml"]