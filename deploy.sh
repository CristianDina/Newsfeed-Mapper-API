cd Terraform_post_steps
terraform init
terraform plan -target="aws_ecr_repository.newsfeed-mapper-api-repository" -out ecr_plan.txt
terraform apply "ecr_plan.txt"

cd ..
~/.local/bin/aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin 128556667010.dkr.ecr.eu-central-1.amazonaws.com
docker build -t newsfeed-mapper-api-repository .
docker tag newsfeed-mapper-api-repository:latest 128556667010.dkr.ecr.eu-central-1.amazonaws.com/newsfeed-mapper-api-repository:latest
docker push 128556667010.dkr.ecr.eu-central-1.amazonaws.com/newsfeed-mapper-api-repository:latest

cd Terraform_post_steps
terraform apply -auto-approve
cd ..

v="~/.local/bin/aws ecs list-tasks --cluster \"default\" --output text --query taskArns[0]"
if  [ "$(eval "$v")" != "None" ]; then
    ~/.local/bin/aws ecs stop-task --task $(eval "$v") > stop.txt
fi
~/.local/bin/aws ecs run-task --cluster "default" --task-definition newsfeed-mapper-api-task > run.txt
