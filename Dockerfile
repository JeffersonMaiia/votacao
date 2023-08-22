FROM openjdk:17-oracle

VOLUME /tmp
COPY /target/votacao*.jar votacao.jar
SHELL ["/bin/sh", "-c"]

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/votacao.jar"]
