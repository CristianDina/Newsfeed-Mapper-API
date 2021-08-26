resource "aws_instance" "newsfeed-mapper-api-ec2instance" {
  ami           = "ami-0102ef3da1a6c47ca"
  instance_type = "t2.micro"
  iam_instance_profile = aws_iam_instance_profile.newsfeed-mapper-api-iam_instance.name
  vpc_security_group_ids = ["sg-0e3449ac9bda66de9"]
  key_name = "jen.pem"
}

resource "aws_iam_instance_profile" "newsfeed-mapper-api-iam_instance" {
  name = "newsfeed-mapper-api-iam_instance"
  role = aws_iam_role.newsfeed-mapper-api-role.name
}