FROM openjdk:11
ADD target/looqbox-test.jar docker-spring-boot.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar","docker-spring-boot.jar"]