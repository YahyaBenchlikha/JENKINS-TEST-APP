pipeline {
    agent any
    
    triggers {
        // Se déclenche sur push vers le repository
        pollSCM('H/5 * * * *') // Vérifie toutes les 5 minutes
        // Ou utiliser githubPush() si webhook configuré
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Setup Maven') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            if ! command -v mvn &> /dev/null; then
                                echo "Installing Maven..."
                                wget https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.tar.gz
                                tar -xzf apache-maven-3.9.4-bin.tar.gz
                                export PATH=$PWD/apache-maven-3.9.4/bin:$PATH
                            fi
                        '''
                    } else {
                        bat '''
                            where mvn >nul 2>nul
                            if %errorlevel% neq 0 (
                                echo Installing Maven...
                                powershell -Command "& {Invoke-WebRequest -Uri 'https://archive.apache.org/dist/maven/maven-3/3.9.4/binaries/apache-maven-3.9.4-bin.zip' -OutFile 'maven.zip'}"
                                powershell -Command "& {Expand-Archive -Path 'maven.zip' -DestinationPath '.'}"
                                set "PATH=%CD%\\apache-maven-3.9.4\\bin;%PATH%"
                            )
                        '''
                    }
                }
            }
        }
        
        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            export PATH=$PWD/apache-maven-3.9.4/bin:$PATH
                            mvn clean compile
                        '''
                    } else {
                        bat '''
                            set "PATH=%CD%\\apache-maven-3.9.4\\bin;%PATH%"
                            mvn clean compile
                        '''
                    }
                }
            }
        }
        
        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            export PATH=$PWD/apache-maven-3.9.4/bin:$PATH
                            mvn test
                        '''
                    } else {
                        bat '''
                            set "PATH=%CD%\\apache-maven-3.9.4\\bin;%PATH%"
                            mvn test
                        '''
                    }
                }
            }
            post {
                always {
                    publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: 'target/surefire-reports',
                        reportFiles: 'index.html',
                        reportName: 'Test Report'
                    ])
                }
            }
        }
        
        stage('Package') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            export PATH=$PWD/apache-maven-3.9.4/bin:$PATH
                            mvn package -DskipTests
                        '''
                    } else {
                        bat '''
                            set "PATH=%CD%\\apache-maven-3.9.4\\bin;%PATH%"
                            mvn package -DskipTests
                        '''
                    }
                }
            }
        }
        
        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
    
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
