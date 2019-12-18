FROM openjdk:11
WORKDIR /src
ADD ./target/eureka-service-v1.0.0.jar /eureka-service/src/eureka-service-v1.0.0.jar
EXPOSE 8762
ENTRYPOINT ["java","-jar","/eureka-service/src/eureka-service-v1.0.0.jar"]
