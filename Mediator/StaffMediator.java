package Mediator;

import javafx.beans.property.SimpleStringProperty;

public class StaffMediator {
    private String id,name,email,username,password;
    SimpleStringProperty col_id,col_name,col_email,col_username,col_password;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCol_id() {
        return col_id.get();
    }

    public void setCol_id(String col_id) {
        this.col_id = new SimpleStringProperty(col_id);
    }

    public String getCol_name() {
        return col_name.get();
    }

    public void setCol_name(String col_name) {
        this.col_name =new SimpleStringProperty( col_name);
    }

    public String getCol_email() {
        return col_email.get();
    }

    public void setCol_email(String col_email) {
        this.col_email =new SimpleStringProperty(col_email);
    }

    public String getCol_username() {
        return col_username.get();
    }

    public void setCol_username(String col_username) {
        this.col_username = new SimpleStringProperty (col_username);
    }

    public String getCol_password() {
        return col_password.get();
    }

    public void setCol_password(String col_password) {
        this.col_password = new SimpleStringProperty (col_password);
    }
    
}
