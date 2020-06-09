package Mediator;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class AuthorMediator {

    String id, author_name;
    SimpleStringProperty col_id, col_author_name;
    public ObservableList<Mediator.BooksMediator> BooksList;

    public void add(Mediator.BooksMediator e) {
        BooksList.add(e);
    }

    public void remove(Mediator.BooksMediator e) {
        BooksList.remove(e);
    }

    public ObservableList<Mediator.BooksMediator> getBooksList() {
        return BooksList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_Name() {
        return author_name;
    }

    public void setAuthor_Name(String name) {
        this.author_name = name;
    }

    public String getCol_id() {
        return col_id.get();
    }

    public void setCol_id(String col_id) {
        this.col_id = new SimpleStringProperty(col_id);
    }

    public String getCol_author_name() {
        return col_author_name.get();
    }

    public void setCol_author_name(String col_author_name) {
        this.col_author_name = new SimpleStringProperty(col_author_name);
    }

}
