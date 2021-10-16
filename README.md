#Yarovenko_AG_Java_2021_Liga
 
[![Docker](https://d1.awsstatic.com/acs/characters/Logos/Docker-Logo_Horizontel_279x131.b8a5c41e56b77706656d61080f6a0217a3ba356d.png)](https://www.docker.com/)

 _Console command for run container postgreSQL 13.3 version_ 

~~~shell
docker run --name=psql-home-work-v13.3 -p 5432:5432 -e POSTGRES_USER=stuff -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=school -d postgres:13.3
~~~

_or  run docker-compose.yml_

## _Rest Api Social Network_

## Users
Get: /api/users/{id}  
PathVariable id - user. Show current user social network  

Get: /api/users  
Show all users social network  

Post: /api/users  
RequestBody user. Add new user  

Put: /api/users  
RequestBody user. update user  

Delete: /api//users/{id}  
PathVariable user id. Delete current user  

Get: /api/users-post/{id}  
PathVariable user id. Show all posts current user  

## School
Get: /api/schools/{id}  
PathVariable id - school. Show current schools  

Get: /api/schools  
show all schools social network  

Post: /api/schools  
RequestBody school. Add new school  

Put: /api/schools  
RequestBody school. Update school  

Delete: /api/schools/{id}  
PathVariable id - school. Delete current school  

## Posts user
Get: /api/posts/{id}  
PathVariable id - post. Show current post  

Get: /api/posts  
show all posts social network  

Post: /api/posts  
RequestParam id - user id, RequestParam content - text. Add user post, return json post  

Put: /api/posts  
RequestParam id - user id, RequestParam content - text. Update user post, return json post  

Delete: /api/posts/{id}  
PathVariable id - id post. Delete current post  

## Friends
Get: /api/friends/{id}  
PathVariable id - user id. Show current user friends  
Post: /api/add  
PathVariable idUser - user id, RequestBody idFriend - user id. Add friend for user  
Delete: /api/delete  
RequestParam idUser - user id, RequestParam idFriend - user id. Delete user from friend
