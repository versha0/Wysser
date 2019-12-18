FROM openjdk:latest
WORKDIR /src
ADD ./target/admin-v1.0.0.jar /admin/src/admin-v1.0.0.jar
EXPOSE 9086
ENTRYPOINT ["java","-jar","/admin/src/admin-v1.0.0.jar"]
