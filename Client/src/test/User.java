/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author soprasteria
 */
public class User {
    
    String id;
    String password;
    boolean admin;

    public User() {
    }

    public User(String id, String password, boolean admin) {
        this.id = id;
        this.password = password;
        this.admin = admin;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", admin=" + admin + '}';
    }
    
    
    
    
    
    
}
