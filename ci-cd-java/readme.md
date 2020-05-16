# Creating CI/CD pipeline using Cloud Build for Java App

The CI/CD pipelines can be build using different tech stacks like Jenkins, TeamCity, GitLab, etc. Here I am using Google Cloud Build to create the pipeline. This pipeline will use a Java Application from Cloud Source Repository to trigger the build. Here are the steps.

1. File push to the Cloud Repository will trigger the build

2. Use Maven to produce the Jar file artifactory

3. Copy the Jar Artfact to Google Cloud Storage(GCS)

4. Build Docker image

5. Deploy The Docker image to Cloud Run


### 1. File push to the Cloud Repository will trigger the build

Create a Java Application and Push it to the Cloud Source Repository or (GIT)

