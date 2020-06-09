package Singleton;

import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SingletonClass {


    private static SingletonClass instance = new SingletonClass();

    private SingletonClass() {
    }


    public static SingletonClass getInstance() {
        return instance;
    }

    public ObservableList<Mediator.StaffMediator> loginVerifier(Mediator.StaffMediator empBeans) { 
        List<Mediator.StaffMediator> ubArray = new ArrayList<>();
        String query = "SELECT * FROM `tb_staff` WHERE username='"+empBeans.getUsername()+"' AND password='"+empBeans.getPassword()+"' LIMIT 1;";
         System.out.println(""+query);
        Statement st = null;
        ResultSet rs = null;
        Mediator.StaffMediator eBeans;
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query); 
            while (rs.next()) {
                eBeans = new Mediator.StaffMediator();
                eBeans.setUsername(rs.getString("username").substring(0, 1).toUpperCase() + rs.getString("username").substring(1));
                eBeans.setId(String.valueOf(rs.getInt("id"))); 
                ubArray.add(eBeans); 
            }
            ObservableList<Mediator.StaffMediator> data = FXCollections.observableArrayList(ubArray);
            return data;
        } catch (SQLException ex) {
            Logger.getLogger(SingletonClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
}
