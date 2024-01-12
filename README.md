# User-Service (Spring Boot Microservice)

## 1. Project Title
UserManagement

## 2. Objective/Purpose
This Spring Boot Microservice, UserManagement, is designed to handle all user-related services in an enterprise application. It encompasses functionalities such as SignUp, SignIn, Forgot Password, Registration Email, Forgot Password Email, Update Profile and Address, and User Groups. The service is built with a focus on Authentication and Authorization concepts.

## 3. Background
The UserManagement service is a fundamental component for any enterprise application, addressing core aspects of user management. It provides features for user registration and login, profile details and updates, as well as roles and authorizations. Starting with such a familiar application serves as a solid foundation for understanding and implementing authentication and authorization concepts.

## 4. Project Scope
- SignUp
- Login
- Logout
- Forgot Password
- GetProfile
- UpdateProfile
- GetUserAddress

## 5. Functional Requirements
- **User Signup:** Allows users to register for the application.
- **User Login:** Enables users to log in securely.
- **Forgot Password:** Provides a mechanism for users to reset their passwords if forgotten.
- **GetProfileByUserName:** Retrieves the user profile based on the username.
- **Update Profile:** Allows users to update their profile information.
- **GetAddressByUsername:** Retrieves the user's address information.

## 6. Authentication
The UserManagement service implements **ROLE-based authentication**, ensuring that users have appropriate roles and authorizations for accessing different functionalities. This ensures a secure and controlled user experience.


The service will be accessible at `http://localhost:8081`.

Feel free to customize the service based on your specific requirements and use case. For more detailed information on endpoints, configurations, and usage, refer to the project documentation and code comments.
