pipeline {
    agent any

    stages {
        stage('Build') {
        agent{
            docker {
              image 'eclipse-temurin:24-jdk'
              reuseNode true
            }
        }
            steps {
               sh '''
               java --version
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
