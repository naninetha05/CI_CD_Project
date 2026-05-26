# AWS DevOps CI/CD Platform

A production-style DevOps project demonstrating end-to-end CI/CD automation for a Java web application on AWS — built without Docker or Kubernetes, using the toolchain that many enterprises still run today.

---

## Key Features

- **Infrastructure as Code** — all AWS resources provisioned via modular Terraform
- **Configuration Management** — servers configured automatically via Ansible playbooks
- **CI/CD Automation** — GitHub webhook triggers Jenkins pipeline on every push
- **Code Quality Gate** — SonarQube static analysis blocks deployment on quality failures
- **Zero Manual Steps** — from code push to live deployment, everything is automated
- **Remote State Management** — Terraform state stored in S3 with encryption
- **Least Privilege IAM** — EC2 instances use IAM roles, no hardcoded credentials

---

## CI/CD Workflow

```
Developer pushes code
        │
        ▼
    GitHub
        │  webhook
        ▼
  Jenkins Pipeline
        │
        ├── 1. Checkout source code
        ├── 2. Terraform init / plan / apply  →  AWS infrastructure
        ├── 3. Verify EC2 instances running
        ├── 4. Ansible → configure Jenkins server
        ├── 5. Ansible → configure app server (Tomcat)
        ├── 6. Maven build → WAR artifact
        ├── 7. SonarQube analysis + quality gate
        └── 8. Ansible → deploy WAR to Tomcat + health check
                │
                ▼
        Application live on AWS
```

---

## AWS Infrastructure

```
AWS Region: ap-south-1 (Mumbai)
│
└── VPC (10.0.0.0/16)
    ├── Public Subnet (10.0.1.0/24)
    │   ├── Jenkins EC2 (t3.micro)  ← CI/CD server
    │   └── App Server EC2 (t3.micro) ← Tomcat deployment target
    ├── Internet Gateway
    └── Security Groups (port 22, 8080)
```

- Terraform remote state stored in S3 with encryption enabled
- DynamoDB used for state locking
- IAM roles attached to EC2 instances — no access keys on servers

---

## Tech Stack

| Layer | Tool |
|---|---|
| Cloud Platform | AWS (EC2, VPC, IAM, S3, CloudWatch) |
| Infrastructure as Code | Terraform (modular structure) |
| Configuration Management | Ansible |
| CI/CD Orchestration | Jenkins |
| Build Tool | Maven |
| Code Quality | SonarQube |
| Application Server | Apache Tomcat 9 |
| Application Language | Java 11 |
| Version Control | Git + GitHub |

---

## Project Structure

```
CI_CD_Project/
│
├── Jenkinsfile                          # Declarative pipeline (9 stages)
├── pom.xml                              # Maven build + SonarQube config
│
├── src/
│   ├── main/
│   │   ├── java/com/cicdproject/        # HelloServlet.java
│   │   └── webapp/WEB-INF/web.xml       # Servlet descriptor
│   └── test/
│       └── java/com/cicdproject/        # HelloServletTest.java (Mockito)
│
├── terraform/
│   ├── main.tf                          # Module orchestration
│   ├── variables.tf
│   ├── outputs.tf
│   ├── backend.tf                       # S3 remote state
│   ├── terraform.tfvars
│   └── modules/
│       ├── vpc/                         # VPC, subnets, IGW, route tables
│       ├── sg/                          # Security groups
│       ├── ec2/                         # EC2 instances
│       └── iam/                         # IAM roles and policies
│
├── ansible/
│   ├── inventory.ini                    # Host definitions
│   ├── jenkins-playbook.yml             # Jenkins server setup
│   ├── app-playbook.yml                 # Tomcat + CloudWatch setup
│   └── deploy-playbook.yml              # WAR deployment + health check
│
└── .gitignore
```

---

## Setup

**Prerequisites:**
- AWS account with programmatic access
- EC2 key pair created in `ap-south-1`
- S3 bucket for Terraform remote state
- Jenkins installed and running

**Steps:**

```bash
# 1. Clone the repository
git clone https://github.com/naninetha05/CI_CD_Project
cd CI_CD_Project

# 2. Configure Terraform backend
# Edit terraform/backend.tf — set your S3 bucket name

# 3. Add AWS credentials in Jenkins
# Manage Jenkins → Credentials → Add:
#   aws-access-key-id
#   aws-secret-access-key

# 4. Configure GitHub webhook
# Repo Settings → Webhooks → http://<jenkins-ip>:8080/github-webhook/

# 5. Push code — pipeline triggers automatically
git push origin main
```

---

## Screenshots

> Recommended screenshots for portfolio/LinkedIn:
> - Jenkins pipeline stage view (all stages green)
> - SonarQube dashboard showing quality gate passed
> - Running application in browser
> - AWS EC2 instances list
> - Terraform apply output

---

## Repository

[github.com/naninetha05/CI_CD_Project](https://github.com/naninetha05/CI_CD_Project)
