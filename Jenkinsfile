pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        AWS_DEFAULT_REGION    = 'ap-south-1'
        REPO_URL              = 'https://github.com/naninetha05/CI_CD_Project'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Terraform Init') {
            steps {
                dir('terraform') {
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                dir('terraform') {
                    sh 'terraform plan -out=tfplan'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                dir('terraform') {
                    sh 'terraform apply -auto-approve tfplan'
                }
            }
        }

        stage('Verification') {
            steps {
                sh 'aws ec2 describe-instances --region ap-south-1 --output table'
            }
        }

        stage('Configure Jenkins Server') {
            steps {
                sh 'ansible-playbook -i ansible/inventory.ini ansible/jenkins-playbook.yml --private-key /var/lib/jenkins/.ssh/openclaw-key.pem -u ubuntu'
            }
        }

        stage('Configure Application Server') {
            steps {
                sh 'ansible-playbook -i ansible/inventory.ini ansible/app-playbook.yml --private-key /var/lib/jenkins/.ssh/project-key.pem -u ubuntu'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn sonar:sonar -Dsonar.projectKey=CI_CD_Project -Dsonar.projectName=CI_CD_Project'
                }
            }
        }

        stage('Deploy Application') {
            steps {
                sh 'ansible-playbook -i ansible/inventory.ini ansible/deploy-playbook.yml --private-key /var/lib/jenkins/.ssh/project-key.pem -u ubuntu -e "war_file_path=$(pwd)/target/app.war"'
            }
        }
    }

    post {
        success {
            echo 'pipeline done successfully'
        }
        failure {
            echo 'pipeline failed - check the logs'
        }
        always {
            cleanWs()
        }
    }
}
