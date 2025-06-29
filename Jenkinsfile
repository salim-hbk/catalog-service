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
                        echo ">> Java Version"
                        java --version

                        echo ">> Installing Gradle 8.4.1"
                        wget https://services.gradle.org/distributions/gradle-8.14.2-bin.zip
                        unzip gradle-8.14.2-bin.zip
                        export GRADLE_HOME=$PWD/gradle-8.14.2
                        export PATH=$GRADLE_HOME/bin:$PATH

                        echo ">> Gradle Version"
                        gradle -v

                        echo ">> Build Project"
                        ./gradlew clean bootJar
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
