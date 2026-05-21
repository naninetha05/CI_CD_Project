variable "vpc_cidr" {
  description = "CIDR block for VPC"
  type        = string
}

variable "public_subnet_cidr" {
  description = "CIDR block for public subnet"
  type        = string
}

variable "private_subnet_cidr" {
  description = "CIDR block for private subnet"
  type        = string
}

variable "public_az" {
  description = "Availability zone for public subnet"
  type        = string
}

variable "private_az" {
  description = "Availability zone for private subnet"
  type        = string
}

variable "vpc_name" {
  description = "VPC name"
  type        = string
}

variable "igw_name" {
  description = "Internet Gateway name"
  type        = string
}

variable "public_subnet_name" {
  description = "Public subnet name"
  type        = string
}

variable "private_subnet_name" {
  description = "Private subnet name"
  type        = string
}

variable "public_rt_name" {
  description = "Public route table name"
  type        = string
}

variable "private_rt_name" {
  description = "Private route table name"
  type        = string
}