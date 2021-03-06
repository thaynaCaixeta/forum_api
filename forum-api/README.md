# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.tackr.forum-api' is invalid and this project uses 'com.tackr.forumapi' instead.

# Getting Started

#Build path variables dev environment

-DSpring.profiles.active=prd
-DCLEARDB_DATABASE_URL=jdbc:mysql://us-cdbr-east-03.cleardb.com:3306/heroku_a135dc6426f8395
-DCLEARDB_DATABASE_USER=b32e60c3eb8108
-DCLEARDB_DATABASE_PASSWORD=37cc8493
-DFORUM-JWT_SECRET_PASSWORD=$2y$12$.HQhp2nvZ50pxX4zPIOl6uK6FU.0bkKV.5JDtK0uqShsxN2vJN1A6
-DServer.port:8080