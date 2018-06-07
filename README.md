# curl samples

## User

### Authorised Admin

#### get all Users
`curl -s http://localhost:8080/rest/admin/users -u admin@gmail.com:admin`

#### create new User
`curl -s -X POST -H "Content-Type: application/json"\
 -d '{"name":"newUser","email":"newUser@yandex.ru","password":"passwordNew","roles":["ROLE_USER"]}'\
  http://localhost:8080/rest/admin/users -u admin@gmail.com:admin`

#### get User 100000
`curl -s http://localhost:8080/rest/admin/users/100000 -u admin@gmail.com:admin`

#### get User 100003 by email
`curl -s http://localhost:8080/rest/admin/users/by?email=user3@yandex.ru -u admin@gmail.com:admin`

#### update User 100000
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"name":"User1Updated","email":"user1@yandex.ru","password":"password1","roles":["ROLE_USER"]}'\
  http://localhost:8080/rest/admin/users/100000 -u admin@gmail.com:admin`
  
#### delete User 100000
`curl -s -X DELETE http://localhost:8080/rest/admin/users/100000 -u admin@gmail.com:admin`  

### Authorised User 

#### get Profile
`curl -s http://localhost:8080/rest/profile -u user3@yandex.ru:password3`

#### update Profile
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"name":"User2Updated","email":"user2@yandex.ru","password":"password2"}'\
  http://localhost:8080/rest/profile -u user2@yandex.ru:password2`
  
#### delete User 100000
`curl -s -X DELETE http://localhost:8080/rest/profile -u user3@yandex.ru:password3` 

## Restaurant

### Authorised Admin

#### get all Restaurants
`curl -s http://localhost:8080/rest/admin/restaurants -u admin@gmail.com:admin`

#### get all with today Meals (not yet)

#### create new Restaurant
`curl -s -X POST -H "Content-Type: application/json"\
 -d '{"name":"newRestaurant"}' http://localhost:8080/rest/admin/restaurants -u admin@gmail.com:admin`
 
#### get Restaurant 100004
`curl -s http://localhost:8080/rest/admin/restaurants/100004 -u admin@gmail.com:admin`

#### get Restaurant 100004 with Meals
`curl -s http://localhost:8080/rest/admin/restaurants/100004/detailed -u admin@gmail.com:admin`
 
#### update Restaurant 100004
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"name":"restaurant1Updated"}' http://localhost:8080/rest/admin/restaurants/100004 -u admin@gmail.com:admin`
  
#### delete Restaurant 100004
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100004 -u admin@gmail.com:admin`

### Authorised User 

#### get Restaurant 100004
`curl -s http://localhost:8080/rest/profile/restaurants/100004 -u user1@yandex.ru:password1`

#### get Restaurant 100004 with Meals
`curl -s http://localhost:8080/rest/profile/restaurants/100004/detailed -u user1@yandex.ru:password1`

#### get all Restaurants
`curl -s http://localhost:8080/rest/profile/restaurants -u user1@yandex.ru:password1`

## Meals

### Authorised Admin

#### get all Meals of Restaurant 100004
`curl -s http://localhost:8080/rest/admin/restaurants/100004/meals -u admin@gmail.com:admin`

#### get Meal 100007 of Restaurant 100004
`curl -s http://localhost:8080/rest/admin/restaurants/100004/meals/100007 -u admin@gmail.com:admin`

#### delete Meal 100007 of Restaurant 100004
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100004/meals/100007 -u admin@gmail.com:admin`

#### create Meal for Restaurant 100004
`curl -s -X POST -H "Content-Type: application/json"\
 -d '{"name":"newMeal","dateTime": "2018-05-01T08:00:00","price":"600"}'\
  http://localhost:8080/rest/admin/restaurants/100004/meals -u admin@gmail.com:admin`
  
#### update Meal 100007 of Restaurant 100004
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"name":"meatUpdated","dateTime": "2018-05-01T08:00:00","price":"10"}'\
  http://localhost:8080/rest/admin/restaurants/100004/meals/100007 -u admin@gmail.com:admin`
  
## Votes
In filtered queries with startDate and endDate if one of the dates not exists 
it's substituted by MIN_DATE (0-1-1) and MAX_DATE (3000-1-1) accordingly 

### Authorised Admin
 
#### get Vote 100013 of User 100000
`curl -s http://localhost:8080/rest/admin/users/100000/votes/100013 -u admin@gmail.com:admin`

#### update Vote 100013 of User 100000
Update couldn't be done after 11:00 am
 
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"date":"2018-05-01","userId":100000,"restaurantId":100005}'\
  http://localhost:8080/rest/admin/users/100000/votes/100013 -u admin@gmail.com:admin`
  
#### create Vote for User 100000
`curl -s -X POST -H "Content-Type: application/json"\
 -d '{"date":"2018-05-03","userId":100000,"restaurantId":100006}'\
  http://localhost:8080/rest/admin/users/100000/votes -u admin@gmail.com:admin`  

#### delete Vote 100013 of User 100000
`curl -s -X DELETE http://localhost:8080/rest/admin/users/100000/votes/100013 -u admin@gmail.com:admin`

#### get all Votes
`curl -s http://localhost:8080/rest/admin/votes -u admin@gmail.com:admin`

#### get all Votes between start Date and end Date
`curl -s "http://localhost:8080/rest/admin/votes/filter?startDate=2018-05-01&endDate=2018-05-01"\
 -u admin@gmail.com:admin`
 
#### get Votes by User 100000
`curl -s http://localhost:8080/rest/admin/users/100000/votes -u admin@gmail.com:admin`

#### get Votes by Restaurant 100006
`curl -s http://localhost:8080/rest/admin/restaurants/100006/votes -u admin@gmail.com:admin`

#### get Votes by User 100000 between start Date and end Date 
`curl -s "http://localhost:8080/rest/admin/users/100000/votes/filter?startDate=2018-05-01&endDate=2018-05-03"\
 -u admin@gmail.com:admin`
 
#### get Votes by Restaurant 100006 between start Date and end Date 
`curl -s "http://localhost:8080/rest/admin/restaurants/100006/votes/filter?startDate=2018-05-01&endDate=2018-05-03"\
 -u admin@gmail.com:admin`    
 
### Authorised User 

#### get Vote 100013 of Profile
`curl -s http://localhost:8080/rest/profile/votes/100013 -u user1@yandex.ru:password1`

#### update Vote 100013 of Profile
Update couldn't be done after 11:00 am
 
`curl -s -X PUT -H "Content-Type: application/json"\
 -d '{"date":"2018-05-01","userId":100000,"restaurantId":100005}'\
  http://localhost:8080/rest/profile/votes/100013 -u user1@yandex.ru:password1`  
  
#### create Vote for Profile
`curl -s -X POST -H "Content-Type: application/json"\
 -d '{"date":"2018-05-03","userId":100000,"restaurantId":100006}'\
  http://localhost:8080/rest/profile/votes -u user1@yandex.ru:password1` 
  
#### delete Vote 100013 of Profile
`curl -s -X DELETE http://localhost:8080/rest/profile/votes/100013 -u user1@yandex.ru:password1`

#### get Votes by Profile
`curl -s http://localhost:8080/rest/profile/votes -u user1@yandex.ru:password1`

#### get today Votes by Profile 
`curl -s "http://localhost:8080/rest/profile/votes/today"\
 -u user1@yandex.ru:password1`    