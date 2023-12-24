pipeline {
    environment {
        dockerImageName = 'aissambsf/movie-catalogue-service' + ":$BUILD_NUMBER"
        dockerImage = ""
        registryCredentials = "dockerhub-credentials"
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
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
                    //dockerImage = docker.build  dockerImageName
                    bat "docker build -t " + dockerImageName + " ."
                }
            }
        }

        stage('Login To DockerHub') {
                    steps {
                        script {
                            //dockerImage = docker.build  dockerImageName
                            bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                        }
                    }
                }

        stage('Push Image') {
            steps {
                script {
                   // docker.withRegistry('https://registry.hub.docker.com', registryCredentials) {
                   //     dockerImage.push()
                   // }
                   bat "docker push " + dockerImageName
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
