FROM openjdk:8
EXPOSE 8081 
ARG target/spring-boot-docker spring-boot-docker
ENTRYPOINT [ "java","-jar","/spring-boot-docker" ]