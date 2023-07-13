/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  jessj
 * Created: Apr 30 , 2023
 */

/***********************************************************
* Create the database named hw5, its tables, and a database user
************************************************************/

DROP DATABASE IF EXISTS hw5;

CREATE DATABASE hw5;

USE hw5;



CREATE TABLE Book (
  isbn13 VARCHAR(15),
  Cover VARCHAR(100),
  Title VARCHAR(50),
  Price VARCHAR(50),
  Button VARCHAR(200),
 
  
  
 
  
  PRIMARY KEY(isbn13) 
);

INSERT INTO Book 
  (isbn13, Cover, Title, Price, Button)
VALUES 
  ('978-0345347534','https://m.media-amazon.com/images/I/51QE9WnTJUL._AC_UY218_.jpg', 'A Spell for Chameleon', '4.25',' <input type="hidden" name="productCode" value="978-0345347534"> <input type="submit" value="Add To Cart"> '),
  ('978-0345350589','https://m.media-amazon.com/images/I/71kLc9X5FuL._AC_UY218_.jpg', 'The Source of Magic', '9.99','<input type="hidden" name="productCode" value="978-0345350589"> <input type="submit" value="Add To Cart">'), 
  ('978-0345350480','https://m.media-amazon.com/images/I/51CEQ3jiQpL._AC_UY218_.jpg', 'Castle Roogna', '13.48','<input type="hidden" name="productCode" value="978-0345350480"> <input type="submit" value="Add To Cart">'),
   ('978-0345352460','https://m.media-amazon.com/images/I/713AtJYE2fL._AC_UY218_.jpg', 'Centaur Aisle', '7.77','<input type="hidden" name="productCode" value="978-0345352460"> <input type="submit" value="Add To Cart">');
CREATE TABLE USER (
  UserID INT NOT NULL AUTO_INCREMENT,
  Email VARCHAR(50),
  FirstName VARCHAR(50),
  LastName VARCHAR(50),
  Password VARCHAR(50),
 
  
  
 
  
  PRIMARY KEY(UserID) 
);

INSERT INTO User 
  (Email, FirstName, LastName, Password)
VALUES 
 ('bat@gmail.com', 'Bat', 'Man', 'bat'),
('spider@gmail.com', 'Spider', 'Man', 'spider'),
('super@gmail.com', 'Super', 'Man', 'super');
 
 -- Create student and grant privileges
select * from User;
DELIMITER //
CREATE PROCEDURE drop_user_if_exists()
BEGIN
    DECLARE userCount BIGINT DEFAULT 0 ;

    SELECT COUNT(*) INTO userCount FROM mysql.user
    WHERE User = 'student' and  Host = 'localhost';

    IF userCount > 0 THEN
        DROP USER student@localhost;
    END IF;
END ; //
DELIMITER ;

CALL drop_user_if_exists() ;

CREATE USER student@localhost IDENTIFIED BY 'sesame';

GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP
ON hw5.*
TO student@localhost;

  
USE hw5;
