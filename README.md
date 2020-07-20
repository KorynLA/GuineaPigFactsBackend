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
- MongoDB 
  - CLI: follow the [Installation Guide](https://docs.mongodb.com/manual/installation/)
  - MongoDB Atlas: create an account and follow the [Getting Started Guide](https://docs.atlas.mongodb.com/getting-started/)
### Installing
1. Clone repository: git@github.com:KorynLA/GuineaPigFactsBackend.git
2. Update the mongoDB that will be used by the application. 
    - If using MongoDB Atlas: 
      - cd /\<pwd>/guineaPigFacts/src/main/resources
      - Remove all text from application.properties
      - Add spring.data.mongodb.uri=[<YOUR_URI>](https://docs.atlas.mongodb.com/tutorial/connect-to-your-cluster/)  
  - If using MongoDB local
    - cd /\<pwd>/guineaPigFacts/src/main/resources
    - Update application.properties with database name you want to save the data in line 7: spring.data.mongodb.database=<YOUR_DATABASE>
## Local Deployment<a name="deployment"></a>
1. Have mongoDB instance running
  - Locally
    - Open a new shell and run the command: [mongo](https://docs.mongodb.com/manual/mongo/#start-the-mongo-shell-and-connect-to-mongodb)
  - MongoDB Atlas
    - Should already be running. The system status can be checked [here](https://status.cloud.mongodb.com/)
2. cd /guineapigfacts
3. Run ./mvnw spring-boot:run

## Endpoints<a name="endpoints"></a>
### /user
  - /user/{id}
    - GET
    - Retrieves user with the ID passed in the URL path
  - /user/
    - POST
    - Adds a user to the User collection
  - /user/{id}
    - PUT
    - Updates a document in the User collection with the ID passed in the URL path
  - /user/{id}
    - DELETE
    - Deletes a document with the ID passed in the URL path
### /fact
  - /fact/
    - GET
    - Retrieves all facts in the Fact collection
  - /fact/{id}
    - GET
    - Retrieves fact with the ID passed in the URL path
  - /fact/
    - POST
    - Adds a fact to the Fact collection
  - /fact/{id}
    - DELETE
    - Deletes a document with the ID passed in the URL path
## Tools Used<a name="toolsUsed"></a>
Spring Boot with the data loaded to and from a MongoDB Atlas database.
