# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.tackr.forum-api' is invalid and this project uses 'com.tackr.forumapi' instead.

# Getting Started

#Build path variables

-DSpring.profiles.active=prd
-DFORUM-DATABASE_URL=jdbc:mysql://localhost:3306/forum-api?useTimezone=true&serverTimezone=UTC
-DFORUM-DATABASE.USER=root
-DFORUM-DATABASE_PASSWORD=senha
-DFORUM-JWT_SECRET_PASSWORD=$2y$12$.HQhp2nvZ50pxX4zPIOl6uK6FU.0bkKV.5JDtK0uqShsxN2vJN1A6
-DFORUM-SPRING_ADMIN_CLIENT_URL=http://localhost:8081

#Running jar
1. Execute command below on your local terminal:

java -jar forum-api -p 8080:8080 -e -SPRING_PROFILE_ACTIVE=prd -e DFORUM-DATABASE_URL=jdbc:mysql://docker-mysql:3306/forum-api?useTimezone=true&serverTimezone=UTC -e DFORUM-DATABASE.USER=root DFORUM-DATABASE_PASSWORD=senha -e DFORUM-JWT_SECRET_PASSWORD=123456 -e DFORUM-SPRING_ADMIN_CLIENT_URL=http://localhost:8081