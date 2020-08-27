# COMBINATION - LAMBDA FUNCTION

Implementation of a simple mathematical combination algorithm where the order of the elements in the grouping does not interfere with the result. The algorithm runs on the architecture of Lambda AWS functions.

## Requirements

* AWS CLI already configured with at least PowerUser permission
* [Java SE Development Kit 8 installed](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Docker installed](https://www.docker.com/community-edition)
* [Maven](https://maven.apache.org/install.html)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)

## Setup process

### Installing dependencies

Use `maven` to install our dependencies and package our application into a JAR file:
```bash
cd Combination/
mvn clean package
```

### Local deploy

**Invoking function locally through local API Gateway**
1. Start the SAM local API.
 - `sam build --template combination.yaml`
 - `sam package --output-template-file packaged.yaml --s3-bucket <some bucket name>`
 - `sam local start-api`


If the previous command ran successfully you should now be able to hit the following local endpoint to
invoke the functions rooted at `http://localhost:3000/combination`

## Bringing to the next level

Next, you can use the following resources to know more about beyond hello world samples and how others
structure their Serverless applications:

* [AWS Serverless Application Repository](https://aws.amazon.com/serverless/serverlessrepo/)
