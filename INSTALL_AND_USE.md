# HOW TO USE

#### The Application has 2 ways to run:
     - Using Docker
        * First you need to create a build of the application
          * Run `./gradlew build` in the root folder of the project
        * Run `docker build -t petshop:1.0 .` in the root folder of the project for build the docker image
        * Run `docker run -p 8080:8080 --rm petshop:1.0` for run a new container with the application
            - The Application will be available at `http://localhost:8080` with base path `/api/pets/`

     - Using Spring Boot Application with Gradle

### With this Postman collection you can test the application: [COLLECTION JSON](src/main/resources/Petshop.postman_collection.json) 

# ENDPOINTS
* For populate the database with a simple relationship between Pet and Owner, you can use the endpoint `Populate` `GET /api/pets/populate`