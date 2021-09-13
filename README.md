# Silly Goofy Scheduling App

You are working for a software company that has been contracted to develop a GUI-based scheduling desktop application. The contract is with a global consulting organization that conducts business in multiple languages and has main offices in Phoenix, Arizona; White Plains, New York; Montreal, Canada; and London, England. The consulting organization has provided a MySQL database that the application must pull data from. The database is used for other systems, so its structure cannot be modified.



The organization outlined specific business requirements that must be met as part of the application. From these requirements, a system analyst at your company created solution statements for you to implement in developing the application. These statements are listed in the requirements section.



Your company acquires Country and First-Level-Division data from a third party that is updated once per year. These tables are prepopulated with read-only data. Please use the attachment “Locale Codes for Region and Language” to review division data. Your company also supplies a list of contacts, which are prepopulated in the Contacts table; however, administrative functions such as adding users are beyond the scope of the application and done by your company’s IT support staff. Your application should be organized logically using one or more design patterns and generously commented using Javadoc so your code can be read and maintained by other programmers.

## C195 - Software II:

Desktop Scheduling Application

This is a Java application made for C195 Software II course.

The purpose of this application is to be able to schedule, track, and report on appointments from a desktop.
The application also allows users to manage and add customers to the database.

Krystal Lee
version 2.0
Aug 3, 2021

IntelliJ IDEA 2019.3.5 Ultimate Edition
JDK: Java SE 11.0.9
JavaFX: JavaFK-SDK-11.0.2
mysql-connector-java-8.0.22

Instructions:

You need access to the internet as the program connects to an online database.

To get to the Main Menu:
1. Launch the application
2. Input the Username and Password
3. Click the "Login" button
4. An alert message will popup with any appointments the user has within 15 minutes of logging in
5. Click the "OK" to acknowledge your upcoming appointments, if any
6. The main menu will be displayed with Customers, Appointments, and Reports categories

From the Main Menu:
To add, update, or delete Customers:
1. Click on "Customers" and a list of all customers will populate
2. On the right side are buttons to add, update, or delete
    Add: Click Add button. Fill out the form for name, address, phone, country, and division. Click "Save".
    Update: Select a customer from the populated list. Click the "Update" button. Update any information. Click update.
    Delete: Select a customer from the populated list. Click delete. Click on the OK button to confirm the deletion.
            Click OK to acknowledge successful deletion of the customer from the database.

To add, update, or delete Appointments:
1. Click on "Appointments" and a list of all appointments will populate
2. Below the populated list of appointments are buttons to add, update, or delete
    Add: Click Add button. Fill out the form for title, description, location, contact, type, date, start time, end time,
         customer name, and user. Click "Save".
    Update: Select an appointment from the populated list. Click the "Update" button. Update any information. Click "Update".
    Delete: Select an appointment from the populated list. Click "Delete". Click on the OK button to confirm the deletion.
            Click OK to acknowledge successful deletion of the appointment from the database.

From the Main Menu:
To run reports:
1. Click on "Reports"
2. Click on the desired report:
    A. Number of Appointments by Type and Month
    B. Schedule for Contacts
    C. Login Information

The customer report is a user login information report. This report pull the number of successful and failed login
attempts to the application.
