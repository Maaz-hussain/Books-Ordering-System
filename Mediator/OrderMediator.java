package Mediator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class OrderMediator {

    String id,item_qty,order_date,order_time,staff_id,amount;
    SimpleStringProperty col_id,col_item_qty,col_order_date,col_order_time,col_staff_name,col_amount;
    public ObservableList<Mediator.BooksMediator> BooksList;

    public void add(ObservableList<Mediator.BooksMediator> ItemList ) {
        this.BooksList=(ItemList);
    }

    public void remove(Mediator.BooksMediator e) {
        BooksList.remove(e);
    }

    public ObservableList<Mediator.BooksMediator> getbooksList() {
        return BooksList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCol_id() {
        return col_id.get();
    }

    public void setCol_id(String col_id) {
        this.col_id = new SimpleStringProperty (col_id);
    }

    public String getCol_item_qty() {
        return col_item_qty.get();
    }

    public void setCol_item_qty(String col_item_qty) {
        this.col_item_qty = new SimpleStringProperty (col_item_qty);
    }

    public String getCol_order_date() {
        return col_order_date.get();
    }

    public void setCol_order_date(String col_order_date) {
        this.col_order_date =new SimpleStringProperty ( col_order_date);
    }

    public String getCol_order_time() {
        return col_order_time.get();
    }

    public void setCol_order_time(String col_order_time) {
        this.col_order_time = new SimpleStringProperty (col_order_time);
    }

    public String getCol_staff_name() {
        return col_staff_name.get();
    }

    public void setCol_staff_name(String col_staff_name) {
        this.col_staff_name =new SimpleStringProperty ( col_staff_name);
    }

    public String getCol_amount() {
        return col_amount.get();
    }

    public void setCol_amount(String col_amount) {
        this.col_amount = new SimpleStringProperty (col_amount);
    }
    
    
}
