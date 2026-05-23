# AWS DevOps CI/CD Project

A production style DevOps project where I built a complete CI/CD pipeline to deploy a Java web application on AWS without Docker or Kubernetes.

---

## What I Built

I set up the entire workflow a real company would use:

- Developer pushes code to GitHub
- GitHub webhook triggers Jenkins automatically
- Jenkins runs the full pipeline build, test, infrastructure, and deploy
- Application ends up running on an EC2 server via Tomcat

---

## What I Used

- **Terraform** — provisioned all AWS infrastructure (VPC, subnets, EC2, security groups, IAM roles) using modules
- **Ansible** — configured the servers after Terraform created them (installed Java, Tomcat, Jenkins, CloudWatch agent)
- **Jenkins** — orchestrated the whole pipeline end to end
- **Maven** — built the Java application and ran unit tests
- **SonarQube** — static code analysis with a quality gate (pipeline stops if code fails)
- **Tomcat** — hosts the Java WAR file
- **CloudWatch** — collects CPU, memory, disk metrics and Tomcat logs from the app server
- **S3 + DynamoDB** — remote Terraform state with locking

---

## AWS Infrastructure

```
VPC (10.0.0.0/16)
├── Public Subnet  → Jenkins EC2
└── Private Subnet → App Server EC2 (Tomcat)
```

State is stored remotely in S3, so it's safe and shared. DynamoDB handles state locking.

---

## Pipeline Stages

1. Clone the repo from GitHub
2. Terraform init → plan → apply (provisions AWS infra)
3. Verify EC2 instances are running
4. Ansible configures the Jenkins server
5. Ansible configures the app server
6. Maven builds the WAR
7. Ansible deploys WAR to Tomcat + health check

---

## Setup

```bash
git clone https://github.com/naninetha05/CI_CD_Project
cd CI_CD_Project
```

Add AWS credentials in Jenkins (`aws-access-key-id`, `aws-secret-access-key`), update the S3 bucket name in `terraform/backend.tf`, update server IPs in `ansible/inventory.ini` after Terraform runs, then push — the pipeline handles the rest.

---

## Repo

[github.com/naninetha05/CI_CD_Project](https://github.com/naninetha05/CI_CD_Project)
