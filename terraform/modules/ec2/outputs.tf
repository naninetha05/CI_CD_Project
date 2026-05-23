output "public_ip" {
  description = "Public IP address of the EC2 instance"
  value       = aws_instance.gojo_server.public_ip
}

output "private_ip" {
  description = "Private IP address of the EC2 instance"
  value       = aws_instance.gojo_server.private_ip
}

output "instance_id" {
  description = "EC2 instance ID"
  value       = aws_instance.gojo_server.id
}

output "instance_arn" {
  description = "ARN of the EC2 instance"
  value       = aws_instance.gojo_server.arn
}

output "availability_zone" {
  description = "Availability zone of the instance"
  value       = aws_instance.gojo_server.availability_zone
}

output "instance_state" {
  description = "Current state of the instance"
  value       = aws_instance.gojo_server.instance_state
}
