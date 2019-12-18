FROM openjdk:latest
WORKDIR /src
ADD ./target/user-registration-v1.0.0.jar /user-registrations/src/user-registration-v1.0.0.jar
EXPOSE 8088
ENTRYPOINT ["java","-jar","/user-registrations/src/user-registration-v1.0.0.jar"]