pipeline {
    environment {
        dockerImageName = 'aissambsf/movie-catalogue-service'
        dockerImage = ""
      }

    agent any

    stages {

        stage('Build Project') {
            steps {
                bat 'echo %JAVA_HOME%'
                bat 'mvn clean package'
            }
        }

        stage('Build Image To Local Registry') {
            steps {
                script {
                    dockerImage = docker.build dockerImageName
                }
            }
        }

         stage('Deploying Service container to Kubernetes') {
            steps {
                script {
                    kubernetesDeploy(configs: "deployment.yaml", "service.yaml")
                }
            }
         }

    }
}
