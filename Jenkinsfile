pipeline {
    agent {
        docker {
            image 'eclipse-temurin:24-jdk'
            args '-u root:root'
            reuseNode true
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



                '''
            }
        }

        stage('Build Spring Boot JAR') {
            steps {
                sh '''
                  echo ">> Add Gradle to PATH"
                                        export GRADLE_HOME=$PWD/$GRADLE_DIR
                                        export PATH=$GRADLE_HOME/bin:$PATH

                echo ">> Make gradlew executable"
                chmod +x ./gradlew

                echo ">> Gradle version"
                gradle -v

                echo ">> Build Spring Boot JAR"
                ./gradlew clean bootJar
                '''
            }
        }

        stage('Scan with Grype') {
            steps {
                sh '''
                echo ">> Checking if Grype is installed..."
                if ! command -v grype >/dev/null 2>&1; then
                    echo ">> Grype not found. Installing..."
                    wget -qO- https://raw.githubusercontent.com/anchore/grype/main/install.sh | sh -s -- -b /usr/local/bin
                else
                    echo ">> Grype is already installed."
                fi

                echo ">> Scanning Spring Boot JAR..."
                ls -lh build/libs/
                grype build/libs/*.jar --fail-on medium
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
