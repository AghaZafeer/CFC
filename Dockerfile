FROM openjdk:8-jdk-alpine
COPY target/spring-boot-0.0.1-SNAPSHOT.jar cfc-backend-1.0.0.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prd","-jar","/cfc-backend-1.0.0.jar"]

# docker image build -t cfc-backend .
#docker container run --network cfc-mysql --name cfc-backend -p 8081:8081 -d cfc-backend