# HotelReservation

Technologies:
1. Spring Web MVC
2. Spring Data JPA & Hibernate
3. Spring Security
4. Thymeleaf
5. Bootstrap(webjars)
6. Junit Jupiter & AssertJ
7. MySQL Database


Software Programs
1. Java Development Kit (JDK)
2. Spring Tool suite IDE (STS)/Intellij
3. MySQL database server & MySQL Workbench

How To Run:

1. Login to mysql from command line mysql -uroot and run the following query.
   ```
   CREATE SCHEMA ridhimakohlidatabase;
   CREATE USER 'ridhimakohli'@'%' IDENTIFIED BY 'ridhimakohlipass';
   GRANT ALL ON ridhimakohlidatabase.* TO 'ridhimakohli'@'%';
    ```
2. Build the project using "mvn clean install" and then Run "HotelReservationApplication"
