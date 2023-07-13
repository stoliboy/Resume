/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  jessj
 * Created: Apr 16, 2023
 */

/***********************************************************
* Create the database named hw4, its tables, and a database user
************************************************************/

DROP DATABASE IF EXISTS hw4;

CREATE DATABASE hw4;

USE hw4;

CREATE TABLE Book (
  BookID INT NOT NULL AUTO_INCREMENT,
  Cover VARCHAR(100),
  Title VARCHAR(50),
  Price VARCHAR(50),
 
  
  PRIMARY KEY(BookID) 
);

INSERT INTO Book 
  (Cover, Title, Price)
VALUES 
  ('https://m.media-amazon.com/images/I/51QE9WnTJUL._AC_UY218_.jpg', 'A Spell for Chameleon', '4.25'),
  ('https://m.media-amazon.com/images/I/71kLc9X5FuL._AC_UY218_.jpg', 'The Source of Magic', '9.99'), 
  ('https://m.media-amazon.com/images/I/51CEQ3jiQpL._AC_UY218_.jpg', 'Castle Roogna', '13.48'),
   ('https://m.media-amazon.com/images/I/713AtJYE2fL._AC_UY218_.jpg', 'Centaur Aisle', '7.77');

 
 -- Create student and grant privileges

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
ON hw4.*
TO student@localhost;

  
USE hw4;