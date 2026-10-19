
pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Package Application') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t devops-cicd-app:latest .'
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                    docker rm -f devops-app || true

                    docker run -d \
                        --name devops-app \
                        -p 8080:8080 \
                        devops-cicd-app:latest

                    for i in $(seq 1 15); do
                        if curl -fsS http://localhost:8080/health; then
                            echo "Deployment successful!"
                            exit 0
                        fi
                        sleep 2
                    done

                    echo "Application health check failed!"
                    docker logs devops-app
                    exit 1
                '''
            }
        }
    }

    post {
        success {
            echo 'CI/CD pipeline completed successfully!'
        }

        failure {
            echo 'Pipeline failed. Check the stage logs.'
        }
    }
}

