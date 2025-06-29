pipeline {
    agent any

    stages {
        stage('Build') {
        agent{
            docker {
              image 'eclipse-temurin:24-jdk'
               args '-u root:root'
              reuseNode true
            }
        }
         steps {
                        sh '''
                        echo ">> Installing unzip and wget"
                        apt-get update && apt-get install -y unzip wget

                        echo ">> Java Version"
                        java --version

                        # Check if Gradle folder exists
                        if [ ! -d "gradle-8.14.2" ]; then
                          echo ">> Gradle not found, downloading..."
                          wget -q https://services.gradle.org/distributions/gradle-8.14.2-bin.zip
                          unzip -o gradle-8.14.2-bin.zip
                        else
                          echo ">> Gradle 8.14.2 already exists, skipping download."
                        fi

                        export GRADLE_HOME=$PWD/gradle-8.14.2
                        export PATH=$GRADLE_HOME/bin:$PATH

                        echo ">> Gradle Version"
                        gradle -v
                        echo ">> Setting execute permission on gradlew"
                        chmod +x ./gradlew

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
