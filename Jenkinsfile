pipeline {
    agent any

    environment {
        SONAR_HOST_URL = 'http://host.docker.internal:9005'
    }

    stages {
        stage('Debug: pwd + ls') {
            steps {
                sh 'pwd'
                sh 'ls -la'
            }
        }

        stage('Checkout full repo') {
            steps {
                git branch: 'main',
                    credentialsId: 'Github',
                    url: 'https://github.com/MISABock/DevOps-MovieApp.git'
            }
        }

        stage('Debug: repo contents') {
            steps {
                sh 'ls -la'
                sh 'ls -la backend || true'
            }
        }

        stage('Build & Test') {
            steps {
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew clean test jacocoTestReport'
                }
                junit testResults: '**/test-results/test/*.xml'
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

        stage('SonarQube Analysis') {
            steps {
                withCredentials([string(credentialsId: 'Sonarqube-movieApp', variable: 'TOKEN')]) {
                    dir('backend') {
                        sh '''#!/bin/bash
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

        stage('Docker Build') {
            steps {
                sh '''
                    export DOCKER_HOST=tcp://host.docker.internal:2375
                    docker build -t michaelmisa/movieapp .
                '''
            }
        }

        stage('Docker Push') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DockerHub-michaelmisa', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                    sh '''
                        export DOCKER_HOST=tcp://host.docker.internal:2375
                        echo $PASSWORD | docker login -u $USERNAME --password-stdin
                        docker push michaelmisa/movieapp
                    '''
                }
            }
        }
    }
}
