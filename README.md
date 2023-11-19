# Twitter Clone

- Using jakarta servlets and jsp
- sqlite3 database
- Java 17
- maven >=3.9
- tailwindcss for jsp styling
- apache tomcat - 10.1.15
- Apache NetBeans IDE (v19)
 
## Tasks remaining

1. Write a TweetModal saving, reading all tweets, findOne tweet, deleting tweet
2. Write a FollowerModal for following and unfollowing
3. Write a NotificationModal for creating notifications
4. Add a WebFilter for authorizating protected routes, right now authorizing is completely done inside the servlet
5. Password hashing has to be implemented
6. Error flags should appear in jsp

## Project structure

- Project follows a simplifiied MVC pattern
- Servlets acts as both views and controllers
- Models as acts DTOs and also controllers, since they do database writing and reading
- controller logic is distributed 50% to servlets and 50% to models


- `src/main/java/com/twitter/controllers` -> contains all servlets
- `src/main/java/com/twitter/models` -> contains all models
- `src/main/java/com/twitter/webapp/pages` -> contains all jsp pages
- `src/main/java/com/twitter/webapp/static` -> contains css files and images
- `src/main/java/com/twitter/utils` -> contains utility functions required for servlets
- `src/main/java/com/twitter/db` -> contains classes for managing database connections

- `src/main/webapp/META-INF` -> contains context.xml, which is being used for establishing DATABASE connection
- `src/main/webapp/WEB-INF` -> contains web.xml, which is deployment descriptor

## Database Schema

1. Project root contains `schema.sql` which contains entire schema of this project
2. this project has following database entities
	
	- User
	- Tweet
	- Follower
	- Notification
	- Comments

3. User performs following actions
	
	- creating a tweet
	- reading tweets
	- deleting his/her tweets
	- commenting on other tweets
	- deleting his/her comments
	- Following and unfollowing other users

4. Application will perform following actions
	
	- Notifying followers of a user, about his/her tweets
	- Notifying a user, about the comments he got for a tweet

5. Changes in the schemas can be done as per the requirements
6. Sessions schema has to be added, for persisting the user sessions to the database


## Authentication Procedure

1. Authentication is HttpSession based
2. Once a user is logged in, users details are added to the session
3. As long as user sends the session cookie back to the server with valid details, all requests will be served

### To be implemented

1. remembering user session id by saving to database
2. Adding WebFilters for authorizing protected servlets




