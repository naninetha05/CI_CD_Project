output "jenkins_role_arn" {
  description = "ARN of Jenkins IAM role"
  value       = aws_iam_role.jenkins_role.arn
}

output "jenkins_instance_profile_name" {
  description = "Instance profile name for Jenkins EC2"
  value       = aws_iam_instance_profile.jenkins_profile.name
}

output "app_role_arn" {
  description = "ARN of App Server IAM role"
  value       = aws_iam_role.app_role.arn
}

output "app_instance_profile_name" {
  description = "Instance profile name for App Server EC2"
  value       = aws_iam_instance_profile.app_profile.name
}
