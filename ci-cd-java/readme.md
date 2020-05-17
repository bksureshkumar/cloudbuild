# Creating CI/CD pipeline using Cloud Build for Java App

The CI/CD pipelines can be build using different tech stacks like Jenkins, TeamCity, GitLab, etc. Here I am using Google Cloud Build to create the pipeline. This pipeline will use a Java Application from Cloud Source Repository to trigger the build. Here are the steps.

1. File push to the Cloud Repository will trigger the build

2. Create cloudbuild.yaml (Use Maven to produce the Jar file artifactory

3. Copy the Jar Artfact to Google Cloud Storage(GCS)

4. Build Docker image

5. Deploy The Docker image to Cloud Run


### 1. File push to the Cloud Repository will trigger the build

Create a Java Application and Push it to the Cloud Source Repository or (GIT). I have used the Microservice code in the GIT(https://github.com/bksureshkumar/cloudrun/tree/master/simple_micro_service) to trigger the build. Have a copy of the code in the Cloud Source Repository

### 2. Create cloudbuild.yaml

Cloudbuild.yaml have the instructions to build the source code and Copy the artifactory to GCS and Container Registery and deploy oit on the Cloud Run.

```
steps:
 - name: maven:3-jdk-8
   entrypoint: mvn
   args: ['--version']    
 - name: maven:3-jdk-8
   entrypoint: mvn
   args: ['package', '-Dmaven.test.skip=true']
 - name: maven:3-jdk-8
   entrypoint: mvn
   args: ['exec:java']
artifacts:
  objects:
    location: 'gs://host-project-deep-data-mart/artifacts/'
    paths: ['target/testApp-1.0.jar']   
 ```   
    
