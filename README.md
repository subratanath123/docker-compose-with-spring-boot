# Docker With Docker Compose & Integration testing Sample

This code shows how to build an image and automate the integration testing with docker and docker compose

## Docker file
```sh
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
```
## Include this in docker-compose.yml
```sh
  services:
  dev:
    build: .
    image: my-web-app-dev
    environment:
      USER: subrata
    ports:
      - "8080:8080"
    networks:
      - my-shared-network
    volumes:
      - type: volume
        source: tmp
        target: /tmp
    depends_on:
      - prod

  prod:
    build: .
    image: my-web-app-prod
    environment:
      USER: subrata
    ports:
      - "8081:8080"
    networks:
      - my-shared-network
    volumes:
      - type: volume
        source: tmp
        target: /tmp

networks:
  my-shared-network: { }
volumes:
  tmp:
```

# After that build the image with docker compose

##### This will build two container image; One is web, another one is hello

> sudo docker-compose -f  docker-compose.yml build

##### Then run the both container image

> sudo docker-compose -f  docker-compose.yml up -d


# Now your containers are running

> Use ```docker ps``` to get the name of the existing container

> Use the command ```docker exec -it <container name> /bin/sh``` to get a bash shell in the container

> Generically, use ```docker exec -it <container name> <command>``` to execute whatever command you specify in the container.



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

[test-link]: <https://>
