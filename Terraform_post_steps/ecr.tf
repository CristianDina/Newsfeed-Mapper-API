resource "aws_ecr_repository" "newsfeed-mapper-api-repository" {
  name                 = "newsfeed-mapper-api-repository"
  image_tag_mutability = "MUTABLE"

  image_scanning_configuration {
    scan_on_push = true
  }
}