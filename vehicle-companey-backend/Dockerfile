FROM openjdk:latest
WORKDIR /src
ADD ./target/vehicle-company-backend-v1.0.0.jar /vehicle-company-backend/src/vehicle-company-backend-v1.0.0.jar
EXPOSE 8095
ENTRYPOINT ["java","-jar","/vehicle-company-backend/src/vehicle-company-backend-v1.0.0.jar"]