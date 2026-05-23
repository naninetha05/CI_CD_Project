terraform {
  backend "s3" {
    bucket  = "ci-cd-pipline-projeact"
    key     = "ci-cd-project/terraform.tfstate"
    region  = "ap-south-1"
    encrypt = true
  }
}
