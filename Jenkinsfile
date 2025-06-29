pipeline {
    agent any

    environment {
            JAVA_HOME = '/usr/local/openjdk-24'
            GRADLE_HOME = '/opt/gradle'
            PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
     }

    stages {
        stage('Build') {
        agent{
            docker {
              image 'eclipse-temurin:24-jdk'
              image 'docker pull gradle:8.14.0-jdk17-alpine'
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
