pipeline {

    agent any

    stages {

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t student-api:${BUILD_NUMBER} .'
            }
        }
    }
}