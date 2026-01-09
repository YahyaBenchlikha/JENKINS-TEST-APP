pipeline {
    agent any

    tools {
        // Assurez-vous que 'Maven3' est bien le nom configuré dans 'Manage Jenkins' -> 'Tools'
        maven 'Maven3'
    }

    environment {
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "8081"
        CONTAINER_PORT = "8080" // Port défini dans votre Dockerfile (EXPOSE 8080)
        
        // Variables TP4 (SonarQube)
        SONAR_PROJECT_KEY = "tp4-java-project"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YahyaBenchlikha/JENKINS-TEST-APP.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn -B clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn -B test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    jacoco()
                }
            }
        }
        stage('Test Credentials') {
            steps {
                
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    bat 'echo Le token est récupéré mais Jenkins va masquer sa valeur : %GITHUB_TOKEN%'
                }
            }
        }
        stage('SonarQube Analysis') {
                steps {
                    withSonarQubeEnv('SonarQube') {
                        bat """
                            mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar ^
                            -Dsonar.projectKey=${SONAR_PROJECT_KEY}
                        """
                    }
                }
            }

        stage('Quality Gate') {
            steps {
                // Attend le verdict de SonarQube avant de continuer vers le déploiement
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Docker Build') {
            steps {
                bat "docker build -t %IMAGE_NAME% ."
            }
        }

        stage('Deploy (Local Docker)') {
            steps {
                bat """
                @echo off
                echo Arret du conteneur s'il existe...
                docker stop ${CONTAINER_NAME} 2>nul || ver >nul
                
                echo Suppression du conteneur s'il existe...
                docker rm ${CONTAINER_NAME} 2>nul || ver >nul
                
                echo Lancement du conteneur sur http://localhost:${HOST_PORT}
                docker run -d --name ${CONTAINER_NAME} -p ${HOST_PORT}:${CONTAINER_PORT} ${IMAGE_NAME}
                """
            }
        }
    }

    post {
        success {
            echo "✅ TP4 réussi : Qualité OK, application déployée via Docker"
        }
        failure {
            echo "❌ Pipeline échoué : vérifiez les tests ou le Quality Gate SonarQube"
        }
    }
}
//jacoco 