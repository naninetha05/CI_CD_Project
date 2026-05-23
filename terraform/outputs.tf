output "vpc_id" {
  description = "VPC ID"
  value       = module.vpc.vpc_id
}

output "public_subnet_id" {
  description = "Public subnet ID"
  value       = module.vpc.public_subnet_id
}

output "private_subnet_id" {
  description = "Private subnet ID"
  value       = module.vpc.private_subnet_id
}

output "security_group_id" {
  description = "Security Group ID"
  value       = module.security_group.security_group_id
}

output "jenkins_public_ip" {
  description = "Public IP of Jenkins server"
  value       = module.jenkins_ec2.public_ip
}

output "jenkins_instance_id" {
  description = "Instance ID of Jenkins server"
  value       = module.jenkins_ec2.instance_id
}

output "app_private_ip" {
  description = "Private IP of App server"
  value       = module.app_ec2.private_ip
}

output "app_instance_id" {
  description = "Instance ID of App server"
  value       = module.app_ec2.instance_id
}

output "jenkins_iam_role_arn" {
  description = "IAM role ARN for Jenkins EC2"
  value       = module.iam.jenkins_role_arn
}

output "app_iam_role_arn" {
  description = "IAM role ARN for App EC2"
  value       = module.iam.app_role_arn
}
