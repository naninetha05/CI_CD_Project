variable "vpc_id" {
  description = "VPC ID where security group will be created"
  type        = string
}

variable "sg_name" {
  description = "Security group name"
  type        = string
  default     = "jenkins-sg"
}

variable "sg_description" {
  description = "Security group description"
  type        = string
  default     = "Security group for Jenkins server"
}

variable "ssh_port" {
  description = "SSH port"
  type        = number
  default     = 22
}

variable "jenkins_port" {
  description = "Jenkins port"
  type        = number
  default     = 8080
}

variable "allowed_cidr" {
  description = "CIDR blocks allowed for ingress"
  type        = list(string)
  default     = ["0.0.0.0/0"]
}
