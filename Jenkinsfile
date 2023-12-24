pipeline {
    environment {
        dockerImageName = 'aissambsf/movie-catalogue-service' + ":$BUILD_NUMBER"
        dockerImage = ""
        registryCredentials = "dockerhub-credentials"

      }

    agent any

    stages {

        stage('Build Project') {
            steps {
                bat 'echo %JAVA_HOME%'
                bat 'mvn clean package'
            }
        }

        stage('Build Image ') {
            steps {
                script {
                    dockerImage = docker.build  dockerImageName
                }
            }
        }

        stage('Push Image To DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', registryCredentials) {
                        dockerImage.push()
                    }
                }
            }
        }

         stage('Deploying Service Container to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: 'mykubeconfig']) {
                                bat 'kubectl get pods'
                                bat 'kubectl apply -f deployment.yaml'
                                bat 'kubectl get pods'
                            }
            }
         }

          stage('Apply Service Configuration') {
                     steps {
                         withKubeConfig([credentialsId: 'mykubeconfig']) {
                            bat 'kubectl apply -f service.yaml'
                         }
                     }
                  }
    }
}
