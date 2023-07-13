//Jess Payton HW4
// formats the data into a table


package murach.sql;

import java.sql.*;

public class SQLUtil {

    public static String getHtmlTable(ResultSet results)
            throws SQLException {
        
        StringBuilder htmlTable = new StringBuilder();
        ResultSetMetaData metaData = results.getMetaData();
        int columnCount = metaData.getColumnCount();

        htmlTable.append("<table>");

        // add header row
        htmlTable.append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            htmlTable.append("<th>");
            htmlTable.append(metaData.getColumnName(i));
            htmlTable.append("</th>");
            htmlTable.append("</th>");
        }
        htmlTable.append("</tr>");

        // add all other rows
        while (results.next()) {
            htmlTable.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<td>");
                if (i==1){
                    //Cover. We want to format the url with an image tag
                    htmlTable.append("<img src=\"" + results.getString(i) + "\"/>");// formats img tag
                }
                else if (i==4){
                    //Add to cart column. We want to make the form post so the servlet will capture the action and read in values
                    htmlTable.append("<form action=\"cart\" method=\"post\">");
                    htmlTable.append(results.getString(i));
                    htmlTable.append("</form>");
                }
                else{
                    htmlTable.append(results.getString(i));
                }    
                htmlTable.append("</td>");
            }
            htmlTable.append("</tr>");
            htmlTable.append("</tr>");
        }

        htmlTable.append("</table>");
        return htmlTable.toString();
    }
}