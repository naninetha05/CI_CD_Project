variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "ap-south-1"
}

variable "key_pair_name" {
  description = "Key pair name for EC2 instances"
  type        = string
}

variable "s3_bucket_name" {
  description = "S3 bucket name for Terraform remote state"
  type        = string
}
