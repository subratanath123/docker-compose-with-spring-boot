FROM openjdk:8-jdk-alpine AS build
WORKDIR /app

#COPY build.gradle  ./
#COPY src/ ./
#COPY gradlew  ./
#COPY gradle ./
#COPY .gradle ./
#COPY settings.gradle  ./
#COPY src ./

#########################
#Alternate way to do this is to copy all
#########################
COPY . ./

########################
#The default user in docker exec is the same user used to start the container which can be set in docker run or your compose file.
#########################
#RUN groupadd -r subrata && useradd -r -g subrata subrata
#USER subrata
#RUN chown subrata .gradle/

#########################
#Run gradle wrapper
#########################
RUN  ./gradlew build --no-daemon --stacktrace


LABEL maintainer="Subrata Nath"

#########################
# If needed we can install curl and apt-get update inside docker container
#########################
#RUN apt-get update
#RUN apt-get install curl
#RUN curl -O https://www-eu.apache.org/dist/tomcat/tomcat-8/v8.5.40/bin/apache-tomcat-8.5.40.tar.gz
#RUN tar xvfz apache*.tar.gz

#########################
# If you wish to deploy jar manully to tomcat then fetch tomcat image from dockerhub repo and then do this to deploy
#########################
#COPY --from=build /home/gradle/src/build/libs/First-Docker-app-web-0.0.1-SNAPSHOT.jar /usr/local/tomcat/webapps/spring-boot-application.jar

#########################
# Exposing 8080 port of the docker to the host machine
# Host machine then will map a port to outer world during docker run or docker compose
#########################
EXPOSE 8080

#########################
# This is entrypoint when running the image. Just trigger the project run
# ENTRYPOINT instruction is used to set executables that will always run when the container is initiated.
#########################
ENTRYPOINT ["./gradlew","bootRun"]
