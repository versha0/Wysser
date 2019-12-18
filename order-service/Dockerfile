FROM openjdk:latest
WORKDIR /src
ADD ./target/order-service-v1.0.0.jar /order-service/src/order-service-v1.0.0.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/order-service/src/order-service-v1.0.0.jar"]