I have implemented all the functionalities which were mentioned in the provided assignment 
SECURITY :-
I have implemented JWT Authentication in my application. So all users only allow to access the SIGNUP and LOGIN API
for SIGNUP :- API => localhost:8080/User/signUp 
              Method => POST
              JSON => {
    "username":"Sahil",
    "email":"sahil@gmail.com",
     "password":"sahil#12354",
     "mob":"7421430231",
     "address":"House no.5 near FD hospital"
}

Hit this api on postman with given input (you can change the values) this will endup with the message "User Sahil added successfully" with status code 200 OK
 
