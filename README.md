# ToDoApp

This is the job interview task for software developer position

## How to run the TODO App with docker
1. clone the project
2. if docker is not installed need to install docker
3. go to frontend project dir and run below command:
>  docker build -t frontend-app .
4. go to backend dir and run below command:
>  docker build -t todo-backend-app .
5. then run below command step by step in terminal:
>  docker network create todo-network
>  docker run --network todo-network --name pgdb -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todo -d postgres
>  docker run --network todo-network --name todo-backend-app -d -p 9090:9090 -e "db_url=pgdb"   todo-backend-app
>  docker run  --network todo-network --name frontend-app -d -p 4200:80 frontend-app
6. then go to browser and hit http://localhost:4200/
