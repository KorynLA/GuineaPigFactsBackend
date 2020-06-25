# Guinea Pig Facts
An API that allows users to create an account, add a guinea pig fact, and opt in to receive weekly facts. 
## Table of Contents
   * [Getting Started](#gettingStarted)
   * [Deployment](#deployment)
   * [Endpoints](#endpoints)
   * [Tools Used](#toolsUsed)

## Getting Started<a name="gettingStarted"></a>

### Prerequisites
- Java 8: if it not already installed follow these [Instructions](https://java.com/en/download/help/download_options.xml) 
- MongoDB Atlas: create an account and follow the [Getting Started Guide](https://docs.atlas.mongodb.com/getting-started/)
### Installing
1. Clone repository: git@github.com:KorynLA/GuineaPigFactsBackend.git
2. cd pwd+/guineaPigFacts/src/main/resources
3. Update the URI with your own string

## Deployment<a name="deployment"></a>
2. cd /guineapigfacts
3. Run ./mvnw spring-boot:run

## Endpoints<a name="endpoints"></a>
### /user
  - /user/
    - GET
    - Retrieves all users in the User collection
  - /user/{id}
    - GET
    - Retrieves user with the ID passed in the URL path
  - /user/add
    - POST
    - Adds a user to the User collection
  - /user/update/{id}
    - PUT
    - Updates a document in the User collection with the ID passed in the URL path
  - /user/delete/{id}
    - DELETE
    - Deletes a document with the ID passed in the URL path
### /fact
  - /fact/
    - GET
    - Retrieves all facts in the Fact collection
  - /fact/{id}
    - GET
    - Retrieves fact with the ID passed in the URL path
  - /fact/add
    - POST
    - Adds a fact to the Fact collection
  - /fact/delete/{id}
    - DELETE
    - Deletes a document with the ID passed in the URL path
## Tools Used<a name="toolsUsed"></a>
Spring Boot with the data loaded to and from a MongoDB Atlas database.
