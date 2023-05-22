# ToDoApp

This is the job interview task for software developer position

## What to do
* Fork the repository
* After work is done, make a pull request and notify me by email

## Task description
You need to make a Todo app with these requirements:
1. Page for listing all the todo items
2. Todo item add and edit forms (separate pages)
3. Todo item consists of (date, item name and description)
4. Pages must interact between each other logically. (You can go from list to new or edit and when saving go back to list)

All the other specific requirements are up to you

## Technical requirements
* Use AngularJS for frontend
* For backend use Java EE
* Use any database (Postgres, Oracle, etc.)
* Make a Maven project

## Main points
* Structure your code
* Use best practises
* Use naming conventions
* Show understanding of software development concepts

## Getting Started
To install this todo app, run the following commands:

git clone https://github.com/anseri/ToDoAppAngular.git

cd ToDoAppAngular
This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

* Create todo nysql schema
* Update todo-app-server below application.properties
* spring.datasource.username=username
* spring.datasource.password=password


To run the server, cd into the todo-app-server folder and run:

./mvnw spring-boot:run
the server on http://localhost:8090

To run the client, cd into the todo-app-client folder and run:

npm install && npm start
the client run on http://localhost:3456
