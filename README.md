## Example Quotation Project

Used technologies:  
 - Spring Boot
 - H2
 - Data JPA
 - Lombok
 - Maven
 - Docker


### Build

`mvn package`  
or  
`./mvnw package`

### How to run

`java -jar target/svc.jar`  
or  
`docker build -f Dockerfile -t quotation . && docker run -p 8080:8080 quotation:latest`   
or  
`docker-compose  up --build`  