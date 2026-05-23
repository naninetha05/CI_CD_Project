# AWS DevOps CI/CD Project

A production-style DevOps project where I built a complete CI/CD pipeline to deploy a Java web application on AWS — without Docker or Kubernetes.

---

## What I Built

A developer pushes code to GitHub → GitHub webhook triggers Jenkins → full pipeline runs automatically → app is live on AWS.

---

## Pipeline Stages

1. Clone repo from GitHub
2. Terraform init → plan → apply (provisions AWS infrastructure)
3. Verify EC2 instances are running
4. Ansible configures Jenkins server
5. Ansible configures app server (Tomcat)
6. Maven builds the WAR artifact
7. Ansible deploys WAR to Tomcat + health check

---

## AWS Infrastructure

```
VPC (10.0.0.0/16) — ap-south-1
├── Public Subnet → Jenkins EC2  (EIP: 13.207.70.94)
└── Public Subnet → App Server EC2 (EIP: 13.207.110.172)
```

- Remote state stored in S3 (`ci-cd-pipline-projeact`)
- IAM roles attached to both EC2s (no hardcoded credentials)
- Security groups for SSH and port 8080

---

## Tech Stack

| Layer | Tool |
|---|---|
| Cloud | AWS (EC2, VPC, IAM, S3, CloudWatch) |
| Infrastructure as Code | Terraform (modular) |
| Configuration Management | Ansible |
| CI/CD | Jenkins |
| Build | Maven |
| App Server | Apache Tomcat 9 |
| Language | Java 11 |
| Version Control | Git + GitHub |

---

## Project Structure

```
CI_CD_Project/
├── Jenkinsfile                          # 9-stage pipeline
├── pom.xml                              # Maven build config
├── src/
│   ├── main/java/com/cicdproject/       # HelloServlet.java
│   └── main/webapp/WEB-INF/web.xml      # Servlet config
├── terraform/
│   ├── main.tf                          # VPC, EC2, SG, IAM modules
│   ├── backend.tf                       # S3 remote state
│   └── modules/vpc, sg, ec2, iam/
├── ansible/
│   ├── inventory.ini                    # Server IPs
│   ├── jenkins-playbook.yml             # Jenkins server setup
│   ├── app-playbook.yml                 # Tomcat server setup
│   └── deploy-playbook.yml              # WAR deployment
└── README.md
```

---

## App

Live at: `http://13.207.110.172:8080/app/`

---

## Setup

```bash
git clone https://github.com/naninetha05/CI_CD_Project
cd CI_CD_Project
```

Add AWS credentials in Jenkins (`aws-access-key-id`, `aws-secret-access-key`), then push — webhook triggers the pipeline automatically.

---

## Repo

[github.com/naninetha05/CI_CD_Project](https://github.com/naninetha05/CI_CD_Project)
