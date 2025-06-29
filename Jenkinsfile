pipeline {
    agent {
        docker {
            image 'eclipse-temurin:24-jdk'
            args '-u root:root'
        }
    }

    environment {
        GRADLE_VERSION = '8.14.2'
        GRADLE_ZIP = "gradle-${GRADLE_VERSION}-bin.zip"
        GRADLE_DIR = "gradle-${GRADLE_VERSION}"
        GRADLE_URL = "https://services.gradle.org/distributions/${GRADLE_ZIP}"
    }

    stages {
        stage('Setup Gradle') {
            steps {
                sh '''
                echo ">> Java Version"
                java --version

                echo ">> Install unzip and wget"
                apt-get update && apt-get install -y unzip wget

                if [ ! -d "$GRADLE_DIR" ]; then
                  echo ">> Downloading Gradle $GRADLE_VERSION"
                  wget -q "$GRADLE_URL"
                  unzip -o "$GRADLE_ZIP"
                else
                  echo ">> Gradle already set up"
                fi

                 echo ">> Add Gradle to PATH"
                        export GRADLE_HOME=$PWD/$GRADLE_DIR
                        export PATH=$GRADLE_HOME/bin:$PATH

                '''
            }
        }

        stage('Build Spring Boot JAR') {
            steps {
                sh '''
                echo ">> Make gradlew executable"
                chmod +x ./gradlew

                echo ">> Gradle version"
                gradle -v

                echo ">> Build Spring Boot JAR"
                ./gradlew clean bootJar
                '''
            }
        }
    }

    post {
        always {
            echo "Pipeline completed."
        }
    }
}
