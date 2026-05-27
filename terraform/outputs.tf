output "jenkins_public_ip" {
  value = module.jenkins_ec2.public_ip
}

output "app_public_ip" {
  value = module.app_ec2.public_ip
}

output "app_private_ip" {
  value = module.app_ec2.private_ip
}

output "vpc_id" {
  value = module.vpc.vpc_id
}
