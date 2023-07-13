//Jess Payton
//HW5 4/30/23
// cartServlet  handles the cart process and creates the cart object.


package murach.cart;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import murach.data.*;
import murach.business.*;

public class CartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ServletContext sc = getServletContext();
        
        // get current action
        String action = request.getParameter("action");
        if (action == null) {
            action = "cart";  // default action
        }

        // perform action and set URL to appropriate page
        String url = "/index.jsp";
        if (action.equals("shop")) {
            url = "/sqlGateway";    // the "index" page
        } else if (action.equals("checkout")){
            url = "/signin.jsp"; // sign in page
        }
        else if (action.equals("cart2")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();            
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }
            session.setAttribute("productCode", "<p>" + productCode + "</p>");
            //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }
            //text file substitute for db
            String path = sc.getRealPath("/WEB-INF/products.txt");
            Product product = ProductIO.getProduct(productCode, path);

            LineItem lineItem = new LineItem();
            lineItem.setProduct(product);
            lineItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(lineItem);
            } else if (quantity == 0) {
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        else if (action.equals("cart")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");

            HttpSession session = request.getSession();
            session.setAttribute("productCode", "<p>" + productCode + "</p>");
            
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            //if the user enters a negative or invalid quantity,
            //the quantity is automatically reset to 1.
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) {
                    quantity = 1;
                }
            } catch (NumberFormatException nfe) {
                quantity = 1;
            }
            
   
        
        Product p = new Product();
        String sqlResult = "";
        try {
          
            /*
            // load the driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // get a connection
            String dbURL = "jdbc:mysql://localhost:3306/hw5";
            String username = "student";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(
                    dbURL, username, password);
            */
            
           //Try connection pool
           ConnectionPool pool = ConnectionPool.getInstance();
           Connection connection = pool.getConnection();
           
                    //Fetch book information to be added to Cart
        String sqlStatement = "SELECT isbn13, Cover, Title, Price FROM Book where isbn13 = ?";
        
        // test prepapred statement
     //  PreparedStatement ps = connection.prepareStatement();
        //test
        session.setAttribute("sqlStatement", "<p>" + sqlStatement + "</p>");
             // test prepapred statement
     
            // create a statement
          //  Statement statement = connection.createStatement();

            sqlStatement = sqlStatement.trim();
            if (sqlStatement.length() >= 6) {
                String sqlType = sqlStatement.substring(0, 6);
                if (sqlType.equalsIgnoreCase("select")) {
                    //For hw5, only this code should apply
                    //Execute query and store results in a Product object
                    //
                    
                      PreparedStatement ps = connection.prepareStatement(sqlStatement);
       ps.setString(1, productCode);
       ResultSet resultSet = ps.executeQuery();
               
                   while(resultSet.next()){
                    session.setAttribute("isbn", "<p>" + resultSet.getString(1) + "</p>");
                   
                    p.setCode(resultSet.getString(1));
                    p.setCover("<img src=\"" + resultSet.getString(2)+ "\"/>");
                    p.setDescription(resultSet.getString(3));
                    p.setPrice(resultSet.getDouble(4));
                    }
                    resultSet.close();
                } 
            }
            
            pool.freeConnection(connection);
           
        }  catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }  
        //test
        session.setAttribute("sqlResult", sqlResult);
                    

            LineItem lineItem = new LineItem();
            lineItem.setProduct(p);
            lineItem.setQuantity(quantity);
            if (quantity > 0) {
                cart.addItem(lineItem);
            } else if (quantity == 0) {
                cart.removeItem(lineItem);
            }

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        else if (action.equals("checkout")) {
            HttpSession session = request.getSession();
            session.setAttribute("email","");
            session.setAttribute("password","");
            url = "/signin.jsp";
            session.invalidate();
            
        }
        // ends sesion and returns user to landing page
        else if (action.equals("return")){
            // need to expire cookie so fields are blank..
             HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");
            session.invalidate();
        
           url = "/sqlGateway";
        }
        
        sc.getRequestDispatcher(url)
                .forward(request, response);
    }
}