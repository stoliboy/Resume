//Jess Payton HW5
// userServlet  will handle db check for user email and password


package murach.user;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import murach.data.*;
import murach.business.*;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
             
        // get current action
        String action = request.getParameter("action");
        if (action.equals("submit")){
            
            HttpSession session = request.getSession();
       
            // get parameterts from user
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            session.setAttribute("email", email);
            

            
            session.setAttribute("testInput","email = " + email + "; password = " + password + ".</p>");
            
            String sqlStatement = "Select *  from User where email= '" + email + "'";   
            
            session.setAttribute("sqlStatement", "<p>" + sqlStatement + "</p>");
       
            String sqlResult = "";
            String url = "";
            
            try {
           /*     // load the driver
               Class.forName("com.mysql.jdbc.Driver");

               // get a connection
               String dbURL = "jdbc:mysql://localhost:3306/hw5";
               String username = "student";
               String pwd = "sesame";
               Connection connection = DriverManager.getConnection(
                       dbURL, username, pwd);
*/
           
              ConnectionPool pool = ConnectionPool.getInstance();
            Connection connection = pool.getConnection();
               // create a statement
               Statement statement = connection.createStatement();
               
               sqlStatement = sqlStatement.trim();
               if (sqlStatement.length() >= 6) {
                   String sqlType = sqlStatement.substring(0, 6);
                   if (sqlType.equalsIgnoreCase("select")) {
                       //For hw5, only this code should apply
                       //Execute query and store results in a User object
                       //
                        ResultSet resultSet = statement.executeQuery(sqlStatement);
                        
                        //Set url to registration.jsp as a default. If there are no rows returned from the query, then it should go to this page.
                        //If there is a match it will be reassigned to thankyou.jsp
                        //url will be assigned to signin if the user entered a wrong password
                        url= "/registration.jsp";
                        
                        
                      ///Does this need to be action = join   
                        while(resultSet.next()) {
                      
                             if (resultSet.getString(5).equals(password)){
                                
                         
                                 url = "/thankyou.jsp";
                                 sqlResult = "<p> email='"+ email + "'; firstName ='" + resultSet.getString(3) +"'; lastName='" + resultSet.getString(4) + "'</p>";
                                 User u = new User();
                                 u.setEmail(email);
                                 u.setPassword(resultSet.getString(2));
                                 u.setFirstName(resultSet.getString(3));
                                 u.setLastName(resultSet.getString(4));
                                 
                                 Cart cart = (Cart) session.getAttribute("cart");
                                 String cartHTML = cart.getCartHTML();
                                 request.setAttribute("cartHTML", cartHTML);
                                 
                                 request.setAttribute("userHTML", u.getUserHTML());
            
                             }
                            
                             else {
                                 url = "/signin.jsp";
                                 sqlResult = "<p>**The password you entered is incorrect. Please try again.**</p>";
                             }
                             
                            
                        }
                    }
                   
                   ///What is this 
                    else {
                        int i = statement.executeUpdate(sqlStatement);
                        if (i == 0) { // a DDL statement
                            sqlResult = 
                                "<p>The statement executed successfully.</p>";
                        } 
                        else { // an INSERT, UPDATE, or DELETE statement
                            sqlResult = 
                                "<p>The statement executed successfully.<br>" + i + " row(s) affected.</p>";
                        }
                    }
                }
                statement.close();
                pool.freeConnection(connection);
                connection.close(); 
            } 
              
           
            catch (SQLException e) {
                sqlResult = "<p>Error executing the SQL statement: <br>"
                       + e.getMessage() + "</p>";
            } 
   
           
            session.setAttribute("sqlResult",sqlResult);
            session.setAttribute("url",url);
          
         // url = "/thankyou.jsp";
            sc.getRequestDispatcher(url)
                .forward(request, response);
        }
        else if (action.equals("join")){
            String sqlResult = "";
            String url = "";
            HttpSession session = request.getSession();
       
              try{
                  
              
               // load the driver
               Class.forName("com.mysql.jdbc.Driver");

        /*       // get a connection
               String dbURL = "jdbc:mysql://localhost:3306/hw5";
               String username = "student";
               String pwd = "sesame";
               Connection connection = DriverManager.getConnection(
                       dbURL, username, pwd);

*/
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
               // create a statement
               Statement statement = connection.createStatement();
                             // get parameterts from user
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            
            // Store data in user object
            User u = new User();
            u.setEmail(email);
            u.setPassword(password);
            u.setFirstName(firstName);
            u.setLastName(lastName);
            
      
            
            String query = "INSERT INTO User (Email, Password, FirstName, LastName) " +
                           "VALUES (?, ?, ?, ?) ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, firstName);
            ps.setString(4, lastName);
            ps.executeUpdate();
            
                statement.close();
                pool.freeConnection(connection);
                connection.close(); 
                
               //this needs to display user info and needs to display checkout cart
               // 
                
                 url = "/thankyou.jsp";
                                 sqlResult = "<p> email='"+ email + "'; firstName ='" + firstName +"'; lastName='" + lastName + "'</p>";
                                 session.setAttribute("sqlResult", sqlResult);
                  
                            
                                 Cart cart = (Cart) session.getAttribute("cart");
                                 String cartHTML = cart.getCartHTML();
                                 request.setAttribute("cartHTML", cartHTML);
                                 
                                 request.setAttribute("userHTML", u.getUserHTML());
                                  sc.getRequestDispatcher(url)
                                    .forward(request, response);
            } 
              
            catch (ClassNotFoundException e) {
               sqlResult = "<p>Error loading the databse driver: <br>"
                       + e.getMessage() + "</p>";
            }
            catch (SQLException e) {
                sqlResult = "<p>Error executing the SQL statement: <br>"
                       + e.getMessage() + "</p>";
            } 
                           
                             }
        
    }
}
