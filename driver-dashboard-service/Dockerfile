FROM openjdk:latest
WORKDIR /src
ADD ./target/driver-dashboard-service-v1.0.0.jar /driver-dashboard-service/src/driver-dashboard-service-v1.0.0.jar
EXPOSE 8086
ENTRYPOINT ["java","-jar","/driver-dashboard-service/src/driver-dashboard-service-v1.0.0.jar"]