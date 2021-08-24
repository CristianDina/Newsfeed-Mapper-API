resource "aws_ecs_task_definition" "newsfeed-mapper-api-task" {
  family = "newsfeed-mapper-api-task"
  container_definitions = jsonencode([
    {
      name      = "newsfeed"
      image     = aws_ecr_repository.newsfeed-mapper-api-repository.repository_url
      memory    = 512
      essential = true
      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
    }
  ])
}