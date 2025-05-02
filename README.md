# Hospital Chain Management System

This Java application provides a graphical user interface for managing a medical institute chain. It allows medical field workers to do their specific tasks. This is a role-base application with multiple roles( e.g. Admin, Doctor, Nurse, etc.).

## Table of Contents

* [Technologies Used](#technologies-used)
* [Installation](#installation)
   * [Prerequisites](#prerequisites)
   * [Database Setup](#database-setup)
   * [Compilation and Building](#compilation-and-building)
   * [Running the Application](#running-the-application)
* [Usage](#usage)
   * [generic Description](#generic-description)
   * [1. Admin and Super Admin](#1-admin-and-super-admin)
      * [Add User](#add-user)
      * [Edit User](#edit-user)
      * [Delete User](#delete-user)
   * [2. Receptionist](#2-receptionist)
      * [Add New Appointment](#add-new-appointment)
   * [3. Doctor](#3-doctor)
      * [View Pacient Information](#view-pacient-information)
      * [View Pacient Reports](#view-pacient-reports)
      * [View Pacient History](#view-pacient-history)
    * [4. Nurse](#4-nurse)
      * [View Pacient Information](#view-pacient-information-1)
      * [View Pacient Reports](#view-pacient-reports-1)
    * [5. Financial Expert](#5-financial-expert)
      * [Salary Operation](#salary-operation)
      * [Profit Operation](#profit-operation)
      * [Doctor Salary](#doctor-salary)
    * [6. Human Resources Inspector](#6-human-resources-inspector)
      * [Add/Edit Holidays](#addedit-holidays)
      * [Add/Edit Generic Schedule](#addedit-generic-schedule)
      * [Add/Edit Specific Schedule](#addedit-specific-schedule)
* [Project Structure](#project-structure)
    * [Common Classes(Used Across Roles)](#common-classesused-across-roles)
    * [Admin Role Classes](#admin-role-classes)
    * [Receptionist Role Classes](#receptionist-role-classes)
    * [Doctor Role Classes](#doctor-role-classes)
    * [Nurse Role Classes](#nurse-role-classes)
    * [Financial Expert Role Classes](#financial-expert-role-classes)
    * [Human Resources Inspector Role Classes](#human-resources-inspector-role-classes)
    * [Medical Unit Classes](#medical-unit-classes)
* [Team Contributions](#team-contributions)

## Technologies Used

* Java
* Java Swing
* MySQL database

## Installation

### Prerequisites

* **Java Development Kit (JDK)**: Version 17 or later is required.  You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use an open-source distribution like [OpenJDK](https://openjdk.java.net/).
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
   * Enter your registration E-mail addres in the *E-mail* field.
   * Enter your registration password in the *Password* field
   * Click the *Login* button.
3. Upon successful login, the application will display the Main Menu.

### generic user functionality

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
2. For adding a new user select the **User("Utilizator)** option, a form will open where you need to add the personal information for the new user:
3. To add the suplimentar information for each role of an already added user select their role. Those roles can be:  **Medical Employee("Medical")***(if you want to add a Doctor or a Nurse you need to complete those information first)*, **Nurse("Asistent Medical)**, **Doctor("Medic")** amd **Receptionist("Receptioner")**.
4. For a Doctor there can be added suplimentar informations as:
    * **Medical Competence("Medic Competenta)**
      * CNP(the *Doctor* CNP)
      * Competence ID
    * **Medical Unit("Medic Unitate")**
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
2. Click *Delete User* button, a dialogue window will open.
3. Confirm this action by clicking the *Yes* button, or *No* button to cancel.

### 2. Receptionist

* The *Receptionist* role can make a **medical appointment** for a pacient to his medical unit.

#### Add New Appointment

* For adding a new appointment the *Receptionist* has to follow those steps:
1. Click *Select pacient* button to select a pacient from the database or *Create new pacient* button to create a new pacient.
   * If the *Select pacient* option was selected a list of all the pacients will open.
   * If the *Create new pacient* option was selected a form with the new pacient information will open. The receptionist need to add those information.
2. Click *Select doctor* button to select a doctor, this will open a new window with all the doctors from this medical unit, then select the wanted doctor. 
3. Click *Select nurse* button to select a nurse, this will open a new window with all the nurses from this medical unit, then select the wanted nurse.
4. Click *Select services* button to select all the wanted services, this will open a new window with all services that the selected doctor can do.
5. Input the wanted date for this appointment. If the date is valid the *Select hour* window will open.
6. Select the wanted hour by clicking the *Select hour* button.
   * If the doctor and nurse are disponible in that date a select menu will open whit the disponible hours.
   * If the doctor or nurse are not disponible a dialogue window fill appear and the date need to be changed.
7. After all the information are added and valid a **bill** will be generated with all the services and their prices.
8. Click *Create appointment* button to finish this action.

### 3. Doctor

* The *Doctor* role can visualize the informations, reports and the medical history for a selected pacient. 
* Doctors have two additional options in the Main Menu:
1. **View Salary:** where the doctor can view his salary for a selected month based on his worked hours.
2. **View Profit:** whre the doctor can view his profit raport for a selected month.

* **To make any action the *Doctor* needs to select a pacient using *Registered pacients* button to view all the registered pacients or *Programed pacients* button to view all the programed pacients for him. After a pacient is selected the doctor can select his wanted action.**

#### View Pacient Information

* Click *View information* button to visualize the pacient account information.

#### View Pacient Reports

* For filling a raport the *Doctor* has to follow those steps:
1. Click *View reports* button, this will open a window with all the uncompleted report for his pacients.
2. Select the wanted report, this will open the *Medical Report Window*.
3. In this window the doctor can add a *medical recomandation* by clicking *Select recomandation doctor* button. A list with all the doctors will open, select the wanted doctor from here.
4. The doctor can add or remove medical services:
   * To add a new service click *Add service* button and select the wanted services.
   * To remove a service click the *Remove* button placed in the right of a service name.
5. Fill the rest of the filds: **Simptoms**, **Diagnostic**, **Recomandation** and **Result**.
6. Click the *Ok* button to finish this action or *Cancel* button to discard those changes. After the report has been finialized it can not be modify.
7. The report will be automaticaly signed with the doctor code.

#### View Pacient History

* Click *View history* button and select the wanted medical report to see it's informations.

### 4. Nurse

* The *Nurse* role can visualize the informations and reports for a selected pacient. 
* Nurses have one additional option in the Main Menu:
1. **View Salary:** where the nurse can view his salary for a selected month based on his worked hours.

* **To make any action the *Nurse* needs to select a pacient using *Registered pacients* button to view all the registered pacients or *Programed pacients* button to view all the programed pacients for him. After a pacient is selected the nurse can select his wanted action.**

#### View Pacient Information

* Click *View information* button to visualize the pacient account information.

#### View Pacient Reports

* For filling a raport the *Nurse* has to follow those steps:
1. Click *View reports* button, this will open a window with all the uncompleted report for his pacients.
2. Select the wanted report, this will open the *Medical Report Window*.
3. In this window the nurse can add the results for all services. Those will appear int the **Investigation** section of the report.
4. Click the *Ok* button to finish this action or *Cancel* button to discard those changes.

### 5. Financial Expert

* The *Financial Expert* role can visualize the salary of every role that has one. He also can view the profit made by a docotr, medical unit, service or the total profit for the hospital chain.

#### Salary Operation

* To visualizing the salary of a user the *Finanacial Expert* has to follow those steps:
1. Click *Salary Operation* button, this will open a window with two list, one for **Nurses** and one for **Doctors**.
2. Select one by clicking on his name, this will open a list with the months of the current year.
3. By clicking on a month the salary will appear in the buttom of this window.

#### Profit Operation

* To visualizing the profit the *Finanacial Expert* has to follow those steps:
1. Click *Profit Operation* button, this will open a window with 4 options:
   * **Profit per Doctor**
   * **Profit per Unit**
   * **Profit per Service**
   * **Total Profit**
2. After selecting one of the above option and clicking *Generate Report* button a new window will open.
3. Based on the option selected at the above step:
   1. Select a **Doctor**, **Medical Unit**, **Service**, if option 1, 2 or 3 was selected.
   2. Go to the next step, if the option 4 was selected.
4. A new window will open the months of the current year.
5. Select one ore more months then click *Generate Report* button.
6. A dialogue window will appear with the profit for each selected month.

#### Doctor Salary

* This option compute the salary of a doctor based on his finished medical report from that month. To visualize this the *Fincancial Expert* has to follow those steps:
1. Click *Doctor Salary Bonus* button, this will open a list with all the **Doctors**.
2. Select a doctor by clicking on his name, this will open a list with the months of the current year.
3. Select one ore more months then click *Generate Report* button.
4. A dialogue window will appear with the salary for each selected month.

### 6. Human Resources Inspector

* The *Human Resources Inspector* can view and modify the work schedule for an user.
* **Before any operation can be carried the *Human Resources Inspector* need to select an user by filling the First Name, Last Name and Role fields**
* If a user was found a new window will open, this window contains:
1. **Holidays Table:** here are all the holidays for the selected user.
2. **generic Schedule:** here are the week days that the user works.
3. **Specific Schedule:** some roles can have a *specific schedule* for a date. This schedule has priority over the *generic Schedule*. 

#### Add/Edit Holidays

* For adding a new holiday the *Human Resources Inspector* has to follow those steps:
1. Click *Add Holiday* button under the holidays table, this will open a new window.
2. Fill the *Start Date* and *End Date* fields.
3. Click *OK* button to finish this action, or *Cancel* otherwise.

* For editing an existing holiday the *Human Resources Inspector* has to follow those steps:
1. Select the wanted holiday from the table.
2. Click *Edit Holiday* button under the holidays table, this will open a new window.
3. Fill the new *Start Date* and *End Date*.
4. Click *OK* button to finish this action, or *Cancel* to discard those changes.

#### Add/Edit Generic Schedule

* For adding a new generic schedule entry the *Human Resources Inspector* has to follow those steps:
1. Click *Add* button under the generic schedule table, this will open a new window.
2. Fill the *Day*, *Start Time* and *End Time* fields.
3. Click *OK* button to finish this action, or *Cancel* otherwise.

* For editing an existing generic schedule entry the *Human Resources Inspector* has to follow those steps:
1. Select the wanted generic schedule entry from the table.
2. Click *Edit* button under the generic schedule table, this will open a new window.
3. Fill the new *Day*, *Start Time* and *End Time* fields.
4. Click *OK* button to finish this action, or *Cancel* to discard those changes.

#### Add/Edit Specific Schedule

* For adding a new specific schedule entry the *Human Resources Inspector* has to follow those steps:
1. Click *Add* button under the specific schedule table, this will open a new window.
2. Fill the *Date*, *Start Time* and *End Time* fields.
3. Click *OK* button to finish this action, or *Cancel* otherwise.

* For editing an existing specific schedule entry the *Human Resources Inspector* has to follow those steps:
1. Select the wanted specific schedule entry from the table.
2. Click *Edit* button under the specific schedule table, this will open a new window.
3. Fill the new *Date*, *Start Time* and *End Time* fields.
4. Click *OK* button to finish this action, or *Cancel* to discard those changes.

## Project Structure

### Common Classes(Used Across Roles)

* `Main.java`: Handles application startup and initialization.
* `Conexiune.java`: Manages the connection to the MySQL database.
* `LoginFame.java`: Handles user login and authentication.
* `MainFrame.java`: Main Menu window.
* `Utilizator.java`: Contains the user specific fields. Provides methods for retrieving user data in the database.
* `ProceduriSql.java`: Contains methods for SQL views and triggers calls.

### Admin Role Classes

* `ShowAdmin.java`: Handles admin role specific actions.
* `InserariFrame.java`: Handles user registration.

 ### Receptionist Role Classes

* `Receptioner.java`: Contains the receptionist specific fields. Provides methods for retrieving receptionist data in the database.
* `Pacient.java`: Contains the pacient specific fields. Provides methods for retrieving and creating pacient data in the database.
* `Programare.java`: Contains the appointment specific fields. Provides methods for retrieving and creating appointment data in the database.
* `ProgramareFrame.java`: Handles receptionist role specific actions.
* `PacientiListFrame.java`, `MediciListFrame.java`, `AsistentListFrame.java`, `ServiciiListFrame.java`, `TimeListFrame.java`: Handles list selection frames.
* `CreerePacientFrame.java`: Handles pacient registration.
* `ServiciuMedical.java`: Contains the medical services specific fields. Provides methods for retrieving medical services data in the database.
* `Competenta.java`: Contains the medical competence specific fields.

### Doctor Role Classes

* `Medical.java`: Contains the medical employee specific fields. Provides methods for retrieving medical employee data in the database.
* `Medic.java`: Contains the doctor specific fields. Provides methods for retrieving doctor data in the database.
* `MedicFrame.java`: Handles doctor role specific actions.
* `RaportMedical.java`: Contains the medical report specific fields. Provides methods for retrieving and creating medical report data in the database.
* `RaportMedicalFrameList.java`: Handles medical reports list selection frame.
* `RaportMedicalFrame.java`: Handles reports specific actions.

### Nurse Role Classes

* `AsistentMedical.java`: Contains the nurse specific fields. Provides methods for retrieving nurse data in the database.
* `AsistentFrame.java`: Handles nurse specific actions.

### Financial Expert Role Classes

* `ShowModul2.java`: Handles financial expert specific actions.
* `ShowExpertFinanciarProfit.java`: Handles profit operations.
* `ShowExpertFinanciarSalarii.java`: Handles salary operations.
* `ShowBonus.java`: Handles doctor salary operation.

### Human Resources Inspector Role Classes

* `AngajatFrame.java`: Handles human resources inspector specific actions.
* `CautaAngajatFrame.java`: Handles user finding.
* `Concediu.java`: Contains the holiday specific fields. Provides methods for retrieving holiday data in the database.
* `DisplayOrarMedicalFrame.java`: Handles schedule display.
* `OrarGeneric.java`: Contains the generic schedule specific fields. Provides methods for retrieving generic schedule data in the database.
* `OrarSpecific.java`: Contains the specific schedule specific fields. Provides methods for retrieving specific schedule data in the database.

### Medical Unit Classes

* `UnitateMedicala.java`: Contains the medical unit specific fields. Provides methods for retrieving medical unit data in the database.
* `CabinetMedical.java`: Contains the medical cabinet specific fields.

## Team Contributions

*This project was a collaborative effort by 3 students. The following details the contributions of each member:

* **Boca Ioan Doru**
  * Project Leadership and Coordination:
    * Defined project scope and objectives.
    * Divided tasks among team members based on their skills and expertise.
    * Scheduled meetings and monitored progress to ensure timely completion.
  * Database Design and Implementation:
    * Created the relational database schema in MySQL to store user, employee, pacient, and medical data.
    * Defined tables, columns, data types, primary keys, foreign keys, and relationships.
    * Implemented views and triggers within the database.
    * Implemented database connection logic within the application.
  * Medical Module Development:
    * Designed and implemented the GUI windows and business logic for the Doctor, Nurse, and Receptionist roles.
    * Developed classes to represent medical staff, pacient information, appointments, and medical records.
  * Initial Project Setup:
    * Established the project's class structure and package organization.
    * Created core classes for database interaction.
  * Documentation:
    * Wrote the project's documentation, including user manual and technical specifications.

* **Birlea Bogdan Alexandru**
  * Database Design and Implementation:
    * Implemented views and triggers within the database.
  * Financial Module Development:
    * Designed and implemented the GUI windows and business logic for Financial Expert role.
  * Admin and Super Admin Module Development
    * Designed and implemented the GUI windows and business logic for Admin and Super Admin roles.

* **Marian Alexandru**
  * Human Resources Module Development:
    * Designed and implemented the GUI windows and business logic for Human Resources Inspector role.
