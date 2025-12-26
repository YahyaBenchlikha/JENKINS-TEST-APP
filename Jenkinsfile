pipeline {
    agent any
    tools {
        maven 'Maven3' // Le nom que vous avez donné dans la config
    }
    environment {
        IMAGE_NAME = "tp3-java-app:latest"
        CONTAINER_NAME = "tp3-java-container"
        HOST_PORT = "8081"
        CONTAINER_PORT = "9090"
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/YahyaBenchlikha/JENKINS-TEST-APP.git'
            }
        }
        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME% .'
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
                
                echo Lancement du nouveau conteneur sur le port ${HOST_PORT}...
                docker run -d --name ${CONTAINER_NAME} -p ${HOST_PORT}:${CONTAINER_PORT} ${IMAGE_NAME}
                """
            }
}
    }
    post {
        success {
            echo "✅ Déploiement local terminé"
        }
        failure {
            echo "❌ Erreur dans le pipeline"
        }
    }
}
