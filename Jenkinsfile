pipeline {
    environment {
        dockerImageName = 'aissambsf/movie-catalogue-service'
        dockerImage = ""
        registryCredentials = "dockerhub-credentials"
        deploymentName = "movie-catalogue-service"
        containerName = "movie-catalogue-service"
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
                            dockerImage = docker.build  dockerImageName
                            //bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                        }
                    }
                }

        stage('Push Image') {
            steps {
                script {
                    docker.withRegistry('', registryCredentials) {
                        dockerImage.push()
                    }
                   //bat "docker push " + dockerImageName
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

                  stage('update deployment') {
                              steps {
                                  withKubeConfig([credentialsId: 'mykubeconfig']) {
                                      bat "kubectl set image deployments/"+deploymentName+" "+ containerName +"="+ dockerImageName+":latest"
                                  }
                              }
                            }
}
}
