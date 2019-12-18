FROM openjdk:latest
WORKDIR /src
ADD ./target/vehicle-demand-service-v1.0.0.jar /vehicle-demand-service/src/vehicle-demand-service-v1.0.0.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/vehicle-demand-service/src/vehicle-demand-service-v1.0.0.jar"]
