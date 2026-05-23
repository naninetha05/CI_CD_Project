resource "aws_security_group" "jenkins_sg" {
  name        = var.sg_name
  description = var.sg_description
  vpc_id      = var.vpc_id

  # SSH ACCESS
  ingress {
    description = "SSH access"
    from_port   = var.ssh_port
    to_port     = var.ssh_port
    protocol    = "tcp"

    cidr_blocks = var.allowed_cidr
  }

  # JENKINS 
  ingress {
    description = "Jenkins"
    from_port   = var.jenkins_port
    to_port     = var.jenkins_port
    protocol    = "tcp"

    cidr_blocks = var.allowed_cidr
  }

  # HTTP
  ingress {
    description = "HTTP access"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"

    cidr_blocks = var.allowed_cidr
  }

  # HTTPS
  ingress {
    description = "HTTPS access"
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"

    cidr_blocks = var.allowed_cidr
  }

  # OUTBOUND
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"

    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = var.sg_name
  }
}
