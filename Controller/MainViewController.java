package Controller;

import Mediator.BooksMediator;
import Mediator.AuthorMediator;
import Mediator.OrderMediator;
import Mediator.StaffMediator;
import ChainOfResponsiblity.AbstractLogger;
import ChainOfResponsiblity.BooksAuthorLogger;
import ChainOfResponsiblity.BooksLogger;
import ChainOfResponsiblity.OrderLogger;
import ChainOfResponsiblity.StaffLogger;
import Factory.BookFactory;
import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MainViewController implements Initializable {

    @FXML
    private TableView<Mediator.BooksMediator> tb_item_list;
    @FXML
    private ComboBox<String> combo_order_item_categ;
    @FXML
    private TableView<Mediator.BooksMediator> tb_order_list;
    @FXML
    private Button btn_order_item_categ_refresh;
    @FXML
    private Button btn_add_order_item;
    @FXML
    private TextField field_order_qty;

    @FXML
    private TableView<Mediator.BooksMediator> tb_items;
    @FXML
    private TableColumn<?, ?> col_item_id;
    @FXML
    private TableColumn<?, ?> col_item_name;
    @FXML
    private TableColumn<?, ?> col_item_price;
    @FXML
    private TableColumn<?, ?> col_item_categ_name;
    @FXML
    private TextField field_item_name;
    @FXML
    private TextField field_item_price;
    @FXML
    private Button btn_item_item_categ_refresh;
    @FXML
    private Button btn_item_item_tb_refresh;
    @FXML
    private Button btn_item_submit;
    @FXML
    private TableView<Mediator.AuthorMediator> tb_item_category;
    @FXML
    private Button btn_refresh_item_categ;
    @FXML
    private Button btn_submit_item_categ;
    @FXML
    private TextField field_item_categ_name;
    @FXML
    private TableView<Mediator.StaffMediator> tb_staff;
    @FXML
    private TableColumn<?, ?> col_staff_id;
    @FXML
    private TableColumn<?, ?> col_staff_name;
    @FXML
    private TableColumn<?, ?> col_staff_email;
    @FXML
    private TableColumn<?, ?> col_staff_password;
    @FXML
    private TableColumn<?, ?> col_staff_uname;
    @FXML
    private Button btn_staff_refresh;
    @FXML
    private Button btn_staff_submit;
    @FXML
    private TableColumn<?, ?> col_item_list_id;
    @FXML
    private TableColumn<?, ?> col_item_list_item_name;
    @FXML
    private TableColumn<?, ?> col_item_list_unit_price;
    @FXML
    private Label lbl_total;
    @FXML
    private Button btn_order_remove_item;
    @FXML
    private Button btn_order_generate_bill;
    @FXML
    private ComboBox<String> combo_item_categ_sel;
    static String user_id = "0";
    public static ObservableList<Mediator.BooksMediator> Books_list = FXCollections.observableArrayList();
    public static ObservableList<Mediator.AuthorMediator> BooksAuthor_List = FXCollections.observableArrayList();
    public static ObservableList<Mediator.StaffMediator> Staff_List = FXCollections.observableArrayList();
    public static ObservableList<Mediator.BooksMediator> Order_List = FXCollections.observableArrayList();

    ///////////////////////////////////ChainOfResponsiblity////////////////////////////////////////////////////////////////////////////////
    private static ChainOfResponsiblity.AbstractLogger getChainOfLoggers() {
        AbstractLogger AuthorLog = new BooksAuthorLogger(AbstractLogger.BOOKAUTHOR);
        AbstractLogger Books = new BooksLogger(AbstractLogger.BOOK);
        AbstractLogger staffLog = new StaffLogger(AbstractLogger.STAFF);
        AbstractLogger orderLog = new OrderLogger(AbstractLogger.ORDER);
        AuthorLog.setNextLogger(Books);
        Books.setNextLogger(staffLog);
        staffLog.setNextLogger(orderLog);
        return AuthorLog;
    }
    AbstractLogger loggerChain = getChainOfLoggers();
    //////////////////////////////////ChainOfResponsiblity End/////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////Factory /////////////////////////////////////////////////////////////////////////////////
    Factory.BookFactory bookFactory = new BookFactory();
    //////////////////////////////////Factory End/////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField field_staff_name;
    @FXML
    private TextField field_staff_email;
    @FXML
    private TextField field_staff_password;
    @FXML
    private TextField field_staff_username;
    @FXML
    private TableColumn<?, ?> col_categ_name;
    @FXML
    private TableColumn<?, ?> col_categ_id;
    @FXML
    private TableColumn<?, ?> col_order_list_item_name;
    @FXML
    private TableColumn<?, ?> col_order_list_qty;
    @FXML
    private TableColumn<?, ?> col_order_list_unit_price;
    @FXML
    private TableColumn<?, ?> col_order_list_total_price;
    @FXML
    private TableColumn<?, ?> col_order_list_item_id;
    @FXML
    private TableView<Mediator.OrderMediator> tb_order_disp;
    @FXML
    private TableColumn<?, ?> col_order_disp_id;
    @FXML
    private TableColumn<?, ?> col_order_disp_qty;
    @FXML
    private TableColumn<?, ?> col_order_disp_timestmp;
    @FXML
    private TableColumn<?, ?> col_order_disp_staff_name;
    @FXML
    private TableColumn<?, ?> col_order_disp_amt;
    @FXML
    private TableView<Mediator.BooksMediator> tb_order_disp_item;
    @FXML
    private TableColumn<?, ?> col_order_disp_item_id;
    @FXML
    private TableColumn<?, ?> col_order_disp_item_name;
    @FXML
    private TableColumn<?, ?> col_order_disp_item_total;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComboAuthor();
        orderDisp();
        NumericField(field_order_qty);
        btn_order_remove_item.setDisable(true);
        btn_add_order_item.setDisable(true);
    }

    @FXML
    private void tb_item_list_onclick(MouseEvent event) {
        int index = tb_item_list.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            btn_add_order_item.setDisable(false);

        }
    }

    @FXML
    private void tb_order_list_onclick(MouseEvent event) {
        int index = tb_order_list.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            btn_order_remove_item.setDisable(false);
        }
    }

    @FXML
    private void btn_order_item_categ_refresh_onclick(MouseEvent event) {
        init();
    }

    @FXML
    private void btn_add_order_item_onclick(MouseEvent event) {
        if (field_order_qty.getText().length() > 0) {

            int index = tb_item_list.getSelectionModel().getSelectedIndex();
            if (index >= 0) {
                Mediator.BooksMediator rc = tb_item_list.getItems().get(index);
                rc.setCol_books_qty(field_order_qty.getText());
                rc.setCol_books_total(String.valueOf((Integer.parseInt(rc.getCol_price()) * Integer.parseInt(field_order_qty.getText()))));
 
                Order_List.add(rc);
            }

            col_order_list_item_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
            col_order_list_item_name.setCellValueFactory(new PropertyValueFactory<>("col_title"));
            col_order_list_unit_price.setCellValueFactory(new PropertyValueFactory<>("col_price"));
            col_order_list_qty.setCellValueFactory(new PropertyValueFactory<>("col_books_qty"));
            col_order_list_total_price.setCellValueFactory(new PropertyValueFactory<>("col_books_total"));
            tb_order_list.setItems(Order_List);
            btn_order_remove_item.setDisable(false);
            calc_total_amt();
        }

    }

    void calc_total_amt() {
        int total_amt = 0;
        for (int i = 0; i < Order_List.size(); i++) {
            total_amt += Integer.parseInt(Order_List.get(i).getCol_books_qty()) * Integer.parseInt(Order_List.get(i).getCol_price());
        }
        lbl_total.setText(String.valueOf(total_amt));

    }

    void NumericField(TextField txtfield) {
        txtfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}(\\d{0,4})?")) {
                    txtfield.setText(oldValue);
                }
            }

        });

    }

    @FXML
    private void btn_order_remove_item_onclick(MouseEvent event) {
        int index = tb_order_list.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            Mediator.BooksMediator rc = tb_order_list.getItems().get(index);
            Order_List.remove(rc);

            tb_order_list.setItems(Order_List);
            btn_order_remove_item.setDisable(true);
            calc_total_amt();
        }
    }

    @FXML
    private void tb_items_onclick(MouseEvent event) {

    }

    @FXML
    private void btn_item_item_categ_refresh_onclick(MouseEvent event) {
        initComboAuthor();
    }

    @FXML
    private void btn_item_item_tb_refresh_onclick(MouseEvent event) {
        Factory.Book std = bookFactory.getFactory("book");
        std.show(std);
        col_item_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
        col_item_name.setCellValueFactory(new PropertyValueFactory<>("col_title"));
        col_item_price.setCellValueFactory(new PropertyValueFactory<>("col_price"));
        col_item_categ_name.setCellValueFactory(new PropertyValueFactory<>("col_author_name"));

        tb_items.setItems(Books_list);
    }

    @FXML
    private void btn_item_submit_onclick(MouseEvent event) {
        Mediator.BooksMediator itemb = new BooksMediator();
        String book_author_id = combo_item_categ_sel.getValue().split(" - ")[0];
        itemb.setTitle(field_item_name.getText());
        itemb.setPrice(field_item_price.getText());
        itemb.setAuthor_id(book_author_id);

        loggerChain.logMessage(AbstractLogger.BOOK, itemb);
    }

    @FXML
    private void btn_staff_refresh_onclick(MouseEvent event) {
        Factory.Book std = bookFactory.getFactory("staff");
        std.show(std);
        col_staff_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
        col_staff_name.setCellValueFactory(new PropertyValueFactory<>("col_name"));
        col_staff_email.setCellValueFactory(new PropertyValueFactory<>("col_email"));
        col_staff_password.setCellValueFactory(new PropertyValueFactory<>("col_password"));
        col_staff_uname.setCellValueFactory(new PropertyValueFactory<>("col_username"));

        tb_staff.setItems(Staff_List);
    }

    @FXML
    private void btn_staff_submit_onclick(MouseEvent event) {
        Mediator.StaffMediator itemb = new StaffMediator();
        itemb.setName(field_staff_name.getText());
        itemb.setEmail(field_staff_email.getText());
        itemb.setPassword(field_staff_password.getText());
        itemb.setUsername(field_staff_username.getText());

        loggerChain.logMessage(AbstractLogger.STAFF, itemb);
    }

    @FXML
    private void btn_submit_item_categ_onclick(MouseEvent event) {

        Mediator.AuthorMediator itemb = new AuthorMediator();
        itemb.setAuthor_Name(field_item_categ_name.getText());
        loggerChain.logMessage(AbstractLogger.BOOKAUTHOR, itemb);

    }

    @FXML
    private void btn_refresh_item_categ_onclick(MouseEvent event) {
        Factory.Book std = bookFactory.getFactory("author");
        std.show(std);
        col_categ_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
        col_categ_name.setCellValueFactory(new PropertyValueFactory<>("col_author_name"));

        tb_item_category.setItems(BooksAuthor_List);
    }

    @FXML
    private void btn_order_generate_bill_onclick(MouseEvent event) {
        Mediator.OrderMediator orderb = new OrderMediator();
        int qty = 0;
        int total = 0;
        for (int i = 0; i < Order_List.size(); i++) {
            qty += Integer.parseInt(Order_List.get(i).getCol_books_qty());
            total += Integer.parseInt(Order_List.get(i).getCol_books_total());
        }

        orderb.setAmount(String.valueOf(total));
        orderb.setItem_qty(String.valueOf(qty));
        orderb.setStaff_id(user_id);
        orderb.add(Order_List);

        loggerChain.logMessage(AbstractLogger.ORDER, orderb);
        Order_List.clear();
        tb_order_list.setItems(Order_List);
        btn_order_remove_item.setDisable(true);
    }

    void initComboAuthor() {
        combo_item_categ_sel.getItems().clear();
        String query = "SELECT * FROM `tb_author`  ";
        Statement st = null;
        ResultSet rs = null;
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                combo_item_categ_sel.getItems().add(String.valueOf(rs.getInt("id")) + " - " + rs.getString("author_name"));
            }
            combo_item_categ_sel.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void init() {

        ArrayList<Mediator.AuthorMediator> author_List = new ArrayList<Mediator.AuthorMediator>();
        if (combo_order_item_categ.getItems().size() > 0) {
            combo_order_item_categ.getItems().clear();
        }
        String query = "SELECT * FROM `tb_author`  ";
        Statement st = null;
        ResultSet rs = null;
        Mediator.AuthorMediator BooksAuthorMediator;

        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                BooksAuthorMediator = new AuthorMediator();
                BooksAuthorMediator.BooksList = FXCollections.observableArrayList();
                combo_order_item_categ.getItems().add(String.valueOf(rs.getInt("id")) + " - " + rs.getString("author_name"));
                BooksAuthorMediator.setId(String.valueOf(rs.getInt("id")));
                BooksAuthorMediator.setAuthor_Name(rs.getString("author_name"));
                String queryE = "SELECT b.*,a.`author_name` FROM `tb_books` b\n"
                        + "LEFT JOIN `tb_author` a ON b.author_id =a.id  "
                        + "WHERE b.`author_id`= " + BooksAuthorMediator.getId() + " ;";
                System.out.println("qu" + queryE);
                Statement ste = null;
                ResultSet rse = null;
                Mediator.BooksMediator books_mediator = new BooksMediator();
                try {
                    ste = (Statement) connection.Connect.con.createStatement();
                    rse = ste.executeQuery(queryE);
                    while (rse.next()) {
                        books_mediator = new Mediator.BooksMediator();
                        books_mediator.setCol_id(String.valueOf(rse.getInt("id")));
                        books_mediator.setCol_title(rse.getString("title").substring(0, 1).toUpperCase() + rse.getString("title").substring(1));
                        books_mediator.setCol_price(String.valueOf(rse.getInt("price")));
                        books_mediator.setCol_author_name(rse.getString("author_name").substring(0, 1).toUpperCase() + rse.getString("author_name").substring(1));
                        books_mediator.setAuthor_id(String.valueOf(rse.getInt("author_id")));

                        BooksAuthorMediator.add(books_mediator);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
                author_List.add(BooksAuthorMediator);

            }
            combo_order_item_categ.getSelectionModel().selectFirst();
        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo_order_item_categ.valueProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue ov, String t, String val) {
                if (!(author_List.isEmpty())) {
                    col_item_list_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
                    col_item_list_item_name.setCellValueFactory(new PropertyValueFactory<>("col_title"));
                    col_item_list_unit_price.setCellValueFactory(new PropertyValueFactory<>("col_price"));
                    tb_item_list.setItems(author_List.get(combo_order_item_categ.getSelectionModel().getSelectedIndex()).getBooksList());
                    btn_add_order_item.setDisable(true);
                }
            }
        });
    }

    @FXML
    private void tb_order_disp_onclick(MouseEvent event) {

        int index = tb_order_disp.getSelectionModel().getSelectedIndex();

        if (index > -1) {
            Mediator.OrderMediator oc = tb_order_disp.getItems().get(index);
            List<Mediator.BooksMediator> iArray = new ArrayList<>();
            String query = "SELECT o.*,f.`title` AS itemName ,(o.`qty`*f.`price`) AS ItemTotal  FROM `tb_order_details` o  "
                    + "LEFT JOIN tb_books f ON o.`item_id`=f.`id` WHERE o.order_id= " + oc.getCol_id();
            System.out.println("" + query);
            Statement st = null;
            ResultSet rs = null;
            Mediator.BooksMediator booksMedaitor;
            try {
                st = (Statement) connection.Connect.con.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) { 
                    booksMedaitor = new Mediator.BooksMediator();
                    booksMedaitor.setCol_id(String.valueOf(rs.getInt("id")));
                    booksMedaitor.setCol_title(rs.getString("itemName"));
                    booksMedaitor.setCol_price(String.valueOf(rs.getInt("ItemTotal")));

                    iArray.add(booksMedaitor);
                }
                ObservableList<Mediator.BooksMediator> data = FXCollections.observableArrayList(iArray);
                col_order_disp_item_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
                col_order_disp_item_name.setCellValueFactory(new PropertyValueFactory<>("col_title"));
                col_order_disp_item_total.setCellValueFactory(new PropertyValueFactory<>("col_price"));

                tb_order_disp_item.setItems(data);

            } catch (SQLException ex) {
                Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    void orderDisp() {

        List<Mediator.OrderMediator> iArray = new ArrayList<>();
        String query = "SELECT o.*,s.`name` AS staffName FROM `tb_order` o LEFT JOIN `tb_staff` s ON o.`staff_id`=s.id  ";
        System.out.println("" + query);
        Statement st = null;
        ResultSet rs = null;
        Mediator.OrderMediator orderMediator;
        try {
            st = (Statement) connection.Connect.con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                orderMediator = new Mediator.OrderMediator();

                orderMediator.setCol_id(String.valueOf(rs.getInt("id")));
                orderMediator.setCol_item_qty(String.valueOf(rs.getInt("item_qty")));
                orderMediator.setCol_order_date((rs.getString("order_date") + " - " + rs.getString("order_time")));
                orderMediator.setCol_amount(String.valueOf(rs.getInt("amount")));
                orderMediator.setCol_staff_name((rs.getString("staffName")));
                iArray.add(orderMediator);
            }
            ObservableList<Mediator.OrderMediator> data = FXCollections.observableArrayList(iArray);
            col_order_disp_id.setCellValueFactory(new PropertyValueFactory<>("col_id"));
            col_order_disp_qty.setCellValueFactory(new PropertyValueFactory<>("col_item_qty"));
            col_order_disp_amt.setCellValueFactory(new PropertyValueFactory<>("col_amount"));
            col_order_disp_timestmp.setCellValueFactory(new PropertyValueFactory<>("col_order_date"));
            col_order_disp_staff_name.setCellValueFactory(new PropertyValueFactory<>("col_staff_name"));

            tb_order_disp.setItems(data);

        } catch (SQLException ex) {
            Logger.getLogger(MainViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btn_order_disp_refresh(MouseEvent event) {
        orderDisp();
    }
}
