# CI/CD Project on AWS

This is my DevOps project where I built a CI/CD pipeline to deploy a Java web app on AWS.
I used Terraform to create the infrastructure, Ansible to configure the servers, Jenkins to automate everything, and Tomcat to host the app.

No Docker, no Kubernetes. Just EC2, Terraform, Ansible, Jenkins, Maven, and Tomcat.

---

## What happens when I push code

1. I push code to GitHub
2. GitHub webhook triggers Jenkins automatically
3. Jenkins runs the pipeline:
   - Terraform creates the AWS infra (VPC, EC2, security groups)
   - Ansible installs and configures Jenkins and Tomcat servers
   - Maven builds the Java app into a WAR file
   - SonarQube checks code quality
   - Ansible deploys the WAR to Tomcat
4. App is live

---

## Tools I used

- **AWS** - EC2, VPC, S3, IAM, CloudWatch
- **Terraform** - to create AWS infrastructure using code
- **Ansible** - to install software on servers automatically
- **Jenkins** - CI/CD pipeline
- **Maven** - to build the Java app
- **SonarQube** - code quality check
- **Tomcat** - to run the Java app
- **Java 11** - the app language
- **GitHub** - source code + webhook

---

## Project structure

```
CI_CD_Project/
├── Jenkinsfile           - pipeline stages
├── pom.xml               - maven build file
├── src/
│   ├── main/java/        - HelloServlet.java
│   ├── main/webapp/      - web.xml
│   └── test/java/        - unit tests
├── terraform/
│   ├── main.tf
│   ├── variables.tf
│   ├── outputs.tf
│   ├── backend.tf
│   └── modules/
│       ├── vpc/
│       ├── sg/
│       └── ec2/
└── ansible/
    ├── inventory.ini
    ├── jenkins-playbook.yml
    ├── app-playbook.yml
    └── deploy-playbook.yml
```

---

## How to run

1. Clone the repo
2. Add AWS credentials in Jenkins (aws-access-key-id, aws-secret-access-key)
3. Update `terraform/backend.tf` with your S3 bucket name
4. Update `ansible/inventory.ini` with your server IPs
5. Set up GitHub webhook pointing to your Jenkins URL
6. Push code — pipeline runs automatically

---

## App

After deployment the app runs on port 8080 on the app server.

---

## GitHub

https://github.com/naninetha05/CI_CD_Project
