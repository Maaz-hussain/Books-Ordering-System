package ChainOfResponsiblity;

import Singleton.SingletonClass;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger; 
import javafx.collections.ObservableList;

public class OrderLogger extends AbstractLogger {

    public OrderLogger(int level) {
        this.level = level;
    }

    @Override
    protected void Add(Object obj) {
        Mediator.OrderMediator order_beans = (Mediator.OrderMediator) obj;

        try {
            String query = "INSERT INTO tb_order (`item_qty`,`order_date`,`order_time`,`staff_id`,`amount`) VALUES( '"
                    + order_beans.getItem_qty() + "','" + generateDate() + "','" + generateTime() + "'," + order_beans.getStaff_id() + "," + order_beans.getAmount() + ")";
            Statement st = null; 
            st = (Statement) connection.Connect.con.createStatement();
            if (st.executeUpdate(query) == 1) {
                System.out.println("Inserted Order");
                 addOrderDetails(order_beans.getbooksList());
            } else {
               
            }
        } catch (SQLException ex) {
            System.out.println("Data Is Not Inserted..");
            if (ex.getErrorCode() == 1062) {

            } else {
                Logger.getLogger(OrderLogger.class.getName()).log(Level.SEVERE, null, ex + " error code " + String.valueOf(ex.getErrorCode()));
            }
        }
    }

    int SelectLastOrderInsId() {
        List<Mediator.OrderMediator> ubArray = new ArrayList<>();
        String query = "SELECT * FROM `tb_order` ORDER BY id DESC LIMIT 1 "; 
        Statement st = null;
        ResultSet rs = null;
        Mediator.OrderMediator obeans;
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                obeans = new Mediator.OrderMediator();
                obeans.setId(String.valueOf(rs.getInt("id")));
                ubArray.add(obeans);
            }
            return Integer.parseInt(ubArray.get(0).getId());
        } catch (SQLException ex) {
            Logger.getLogger(SingletonClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    void addOrderDetails(ObservableList<Mediator.BooksMediator> ItemList) {
        int lastInsId=SelectLastOrderInsId();
           String val = "VALUES";
            for (int i = 0; i < ItemList.size(); i++) {
                if (i == 0) {
                    val += "("  +lastInsId+","+ ItemList.get(i).getCol_books_qty()+","+ItemList.get(i).getCol_id()+ ")";
                } else {
                    val += ",("  +lastInsId+","+ ItemList.get(i).getCol_books_qty()+","+ItemList.get(i).getCol_id()+ ")";
                }
            }

        try {
            String query = "INSERT INTO tb_order_details (`order_id`,`qty`,`item_id`) " +val;
            Statement st = null; 
            st = (Statement) connection.Connect.con.createStatement();
            if (st.executeUpdate(query) == 1) {
                System.out.println("Inserted OrderDetails");
            } else {

            }
        } catch (SQLException ex) {
            System.out.println("Data Is Not Inserted..");
            if (ex.getErrorCode() == 1062) {

            } else {
                Logger.getLogger(OrderLogger.class.getName()).log(Level.SEVERE, null, ex + " error code " + String.valueOf(ex.getErrorCode()));
            }
        }
    }

    public static String generateDate() {
        LocalDate dt = LocalDate.parse(LocalDate.now().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(dt);
    }

    public static String generateTime() {
        LocalTime dt = LocalTime.parse(LocalTime.now().toString());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(dt);
    }
}
