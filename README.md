I have implemented all the functionalities which were mentioned in the provided assignment 
SECURITY :-
I have implemented JWT Authentication in my application. So all users only allow to access the SIGNUP and LOGIN API
1) for SIGNUP :- API => localhost:8080/User/signUp 
              Method => POST
              JSON => {
    "username":"Sahil",
    "email":"sahil@gmail.com",
     "password":"sahil#12354",
     "mob":"7421430231",
     "address":"House no.5 near FD hospital"
}

Hit this api on postman with given input (you can change the values) this will endup with the message "User Sahil added successfully" with status code 200 OK

2) For LOGIN :- API => localhost:8080/User/login
            MEthod => POST
            JSON => {
 "email":"Kethali@gmail.com",
  "password":"kethi#12354"
}

By hitting this api the user will login to the application by checking if the user is authenticated or not. This will return the JWT token which we have to pass in the header
for accessing the private apis.

ACCESS PRIVATE APIs
After login the user will get the JWT token pass this token to Bearer token bracket in authentication option in postman. Application will access this token and allow the authorized
user to access the private apis . if the user is not authorized it will through the error(Bad credential) or if token is not passed then also throws the exception access denied.

COMMON STEP :- Add the JWT token to (Auth -> Type -> Bearer Token -> Token) header in postman.
A) USER API

1) Get user by user name
This api takes the user name and returns the user details
API =>localhost:8080/User/getUser?username=Mina
METHOD =>GET

OUTPUT => It will return the user details with status code "OK" if api hits successfully. But if the user of provided user name is not exist in the database it will throw an exception "user not found".

B) PRODUCT API
  1) Create new product
     This Api will create the new product as up now i am not given the role based authentication this  
     api will be access by the regular user also.
     API => localhost:8080/Product/newProduct
     METHOD=>POST
     JSON BODY=>{
    "productName":"Pants" ,
    "description": "Child wear",
     "price": 5000,
     "category": "Clothing",
     "quantity":1000
}

OUTPUT=> It will return the added product with status code "Created" 201.

2)Get All Products
This will return all the product present in the product database
API =>localhost:8080/Product/getAllProduct
METHOD=>GET
OUTPUT=> return the list of products with status code 200 "OK"

3)Get Product By given Product_id
This will return product of given product id from the product database
API =>localhost:8080/Product/getById?id=4   (replace 4 by the id of the product you want to get)
METHOD=>GET
OUTPUT=> return the list of products with status code 200 "OK". If the product is not present throws an exception "Invalid product id".


C) WISHLIST API

  1) Add Product to the WishList of the User
     This method will add the Product (of given product id) to the wishlist of User (of given user id).
     API=>localhost:8080/WishList/addProduct
     METHOD => POST
     OUTPUT => return status code 200 "OK" and "Product added successfully" if the api hits successfully.
     Otherwise throws exception "Invalid user id" or "Invalid product id". If product is already addeded 
     it will return "Product already exist".

  2) Get products users wishlist
    This will return all the products present in users wishlist.
   API =>localhost:8080/WishList/get_User_Wishlist?userId=3
   METHOD => GET
   OUTPUT => It will return list of products present in the users wishlist with status code 200 
   "OK".Otherwise throws an exception "Invalid user id".

  3) Delete Product from user wishlist
     This will delete the (provided product id) product from the wishlist of (provided user id) user.
     ADD=>localhost:8080/WishList/get_User_Wishlist?userId=3
     METHOD => DELETE
     OUTPUT => returns "removed product successfully" for valid user and product id otherwise throws exception. If user and product id are valid but product is not present in the wishlist of user it will return "product not exist in wishlist".


**IMPORTANT**
1)My database will have the 4 Tables.
  I)user_table (contains details of user)
  II)wishlist  (contains wishlist id and user id details)
  III)wishlist_product (contains details of wishlist and products present in it)
  IV) product (contains details of products)
  
this is beacause I have created the relationship between the USER and WISHLIST entity(one-to-one) and WISHLIST and PRODUCT entity(many-to-many) 
2) I have written Junit Test cases for WishListService and WishListController layer.

      



