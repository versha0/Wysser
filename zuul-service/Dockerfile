FROM openjdk:11
WORKDIR /src
ADD ./target/zuul-service-v1.0.0.jar /zuul-service/src/zuul-service-v1.0.0.jar
ENTRYPOINT ["java","-jar","/zuul-service/src/zuul-service-v1.0.0.jar"]