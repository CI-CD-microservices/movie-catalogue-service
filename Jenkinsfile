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
        node {
          stage('Deploying Service Container to Kubernetes') {
            withKubeConfig([credentialsId: 'mykubeconfig']) {
                bat 'kubectl get pods'
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl get pods'
            }
          }
        }

    }
}
