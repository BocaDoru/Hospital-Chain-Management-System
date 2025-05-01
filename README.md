#Hospital Chain Management System

This Java application provides a graphical user interface for managing a medical institute chain. It allows medical field workers to do their specific tasks. This is a role base application with multiple roles( e.g. Admin, Doctor, Nurse, etc.).

## Table of Contents

* [Technologies Used](#technologies-used)
* [Installation](#installation)
* [Usage](#usage)
    * [General Description](#general-description)
    * [Admin and Super Admin](#admin-and-super-admin)
        * [Add User](#add-user)
        * [Edit User](#edit-user)
        * [Delete User](#delete-user)

## Technologies Used

* Java
* Java Swing
* MySQL database

## Installation

1.  Ensure you have Java Development Kit (JDK) version 17 or higher installed.
2.  Clone this repository: `git clone https://github.com/BocaDoru/Hospital-Chain-Management-System.git`
3.  Compile the Java files.
    * If using an IDE (e.g., IntelliJ IDEA, Eclipse), import the project and build it.
    * If using the command line:
        * Navigate to the project's root directory.
        * Compile all Java files: `javac -sourcepath src -d bin src/*.java`
4.  Execute the `Main.java` file: `java Main`

## Usage

### General Description

* First login into the application using the GUI frame, you need to add your e-mail and password used at registration.
* After login succesfully the user will be redirected to the Main Menu("Meniul Principal") from where he can access diferent parts of the application based on his role. The basic 4 interaction that every role has are:
  1. View Personal Infos("Vizualizare Informatii Personale") where the user can view his personal infos(e.g. First Name, Last Name, CNP, Address, E-mail, etc.)
  2. Show Timetable("Afisare Orar") where the user can view his work schedule and his medical institute schedule where it is the case.
  3. Work("Munca") this is the principal menu for work related action for every user.
  4. Logout.

1. ### Admin and Super Admin

* The *Admin* role can visualize all the **non admin users** and can do 3 actions: Add, Edit, Delete User for the non admin roles. This role it's used for better users administration and easier management.
* The *Super Admin* role can visualize all the users, **including the Admin and Super Admin roles**, and can do the same action as an Admin for all the roles.

  #### Add User
  * For adding a new user the *Admin* has to follow those steps:
    1. Press the *Add User* button, this will open another menu with more options.
    2. For adding a new user select the **User("Utilizator)** option, a formular will open where you need to add the personal information for the new user:
      * CNP
      * Last Name
      * First Name
      * Address
      * Phone Number
      * E-mail
      * Bank Account
      * Contract Number
      * Date of Employment
      * Role
      * Password
    3. To add the suplimentar information for each role of an already added user select their role. Those roles can be:  **Medical Employee("Medical")***(if you want to add a Doctor or a Nurse you need to complete those information first)*, **Nurse("Asistent Medical)**, **Doctor("Medic")** amd **Receptionist("Receptioner")**.
      * Medical Employee has the following information:
          * CNP(used to link those information to the *User* account)
          * Salary
          * Number of contractual hours
      * Doctor has the following information:
        * CNP(used to link those information to the *Medical Employee* account)
        * Speciality
        * Degree
        * Code
        * Service procentile
        * Scientific title(Optional)
        * Didactic title(Optional)
      * Nurse has the following information:
        * CNP(used to link those information to the *Medical Employee* account)
        * Medical Unit ID
        * Type
        * Degree
      * Receptionist has the following information:
        * CNP(used to link those information to the *User* account)
        * Medical Unit ID
    4. For a Doctor there can be added suplimentar informations as:
        * **Medical Competence("Medic Competenta)**
          * CNP(the *Doctor* CNP)
          * Competence ID
        * **Medical Unit("Medic Unitate")**
          * CNP(the *Doctor* CNP)
          * Medical Unit ID
    5. After the wanted user has been added press the *Sign Up* button.

  ### Edit User
  * For editing an existing user the *Admin* has to follow those steps:
    1. Select the user from the table and then press the *Edit User* button, a new menu will open.
    2. In this menu the *Admin* can modify all the information except the **CNP**.
    3. After the wanted information where changed press the *Save Changes* button.

  ### Delete User
  * For deleting an user the *Admin* need to select him from the table and press *Delete User* button, after he confirme this action the user account will pe deleted.
