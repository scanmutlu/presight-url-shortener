FROM openjdk:11

# Add Maintainer Info
LABEL maintainer="canmtlu@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/url-shortener-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} url-shortener.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/url-shortener.jar"]