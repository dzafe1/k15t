# Description
Project used several new dependencies 
* spring-boot-starter-data-jpa - used for extending UserRepository and for easier interaction with the database 
* h2 - used for creation in-memory database in order to save registered users
* spring-boot-starter-test & spring-test - used for creation of unit tests
* joda-time - used in User model for creation of *dateCreated* field
* spring-boot-devtools - used for easier developing spring boot application
 
## RegistrationResource 
Autowired annotation is used in order to use methods in *UserService*
I changed *save* method. Method receives *User* model, *BindingResult* and *HttpSession* as well. 
User model needs to be sent in corresponding format, which is defined by annotations in *User* model.
*BindingResult* is used to check if there are some errors in data which is sent by *registration.vm* file.
If there are some errors, *bindingResult* is saved into session object and sent to route which returns view *registration.vm*.
Also, it sends back *User* object to that route so that form fields can be populated with user's data.
There is also if statement which checks does email already exists in the database. If yes, error message is sent back to the *registration.vm*.
If validation goes well, user is then inserted in the database and returned to the *registration.vm*, so the data can be shown
as requested in task.

## UserService
Logger is used to log all activities.
  * createUserForRegistration - Method creates new user who registered for Java meetup
  * checkIfUserExists - Method checks does user exists with given email
  
I also implemented additional methods in order to show my knowledge
 * getAllRegisteredUsers - Method gets all registered users
 * getRegisteredUserById - Method gets registered user by id, it throws ItemNotFoundException if not found
 * deleteRegisteredUserById - Deletes registered user, it also throws ItemNotFoundException if user was not found

## User
Class which represents table in database.
 * id - represents id of record in database
 * dateCreated - represents creation date of registration.
 * name - represents user's name. Mandatory field, of maximum length of 50. Only letters could be inserted.
 * password - represents user's password. Mandatory field, of maximum length of 255. This was stated this way because password usually encoded.
 * address - represents user's address. Mandatory field, of maximum length of 50.
 * email - represents user's email. Mandatory field, of maximum length of 50. This field is unique.
 * phone - represents user's phone. Size can be maximum 30 characters.

## Exceptions
* ItemFoundException - thrown when email already exists in database.
* ItemNotFoundException - thrown when user is not found in database.

## UserRepository
Extends JpaRepository to inherit base JpaRepository methods for CRUD operation on the User.

## RegistrationController
It was modified so it can take data from session which is sent by *RegistrationResource* and send the data to *registration.vm* template.

## Templates
Error template was added to change whitelabel error page. Registration template was extended to show validation errors and registration form. 


## About project setup
Project will create in memory database upon start. Database will be created every time on start, deleting older version of database.
If you want to change this, open *application.properties* file and change *spring.jpa.hibernate.ddl-auto* to *update* or leave it blank.
In order to run tests successfully, you need firstly run *spring-boot* application.
Test coverage is **100%** on *UserService*.

### Additional notes
I also know how to use **DAO** and **DTO** classes. I used them in my previous work with Spring,
but in this case I have decided that it would not be necessary because *User* entity (class) is small.