pipeline {
    environment {
        dockerImageName = 'ci-cd/movie-catalogue-service'
        dockerImage = ""
      }

    agent any

    stages {

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