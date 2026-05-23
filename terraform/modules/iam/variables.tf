variable "jenkins_instance_name" {
  description = "Name for Jenkins IAM role"
  type        = string
  default     = "jenkins-role"
}

variable "app_instance_name" {
  description = "Name for App Server IAM role"
  type        = string
  default     = "app-role"
}

variable "s3_bucket_name" {
  description = "S3 bucket name for Terraform state"
  type        = string
}

variable "dynamodb_table_name" {
  description = "DynamoDB table name for state locking"
  type        = string
  default     = "terraform-state-lock"
}
