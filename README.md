# Docker With Docker Compose & Integration testing Sample

This code shows how to build an image and automate the integration testing with docker and docker compose

## Docker file
```sh
    FROM gradle:4.7.0-jdk8-alpine AS build
    COPY . /home/gradle/src
    RUN gradle build --no-daemon
    FROM openjdk:8-jre-slim
    EXPOSE 8080
    RUN mkdir /app
    
    COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
    ENTRYPOINT ["java", "-jar","/app/spring-boot-application.jar"]
```
## Include this in docker-compose.yml
```sh
    web:
        build: .
        dockerfile: Dockerfile
        links:
            - hello
        ports:
            - "80:80"
    hello:
        image: hello-world
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
