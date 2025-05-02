# Hospital Chain Management System

This Java application provides a graphical user interface for managing a medical institute chain. It allows medical field workers to do their specific tasks. This is a role base application with multiple roles( e.g. Admin, Doctor, Nurse, etc.).

## Table of Contents

* [Technologies Used](#technologies-used)
* [Installation](#installation)
   * [Prerequisites](#prerequisites)
   * [Database Setup](#database-setup)
   * [Compilation and Building](#compilation-and-building)
   * [Running the Application](#running-the-application)
* [Usage](#usage)
   * [General Description](#general-description)
   * [1.Admin and Super Admin](#1.admin-and-super-admin)
      * [Add User](#add-user)
      * [Edit User](#edit-user)
      * [Delete User](#delete-user)

## Technologies Used

* Java
* Java Swing
* MySQL database

## Installation

### Prerequisites

* **Java Development Kit (JDK)**: Version 8 or later is required.  You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use an open-source distribution like [OpenJDK](https://openjdk.java.net/).
* **MySQL Server**: A MySQL server is necessary to store the application's data.  You'll need to set up a database and user for the application.  Download MySQL from [MySQL Community Downloads](https://dev.mysql.com/downloads/).

### Database Setup

1.  **Create Database**:  Use a MySQL client (like the MySQL Command Line Client or MySQL Workbench) to create the database.

    ```sql
    CREATE DATABASE proiect3;
    ```

2.  **Create User and Grant Privileges**:  Create a user named `root` with the password `123456789` and grant it full access to the `proiect3` database.

    ```sql
    CREATE USER 'root'@'localhost' IDENTIFIED BY '123456789';
    GRANT ALL PRIVILEGES ON proiect3.* TO 'root'@'localhost';
    FLUSH PRIVILEGES;
    ```

    * **Important**:  If you change the username or password, you *must* update the `src/Conexiune.java` file to reflect those changes.  The connection details are hardcoded in the `Conexiune` class:

        ```java
        connection = DriverManager.getConnection("jdbc:mysql://localhost/proiect3?user=root&password=123456789");
        ```

### Compilation and Building

1.  **Obtain the Code**:  Download or clone the project source code.
2.  **Include MySQL Connector/J**:  You need the MySQL Connector/J JDBC driver to compile and run the application. Download it from the [MySQL website](https://dev.mysql.com/downloads/connector/j/) and add the JAR file to your project.
3.  **Compile**:
    * **Using an IDE**:  If you're using an IDE like IntelliJ IDEA or Eclipse, import the project and use the IDE's build function to compile the code.  Make sure the MySQL Connector/J JAR is included in the project's build path.
    * **Command Line**:  To compile from the command line:
        * Navigate to the project's `src` directory.
        * Compile the Java files:

            ```bash
            javac *.java
            ```

        * Ensure the MySQL Connector/J JAR file is in your classpath when compiling.  For example:

            ```bash
            javac -cp ".:/path/to/mysql-connector-java-8.0.33.jar" *.java 
            ```
            (Replace `/path/to/mysql-connector-java-8.0.33.jar` with the actual path to your JAR)
4.  **Create a JAR file (Optional)**:
    * To create an executable JAR file:

        ```bash
        jar cf HospitalManagementSystem.jar *.class
        ```

    * This will create a JAR file named `HospitalManagementSystem.jar` in the `src` directory.

### Running the Application

1.  **Start MySQL Server**:  Make sure your MySQL server is running.
2.  **Run the Application**:
    * **If using an IDE**:  Run the `src/Main.java` file from your IDE.
    * **From the command line**:
        * Navigate to the directory containing the compiled `.class` files or the JAR file.
        * Execute the JAR file:
          
            ```bash
            java -jar HospitalManagementSystem.jar
            ```
            
            or
          
            ```bash
            java Main
            ```
            (if running the class files directly)
          
3.  **Login**:  The application's login window should appear.  You can then log in to use the system.

## Usage

### Login

1. Launch the application.
2. In the login window:
   * Enter your registration email addres in the *Email* field.
   * Enter your registration password in the *Password* field
   * Click the *Login* button.
3. Upon successful login, the application will display the Main Menu.

### General user functionality

*   After login succesfully the user will be redirected to the Main Menu("Meniul Principal") from where he can access diferent parts of the application based on his role. The basic 4 interaction that every role has are:
1.   **View Personal Infos("Vizualizare Informatii Personale"):** Where the user can view his personal infos. Those infos are based on the specific role:
     * *User-"Utilizator"(all roles have those information):*
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
     * *Medical Employee-"Medical"(all the medical roles have those information):*
        * Salary
        * Number of contractual hours
     * *Doctor-"Medic":*
        * Speciality
        * Degree
        * Code
        * Service procentile
        * Scientific title(Optional)
        * Didactic title(Optional)
     * *Nurse-"Asistent Medical":*
        * Medical Unit ID
        * Type
        * Degree
     * *Receptionist-"Receptioner":*
        * Medical Unit ID
3.   **Show Timetable("Afisare Orar"):** Where the user can view his *work schedule* and his *medical institute schedule*(where it is the case).
4.   **Work("Munca"):** This is the principal menu for role-specific actions.
5.   **Logout:** Exits the user account.

### 1. Admin and Super Admin

* The *Admin* role can visualize all the **non admin users** and can do 3 actions: Add, Edit, Delete User for the non admin roles. This role it's used for better users administration and easier management.
* The *Super Admin* role can visualize all the users, **including the Admin and Super Admin roles**, and can do the same action as an Admin for all the roles.

#### Add User

* For adding a new user the *Admin* has to follow those steps:
1. Click the *Add User* button, this will open another menu with more options.
2. For adding a new user select the **User("Utilizator)** option, a formular will open where you need to add the personal information for the new user:
3. To add the suplimentar information for each role of an already added user select their role. Those roles can be:  **Medical Employee("Medical")***(if you want to add a Doctor or a Nurse you need to complete those information first)*, **Nurse("Asistent Medical)**, **Doctor("Medic")** amd **Receptionist("Receptioner")**.
4. For a Doctor there can be added suplimentar informations as:
*  **Medical Competence("Medic Competenta)**
  * CNP(the *Doctor* CNP)
  * Competence ID
*  **Medical Unit("Medic Unitate")**
  * CNP(the *Doctor* CNP)
  * Medical Unit ID
5. After the wanted user has been added click the *Sign Up* button.

#### Edit User

* For editing an existing user the *Admin* has to follow those steps:
1. Select the wanted user from the *Users table*.
2. Click the *Edit User* button, a new menu will open.
3. In this menu the *Admin* can modify all the information except the **CNP**.
4. After the wanted information where changed click the *Save Changes* button.

#### Delete User

* For deleting an user the *Admin* has to follow those steps:
1. Select the wanted user from the *Users Table*.
2. Click *Delete User* button, a dialog window will open.
3. Confirm this action by clicking the *Yes* button, or *No* button to cancel.
