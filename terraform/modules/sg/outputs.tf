output "security_group_id" {
  description = "Security group ID"
  value       = aws_security_group.jenkins_sg.id
}

output "security_group_name" {
  description = "Security group name"
  value       = aws_security_group.jenkins_sg.name
}

output "security_group_vpc_id" {
  description = "VPC ID where security group is attached"
  value       = aws_security_group.jenkins_sg.vpc_id
}

output "security_group_arn" {
  description = "ARN of the security group"
  value       = aws_security_group.jenkins_sg.arn
}
