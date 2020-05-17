# Creating CI/CD pipeline using Cloud Build for Java App

The CI/CD pipelines can be build using different tech stacks like Jenkins, TeamCity, GitLab, etc. Here I am using Google Cloud Build to create the pipeline. This pipeline will use a Java Application from Cloud Source Repository to trigger the build. Here are the steps.

1. File push to the Cloud Repository will trigger the build

2. Create cloudbuild.yaml (Use Maven to produce the Jar file artifactory

3. Build Docker image

4. Wire the Source Repository Project to Cloud Build


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

### 3. Build Docker image

Dockerfile
```
FROM openjdk:11-jdk
ADD target/simple_micro_service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dserver.port=${PORT}","-jar","/app.jar"]
```

### 4. Wire the Source Repository Project to Cloud Build

Go to Cloud Build Menu and Click Triggers

[]image

Click Create Trigger

[]image

Give a name for the trigger(ex: simple-microservice-build-trigger), select the Source Repository(ex: simple_micro_service),  select the Branch to ".*", Build configuration to /cloudbuild.yaml and click Create.

[]image
To manually trigger the build, click "Run trigger"

Now go to Cloud Build History and select the Build .



Grant permissions
Cloud Build requires Cloud Run Admin and IAM Service Account User permissions before it can deploy an image to Cloud Run.

Open a terminal window.

Set environment variables to store your project ID and project number:

PROJECT_ID=$(gcloud config list --format='value(core.project)')
PROJECT_NUMBER=$(gcloud projects describe $PROJECT_ID --format='value(projectNumber)')

Grant the Cloud Run Admin role to the Cloud Build service account:

gcloud projects add-iam-policy-binding $PROJECT_ID \
    --member=serviceAccount:$PROJECT_NUMBER@cloudbuild.gserviceaccount.com \
    --role=roles/run.admin

Grant the IAM Service Account User role to the Cloud Build service account for the Cloud Run runtime service account:

gcloud iam service-accounts add-iam-policy-binding \
    $PROJECT_NUMBER-compute@developer.gserviceaccount.com \
    --member=serviceAccount:$PROJECT_NUMBER@cloudbuild.gserviceaccount.com \
    --role=roles/iam.serviceAccountUser
    
    
