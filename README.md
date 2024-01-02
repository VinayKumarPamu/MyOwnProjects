BANKING APPLICATION 

1. Account Registration:
● Users wishing to register are prompted to enter their personal details.
● Function responsible: accountCreation from user_service.java
● The application validates the entered details using various functions.
● Function references: Multiple validation functions from validations.java
● Validated details are then saved into the application's database.
● Function responsible: insert_data from db_operations.java
● To verify the user's email address, an OTP is sent for confirmation.
● Function responsible: mailOtpVerification from mail_operations.java
● The user must verify the OTP before proceeding.

3. Account Login:
● Existing users can log in by entering their registered email.
● Function responsible: login (within app.java)

Upon Successful Login:
● Users gain access to various banking services, including:
● Displaying Account Balance: Users can view the current balance in their
account.
● Function responsible: displayBalance from banking_service.java
● Depositing Money: Users can add funds to their account.
● Function responsible: depositMoney from banking_service.java
● Withdrawing Money: Users can deduct funds from their account.
● Function responsible: withdrawlMoney from banking_service.java
● Transferring Money: Users can send funds to another registered user.
● Function responsible: transferMoney from banking_service.java
● Viewing Transaction History: Users can review their past transactions.
● Function responsible: transactionHistory from banking_service.java
● Users can also choose to log out, taking them back to the main menu.

3. Forgot Password:
● Users who've forgotten their password can initiate the recovery process by
entering their registered email.
● For additional security, they're prompted to enter their Date of Birth for
verification.
● If the entered Date of Birth matches the database's records, an OTP is sent to the
user's email for further verification.
● Function responsible: mailOtpVerification from mail_operations.java
● Upon successful OTP verification, users can reset their password. If the
verification fails, users are redirected to the main menu.

4.Exit the Application:
● Users can choose to exit the application, upon which an exit banner is displayed,
and the application terminates.
● Function responsible: exit_banner_printing from banner_printing.java
