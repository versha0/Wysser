FROM openjdk:11
WORKDIR /src
ADD ./target/config-service-v1.0.0.jar /config-service/src/config-service-v1.0.0.jar
EXPOSE 8889
ENTRYPOINT ["java","-jar","/config-service/src/config-service-v1.0.0.jar"]
