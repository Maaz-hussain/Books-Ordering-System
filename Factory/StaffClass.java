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

public class StaffClass implements Book {
    @Override
    public void show(Object obj) { 
        List<Mediator.StaffMediator> iArray = new ArrayList<>();
        String query = "SELECT * from tb_staff ";
        System.out.println("" + query);
        Statement st = null;
        ResultSet rs = null;
        Mediator.StaffMediator sbeans;
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                sbeans = new Mediator.StaffMediator();
                sbeans.setCol_id(String.valueOf(rs.getInt("id")));
                sbeans.setCol_name(rs.getString("name").substring(0, 1).toUpperCase() + rs.getString("name").substring(1));
                sbeans.setCol_email((rs.getString("email"))); 
                sbeans.setCol_password(rs.getString("password") );
                sbeans.setCol_username((rs.getString("username"))); 
                iArray.add(sbeans);
            }
            Controller.MainViewController.Staff_List.clear();
            ObservableList<Mediator.StaffMediator> data = FXCollections.observableArrayList(iArray);
            Controller.MainViewController.Staff_List = data;
        } catch (SQLException ex) {
            Logger.getLogger(SingletonClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
    } 
}
