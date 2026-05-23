module "vpc" {
  source = "./modules/vpc"

  vpc_cidr            = "10.0.0.0/16"
  public_subnet_cidr  = "10.0.1.0/24"
  private_subnet_cidr = "10.0.2.0/24"

  public_az  = "ap-south-1a"
  private_az = "ap-south-1b"

  vpc_name            = "gojo-vpc"
  igw_name            = "gojo-igw"
  public_subnet_name  = "public-subnet"
  private_subnet_name = "private-subnet"

  public_rt_name  = "public-route-table"
  private_rt_name = "private-route-table"
}

module "security_group" {
  source = "./modules/sg"

  vpc_id       = module.vpc.vpc_id
  sg_name      = "jenkins-sg"
  ssh_port     = 22
  jenkins_port = 8080
  allowed_cidr = ["0.0.0.0/0"]
}

# Jenkins Server — public subnet
module "jenkins_ec2" {
  source = "./modules/ec2"

  instance_type     = "t3.micro"
  ami               = "ami-07a00cf47dbbc844c"
  key_name          = var.key_pair_name
  availability_zone = "ap-south-1a"
  subnet_id         = module.vpc.public_subnet_id
  security_group_id = module.security_group.security_group_id
  instance_name     = "jenkins-server"
}

# App Server — private subnet
module "app_ec2" {
  source = "./modules/ec2"

  instance_type     = "t3.micro"
  ami               = "ami-07a00cf47dbbc844c"
  key_name          = var.key_pair_name
  availability_zone = "ap-south-1b"
  subnet_id         = module.vpc.private_subnet_id
  security_group_id = module.security_group.security_group_id
  instance_name     = "app-server"
}

module "iam" {
  source = "./modules/iam"

  jenkins_instance_name = "jenkins-role"
  app_instance_name     = "app-role"
  s3_bucket_name        = var.s3_bucket_name
  dynamodb_table_name   = "terraform-state-lock"
}
