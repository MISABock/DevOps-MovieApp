pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://host.docker.internal:9005'
        DOCKER_HOST = 'tcp://host.docker.internal:2375'
    }

    stages {
        stage('Install Docker CLI') {
    steps {
        sh '''
            apt-get update
            apt-get install -y docker.io
        '''
    }
}

        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'Github',
                    url: 'https://github.com/MISABock/DevOps-MovieApp.git'
            }
        }

        stage('Build & Test Backend') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean test jacocoTestReport'
                }
                junit '**/test-results/test/*.xml'
            }
        }

        stage('Lint Frontend') {
            steps {
                nodejs('NodeJS 22.11.0') {
                    dir('frontend') {
                        sh 'npm install'
                        sh 'npm run lint:html'
                    }
                }
            }
        }

        stage('SonarQube') {
            steps {
                withCredentials([string(credentialsId: 'Sonarqube-movieApp', variable: 'TOKEN')]) {
                    dir('backend') {
                        sh '''
                            ./gradlew sonar \
                            -Dsonar.projectKey=movieApp-backend \
                            -Dsonar.projectName="movieApp Backend" \
                            -Dsonar.host.url=$SONAR_HOST_URL \
                            -Dsonar.token=$TOKEN
                        '''
                    }
                }
            }
        }

        stage('Docker Build & Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerHub-michaelmisa', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '''
                        docker build -t michaelmisa/movieapp .
                        echo $PASSWORD | docker login -u $USERNAME --password-stdin
                        docker push michaelmisa/movieapp
                    '''
                }
            }
        }
    }
}
