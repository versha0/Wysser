FROM openjdk:latest
WORKDIR /src
ADD ./target/jwt-security-v1.0.0.jar /authentication-service/src/jwt-security-v1.0.0.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","/authentication-service/src/jwt-security-v1.0.0.jar"]
