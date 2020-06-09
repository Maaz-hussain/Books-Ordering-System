package ChainOfResponsiblity;
import com.mysql.jdbc.Statement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BooksAuthorLogger   extends AbstractLogger{

  public BooksAuthorLogger(int level){
      this.level = level;
   }
    @Override
    protected void Add(Object obj) {
        Mediator.AuthorMediator itemb = (Mediator.AuthorMediator) obj;
        try {
            String query = "INSERT INTO tb_author (`author_name`) VALUES( '" + itemb.getAuthor_Name()+"')";
            Statement st = null;
            st = (Statement) connection.Connect.con.createStatement();
            if (st.executeUpdate(query) == 1) {
                System.out.println("Author inserted");
            } else {

            }
        } catch (SQLException ex) {
            System.out.println("Data Is Not Inserted");
            if (ex.getErrorCode() == 1062) {

            } else {
                Logger.getLogger(BooksAuthorLogger.class.getName()).log(Level.SEVERE, null, ex + " error code " + String.valueOf(ex.getErrorCode()));
            }
        }
    }

}
