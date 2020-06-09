package Factory;

import Mediator.AuthorMediator;
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

public class ItemCategoryClass implements Book { 
    @Override
    public void show(Object obj) { 
        List<Mediator.AuthorMediator> ubArray = new ArrayList<>();
        String query = "SELECT * FROM `tb_author` ";
        System.out.println("" + query);
        Statement st = null;
        ResultSet rs = null;
        Mediator.AuthorMediator sbeans; 
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                sbeans = new Mediator.AuthorMediator();
                sbeans.setCol_id(String.valueOf(rs.getInt("id")));
                sbeans.setCol_author_name(rs.getString("author_name").substring(0, 1).toUpperCase() + rs.getString("author_name").substring(1)); 
                ubArray.add(sbeans);
            }
            Controller.MainViewController.BooksAuthor_List.clear();
            ObservableList<Mediator.AuthorMediator> data = FXCollections.observableArrayList(ubArray);
            Controller.MainViewController.BooksAuthor_List = data;
        } catch (SQLException ex) {
            Logger.getLogger(SingletonClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
} 