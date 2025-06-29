pipeline {
    agent any

    stages {
        stage('Build') {
        agent{
            docker {
              image 'eclipse-temurin:24-jdk'
              image 'gradle:8.4.1-jdk'
              reuseNode true
            }
        }
            steps {
               sh '''
               java --version
               gradle -v
               '''

            }
        }

    }

    post {
        always {
             echo "All steps..."
        }
    }
}
