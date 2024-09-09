FROM openjdk:22
LABEL authors="ctme"
EXPOSE 4000

# Copy the JAR file into the container
ADD target/spring-security.jar spring-security.jar

# Correct the entrypoint to use `-jar`
ENTRYPOINT ["java", "-jar", "dealerjar"]