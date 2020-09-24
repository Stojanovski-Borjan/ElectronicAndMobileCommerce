#### Stage 1: Build the application
FROM openjdk:14.0.1-jdk-oracle as build

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline. 
# This is a separate step so the dependencies will be cached unless 
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw clean package 
#-DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:14.0.1-jdk-oracle

#ARG POSTGRES_CONTAINER
#ENV POSTGRES_CONTAINER_IP=$POSTGRES_CONTAINER
VOLUME /tmp
#EXPOSE 8081
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
COPY --from=build /app/target/rentscoot-0.0.1-SNAPSHOT.jar /app/spring-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "app/spring-app.jar"]