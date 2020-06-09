package Mediator;

import javafx.beans.property.SimpleStringProperty;

public class BooksMediator {
    String id,title,price,author_id,author_name,iban_no;
    SimpleStringProperty col_id,col_title,col_price,col_author_id,col_author_name,col_books_qty,col_books_total,col_iban_no;

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String food_categ_id) {
        this.author_id = food_categ_id;
    }

    public String get_author_name() {
        return author_name;
    }

    public void setAuthor_name(String food_categ_name) {
        this.author_name = food_categ_name;
    }

    public String getIban_no() {
        return iban_no;
    }

    public void setIban_no(String iban_no) {
        this.iban_no = iban_no;
    }

    public String getCol_iban_no() {
        return col_iban_no.get();
    }

    public void setCol_iban_no(String col_iban_no) {
        this.col_iban_no = new SimpleStringProperty(col_iban_no);
    }

    public String getCol_id() {
        return col_id.get();
    }

    public void setCol_id(String col_id) {
        this.col_id = new SimpleStringProperty (col_id);
    }
                
    public String getCol_title() {
        return col_title.get();
    }

    public void setCol_title(String col_name) {
        this.col_title = new SimpleStringProperty(col_name);
    }

    public String getCol_price() {
        return col_price.get();
    }

    public void setCol_price(String col_price) {
        this.col_price = new SimpleStringProperty(col_price);
    }

    public String getCol_Author_id() {
        return col_author_id.get();
    }

    public void setCol_Author_id(String col_food_categ_id) {
        this.col_author_id =new SimpleStringProperty (col_food_categ_id);
    }

    public String getCol_author_name() {
        return col_author_name.get();
    }

    public void setCol_author_name(String col_food_categ_name) {
        this.col_author_name = new SimpleStringProperty(col_food_categ_name);
    }

    public String getCol_books_qty() {
        return col_books_qty.get();
    }

    public void setCol_books_qty(String col_item_qty) {
        this.col_books_qty =  new SimpleStringProperty(col_item_qty);
    }

    public String getCol_books_total() {
        return col_books_total.get();
    }

    public void setCol_books_total(String col_item_total) {
        this.col_books_total =  new SimpleStringProperty(col_item_total);
    }
    
    
    
    
}
