package ChainOfResponsiblity;

import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksLogger extends AbstractLogger {

    public BooksLogger(int level) {
        this.level = level;
    }

    @Override
    protected void Add(Object obj) {
        Mediator.BooksMediator itemb = (Mediator.BooksMediator) obj;
        try {
            String query = "INSERT INTO tb_books (`title`,`price`,`iban_no`,`author_id`) VALUES( '"
                    + itemb.getTitle() + "'," + itemb.getPrice() + ",'"+itemb.getIban_no()+"'," + itemb.getAuthor_id() + ")";
            Statement st = null;
            st = (Statement) connection.Connect.con.createStatement();
            if (st.executeUpdate(query) == 1) {
                System.out.println("Book Inserted ");
            } else {

            }
        } catch (SQLException ex) {
            System.out.println("Data Is Not Inserted..");
            if (ex.getErrorCode() == 1062) {

            } else {
                Logger.getLogger(BooksLogger.class.getName()).log(Level.SEVERE, null, ex + " error code " + String.valueOf(ex.getErrorCode()));
            }
        }
    }

    
}
