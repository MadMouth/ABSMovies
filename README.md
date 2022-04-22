# ABSMovies

REST API for the film searching service [ABSMovies](https://github.com/Maksewsha/ABSMovies).

# Technologies

### Backend
* Spring Boot
* Spring Web
* Spring Security
* Spring Data JPA
* MySQL Driver
* Json Web Token

### Services
* Yandex Cloud


### Set up the project
Maven is downloadable as a zip file at https://maven.apache.org/download.cgi.

Once you have downloaded the zip file, unzip it to your computer. Then add the bin folder to your path.

To test the Maven installation, run mvn from the command-line:

`mvn -v`


### Build Java code
Download the zip file and open the console in its root

To try out the build, issue the following at the command line:

`mvm package`

This will run Maven, telling it to execute the compile goal. When it’s finished, you should 
find the compiled .class files in the target/classes directory.

To execute the JAR file run:

`java -jar target/ABSMovies-0.0.1-SNAPSHOT.jar`


