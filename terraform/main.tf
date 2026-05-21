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