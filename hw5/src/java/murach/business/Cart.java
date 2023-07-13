//Jess Payton
//HW5 4/30/23
// cart java bean


package murach.business;

import java.io.Serializable;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.text.NumberFormat;

public class Cart implements Serializable {

    private ArrayList<LineItem> items;
    private double cartTotal;

    public Cart() {
        items = new ArrayList<LineItem>();
        cartTotal = 0;
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }
    
    private String setHtmlString(){
        return getCartHTML();
    }
    
    
    public int getCount() {
        return items.size();
    }
    
    // is it here  
// creates line item object which will be used as input for cart.jsp table 
    public void addItem(LineItem item) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                lineItem.setQuantity(quantity);
                return;
            }
        }
        items.add(item);
    }
// removes line item object used in cart.jsp
    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
    // this loops through line item java bean and sums total of all line items.
    public String getCartTotal(){
         NumberFormat currency = NumberFormat.getCurrencyInstance();
        cartTotal = 0;
        for (int i = 0; i < items.size(); i++){
            LineItem lineItem = items.get(i);
            cartTotal += lineItem.getTotal();
        }
           return currency.format(cartTotal);
        
    }
    
    public String getCartHTML(){
        StringBuilder htmlTable = new StringBuilder();
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        
        //format table here
            
        //column name
        htmlTable.append("<table border=\"true\">");

         // add header row
        htmlTable.append("<tr>");
        htmlTable.append("<th>Cover</th>");
        htmlTable.append("<th>Title</th>");
        htmlTable.append("<th>Price</th>");
        htmlTable.append("<th>Amount</th>");
        htmlTable.append("<th>Quantity</th>");      
        htmlTable.append("</tr>");
            
        // add cart items  
        for (LineItem item : items) {
            //Get product information
            Product p = item.getProduct();
            
            htmlTable.append("<tr>");
            htmlTable.append("<td>");
            htmlTable.append(p.getCover());
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append(p.getDescription());
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append(currency.format(p.getPrice()));
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append(item.getTotalCurrencyFormat());
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append(item.getQuantity());
            htmlTable.append("</td>");
            htmlTable.append("</tr>");
        }
        
        //add total line
        htmlTable.append("<tr>");
            htmlTable.append("<td>");
            htmlTable.append("Total");
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append(this.getCartTotal());
            htmlTable.append("</td>");
            htmlTable.append("<td>");
            htmlTable.append("</td>");
            htmlTable.append("</tr>");
            htmlTable.append("</table>");
        
        return htmlTable.toString();
               
    }
    
    
    public void cleanCart() {
 
           items = null;
           
    }
    
}
   
 