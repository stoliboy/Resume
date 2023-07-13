//Jess Payton HW4
// gateway acts as landing page and forwards database data to index.jsp


package murach.sql;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import murach.data.ConnectionPool;

public class SqlGatewayServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String sqlStatement = "SELECT Cover, Title, Price, Button FROM Book";
        String sqlResult = "";
        try {
          /*  // load the driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // get a connection
            String dbURL = "jdbc:mysql://localhost:3306/hw5";
            String username = "student";
            String password = "sesame";
            Connection connection = DriverManager.getConnection(
                    dbURL, username, password);

          */
          //Try connecition pool
          ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
            // create a statement
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlStatement);
            sqlResult = SQLUtil.getHtmlTable(resultSet);
                    resultSet.close();
            
            statement.close();
            pool.freeConnection(connection);
          
        }  catch (SQLException e) {
            sqlResult = "<p>Error executing the SQL statement: <br>"
                    + e.getMessage() + "</p>";
        }

        HttpSession session = request.getSession();
        session.setAttribute("sqlResult", sqlResult);
        session.setAttribute("sqlStatement", sqlStatement);

        String url = "/index.jsp";
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }
}