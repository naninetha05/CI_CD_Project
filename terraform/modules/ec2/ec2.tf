resource "aws_instance" "gojo_server" {
  instance_type        = "t3.micro"
  ami                  = "ami-07a00cf47dbbc844c"
  key_name             = "project-key"
  availability_zone    = "ap-south-1b"
 
  tags = {
    Name = "gojo-jenkins"
  }
}
