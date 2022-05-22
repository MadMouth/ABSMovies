FROM openjdk:11
ADD target/ABSMovies-0.0.2-SNAPSHOT.jar ABSMovies-0.0.2-SNAPSHOT.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "ABSMovies-0.0.2-SNAPSHOT.jar"]