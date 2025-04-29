**This is our mobile payment systems appliction.**

**LCOM4 & Relatedness**

NotificationController – LCOM4 = 1, there are 2 methods that access notifications. 
NotificationModel – LCOM4 = 1, multiple methods are independent, but one method accesses multiple classes.
NotificaitonView – LCOM4 = 2, the method of showNotification only takes in notification.
PaymentManagementController – LCOM4 = 2, multiple methods pointing to their own attributes
PaymentManagementModel – LCOM4 = 1, Multiple methods relating to one attribute and multiple attributes relating to one method.
PaymentManagementView – LCOM4 = 0, no methods at this time.
TransactionManagementController – LCOM4 = 1 method accessing multiple attributes.
TransactionManagementModel – LCOM4 = 2, multiple methods accessing multiple attributes.
TransactionManagementView – LCOM4 = 0, no methods or attributes at this time.
UserAuthentication – LCOM4 = 1, multiple attributes accessing multiple methods
UserManagementController – LCOM4 = 0, multiple methods only accessing a single attribute inside the method.
UserManagementModel – LCOM4 = 2, each attribute is accessed by one method
UserManagementView – LCOM4 = 2, each attribute is accessed by one method
Team Member ID + Name	Contribution in The Particular Assignment


**M02 - A03**
We added methods to each of the different classes and made sure that there were stubs that were
ready for testing in them. We then created a testHarness class where we called the methods from the 
controllers and made sure that our API design was working. The testHarness class works as it should
and we instantiate the controllers which in turn instantiate more. 


Ethan Snyder: 1 Helped build out modules, javadocs and tests
August Wolf: 2 Helped build out modules, javadocs and tests
Zach Carson: 3	Helped build out modules, javadocs and tests
Juan Oliver: 4	Helped build out modules, javadocs and tests

**M03-A04**
Ethan Snyder: Created Notification Factory, created Notification UI Design Pattern. Redesigned the GUI for the offer and main page. Contributed to send offer use case.

August Wolf 997519799: Created Factory backend for the link bank account use case 
Created a progressive disclosure design pattern for linking bank account
Classes: Profile View, BankLinkerFactory, and ChaseBankLinker
Interface: BankLinker Interface
When you run, run the ProfileView class and it will populate a GUI.

Zach Carson: Created a "Main Menu" that appears after the user logs in with the UserLogin class. Gives the choice to go to the Business Dashboard or the Social Feed. Basically connected the two main use cases so there's a flow between them. 
Classes: UserLogin, App

Juan Oliver: 
Created the User to User Payment use case using the Singleton design pattern. 
Created the payment activity UI using the Wizard UI design pattern for users to send payments to each other. 
Classes: Payment Manager, Payment Wizard Controller, Payment Wizard Frame, Step1Panel, Step2Panel, Step3Panel

**M04-A03**
Zach Carson: Worked on the LoginGUI, the user authentication function, and the DatabaseConnector class. 
Juan Oliver: Created transaction table in database, linked sent offers to populate on the transaction table in the database, linked sent offers to update on the notification view, enhanced flow of offer use case.

