 
package Factory;
import Singleton.SingletonClass;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ItemClass implements Book{
    @Override
    public void show(Object obj) { 
        List<Mediator.BooksMediator> iArray = new ArrayList<>();
        String query = "SELECT b.*,a.`author_name` FROM `tb_books` b LEFT JOIN tb_author a ON b.`author_id`=a.id ;";
        System.out.println("" + query);
        Statement st = null;
        ResultSet rs = null;
        Mediator.BooksMediator ibeans; 
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            
            
            while (rs.next()) {
                ibeans = new Mediator.BooksMediator();
                ibeans.setCol_id(String.valueOf(rs.getInt("id"))); 
                ibeans.setCol_title(rs.getString("title").substring(0, 1).toUpperCase() + rs.getString("title").substring(1));
                ibeans.setCol_iban_no(rs.getString("iban_no") );
                ibeans.setCol_price(String.valueOf(rs.getInt("price"))); 
                ibeans.setCol_author_name(rs.getString("author_name").substring(0, 1).toUpperCase() + rs.getString("author_name").substring(1));
                ibeans.setAuthor_id(String.valueOf(rs.getInt("author_id"))); 
                iArray.add(ibeans);
            }
            Controller.MainViewController.Books_list.clear();
            ObservableList<Mediator.BooksMediator> data = FXCollections.observableArrayList(iArray);
            Controller.MainViewController.Books_list = data;
        } catch (SQLException ex) {
            Logger.getLogger(ItemClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

  
