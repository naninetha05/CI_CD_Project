resource "aws_instance" "gojo_server" {
  instance_type           = var.instance_type
  ami                     = var.ami
  key_name                = var.key_name
  availability_zone       = var.availability_zone
  subnet_id               = var.subnet_id
  vpc_security_group_ids  = [var.security_group_id]

  tags = {
    Name = var.instance_name
  }
}

