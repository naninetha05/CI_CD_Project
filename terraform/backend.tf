terraform {
  backend "s3" {
    bucket         = "your-terraform-state-bucket"
    key            = "ci-cd-project/terraform.tfstate"
    region         = "ap-south-1"
    encrypt        = true
    dynamodb_table = "terraform-state-lock"
  }
}
