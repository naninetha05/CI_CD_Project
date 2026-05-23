pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
        AWS_DEFAULT_REGION    = 'ap-south-1'
        REPO_URL              = 'https://github.com/naninetha05/CI_CD_Project'
        TF_DIR                = 'terraform'
    }

    stages {

        stage('Clone Repository') {
            steps {
                git branch: 'main', url: "${REPO_URL}"
            }
        }

        stage('Terraform Init') {
            steps {
                dir("${TF_DIR}") {
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                dir("${TF_DIR}") {
                    sh 'terraform plan -out=tfplan'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                dir("${TF_DIR}") {
                    sh 'terraform apply -auto-approve tfplan'
                }
            }
        }

        stage('Verification') {
            steps {
                sh 'aws ec2 describe-instances --region ap-south-1 --query "Reservations[*].Instances[*].{ID:InstanceId,State:State.Name,IP:PublicIpAddress}" --output table'
            }
        }

        stage('Configure Jenkins Server') {
            steps {
                sh '''
                    ansible-playbook -i ansible/inventory.ini ansible/jenkins-playbook.yml \
                        --private-key ~/.ssh/project-key.pem \
                        -u ubuntu
                '''
            }
        }

        stage('Configure Application Server') {
            steps {
                sh '''
                    ansible-playbook -i ansible/inventory.ini ansible/app-playbook.yml \
                        --private-key ~/.ssh/project-key.pem \
                        -u ubuntu
                '''
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy Application') {
            steps {
                sh '''
                    ansible-playbook -i ansible/inventory.ini ansible/deploy-playbook.yml \
                        --private-key ~/.ssh/project-key.pem \
                        -u ubuntu \
                        -e "war_file_path=$(pwd)/target/app.war"
                '''
            }
        }
    }

    post {
        success {
            echo '✅ Pipeline completed successfully! Infrastructure provisioned and application deployed.'
        }
        failure {
            echo '❌ Pipeline failed. Check the logs above for details.'
        }
        always {
            cleanWs()
        }
    }
}
