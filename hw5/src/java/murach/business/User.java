/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//Jess Payton
//HW5 4/30/23
// user java bean to store user data


package murach.business;

import java.io.Serializable;

/**
 *
 * @author jessj
 */

public class User implements Serializable {
    
    private String email;
    private String password;
    private String firstName;
    private String lastName;   


  public User () {
    email = "";
    password = "";
    firstName = "";
    lastName = "";

}
    public void setEmail(String email){
    this.email = email;
    }
    
    public String getEmail(){
    return email;
    }
    
    public void setPassword(String password){
    this.password = password;
    }
    
    public String getPassword(){
    return password;
    }
    
    public void setFirstName(String firstName){
    this.firstName = firstName;
    }
    
    public String getFirstName(){
    return firstName;
    }

    public void setLastName(String lastName){
    this.lastName = lastName;
    }

    public String getLastName(){
    return lastName;
    }
    
    public String getUserHTML(){
        StringBuilder userHTML = new StringBuilder();
        
        userHTML.append("<p> Here is the information that you entered </p>");
        userHTML.append("<p><b>Email:</b>");
        userHTML.append(this.getEmail());
        userHTML.append("</p>");
        userHTML.append("<p><b>First Name:</b>");
        userHTML.append(this.getFirstName());
        userHTML.append("</p>");
        userHTML.append("<p><b>Last Name:</b>");
        userHTML.append(this.getLastName());
        userHTML.append("</p>");
        userHTML.append("<br><br>");
        
    return userHTML.toString();
    }

    
}   