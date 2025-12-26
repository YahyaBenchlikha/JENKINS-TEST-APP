pipeline {
    agent any
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
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t $IMAGE_NAME .'
            }
        }
        stage('Deploy Local Docker') {
            steps {
                sh '''
                    docker stop $CONTAINER_NAME || true
                    docker rm $CONTAINER_NAME || true
                    docker run -d --name $CONTAINER_NAME -p $HOST_PORT:$CONTAINER_PORT $IMAGE_NAME
                '''
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
