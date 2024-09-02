resource "aws_s3_bucket" "my-s3-bucket" {
  bucket = "aws-example-bucket-terraform"

  tags = {
    Name        = "My bucket"
    Environment = "Dev"
  }
}