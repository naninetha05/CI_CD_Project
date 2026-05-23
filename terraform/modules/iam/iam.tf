resource "aws_iam_role" "jenkins_role" {
  name = var.jenkins_instance_name

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action    = "sts:AssumeRole"
        Effect    = "Allow"
        Principal = { Service = "ec2.amazonaws.com" }
      }
    ]
  })
}

resource "aws_iam_instance_profile" "jenkins_profile" {
  name = "${var.jenkins_instance_name}-profile"
  role = aws_iam_role.jenkins_role.name
}

resource "aws_iam_role_policy" "jenkins_s3_policy" {
  name = "jenkins-s3-state-policy"
  role = aws_iam_role.jenkins_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect = "Allow"
        Action = ["s3:GetObject", "s3:PutObject", "s3:DeleteObject", "s3:ListBucket"]
        Resource = [
          "arn:aws:s3:::${var.s3_bucket_name}",
          "arn:aws:s3:::${var.s3_bucket_name}/*"
        ]
      }
    ]
  })
}

resource "aws_iam_role_policy" "jenkins_dynamodb_policy" {
  name = "jenkins-dynamodb-lock-policy"
  role = aws_iam_role.jenkins_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = ["dynamodb:PutItem", "dynamodb:GetItem", "dynamodb:DeleteItem", "dynamodb:DescribeTable"]
        Resource = "arn:aws:dynamodb:*:*:table/${var.dynamodb_table_name}"
      }
    ]
  })
}

resource "aws_iam_role_policy" "jenkins_terraform_policy" {
  name = "jenkins-terraform-policy"
  role = aws_iam_role.jenkins_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = ["ec2:*", "vpc:*", "iam:*", "s3:*", "cloudwatch:*", "logs:*"]
        Resource = "*"
      }
    ]
  })
}

# App Server Role
resource "aws_iam_role" "app_role" {
  name = var.app_instance_name

  assume_role_policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Action    = "sts:AssumeRole"
        Effect    = "Allow"
        Principal = { Service = "ec2.amazonaws.com" }
      }
    ]
  })
}

resource "aws_iam_instance_profile" "app_profile" {
  name = "${var.app_instance_name}-profile"
  role = aws_iam_role.app_role.name
}

resource "aws_iam_role_policy" "app_cloudwatch_policy" {
  name = "app-cloudwatch-policy"
  role = aws_iam_role.app_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = ["cloudwatch:PutMetricData", "logs:PutLogEvents", "logs:CreateLogGroup", "logs:CreateLogStream"]
        Resource = "*"
      }
    ]
  })
}

resource "aws_iam_role_policy" "app_s3_policy" {
  name = "app-s3-policy"
  role = aws_iam_role.app_role.id

  policy = jsonencode({
    Version = "2012-10-17"
    Statement = [
      {
        Effect   = "Allow"
        Action   = ["s3:GetObject", "s3:ListBucket"]
        Resource = [
          "arn:aws:s3:::${var.s3_bucket_name}",
          "arn:aws:s3:::${var.s3_bucket_name}/*"
        ]
      }
    ]
  })
}
