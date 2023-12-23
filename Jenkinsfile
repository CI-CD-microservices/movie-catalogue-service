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

        stage('Build Image') {
            steps {
                script {
                    dockerImage = docker.build dockerImageName
                }
            }
        }

        stage('Pushing Image To Local Docker Registry') {
            environment {
                registryCredentials = 'dockerhub-credentials'
                localRegistryURL = 'http://localhost:5000'
            }
            steps {
                script {
                    docker.withRegistry(localRegistryURL, registryCredential) {
                        dockerImage.push('latest')
                    }
                }
            }
        }

    }
}
