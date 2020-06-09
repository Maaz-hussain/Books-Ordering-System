package ChainOfResponsiblity;

import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class StaffLogger extends AbstractLogger {

    public StaffLogger(int level) {
        this.level = level;
    }

    @Override
    protected void Add(Object obj) {
        Mediator.StaffMediator sbeans = (Mediator.StaffMediator) obj;
        try {
            String query = "INSERT INTO tb_staff (`name`,`email`,`password`, `username`) VALUES( '"
                    + sbeans.getName()+ "','" + sbeans.getEmail()+ "','" + sbeans.getPassword()+ "','" + sbeans.getUsername()+ "')";
            Statement st = null;
            st = (Statement) connection.Connect.con.createStatement();
            if (st.executeUpdate(query) == 1) {
                System.out.println("Staff Data Inserted.");
            } else {

            }
        } catch (SQLException ex) {
            System.out.println("Staff Not Inserted.");
            if (ex.getErrorCode() == 1062) {

            } else {
                Logger.getLogger(StaffLogger.class.getName()).log(Level.SEVERE, null, ex + " error code " + String.valueOf(ex.getErrorCode()));
            }
        }
    }

   
}
