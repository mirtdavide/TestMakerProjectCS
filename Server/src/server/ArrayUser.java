/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.*;

/**
 *
 * @author CC1-PC14
 */
public class ArrayUser {

    ArrayList<User> a;

    public ArrayUser() {

        a = new ArrayList();

    }

    public int add(String user, String password){

        boolean flagn = false;
        User u = new User(user, password,false);

        if (a.isEmpty()) {

            a.add(u);

        } else {

            for (int i = 0; i < a.size(); i++) {

                if (a.get(i).getId().equals(user) && a.get(i).getPassword().equals(password)) {
                    
                    

                    flagn = true;

                }

            }

            if (flagn== true) {
                
                System.out.println("Sono uguali");
                return -2;

            } else {
                
                System.out.println("Non lo sono");
                a.add(u);
                return 1;

            }

           

        }

        return 1;

    }

}
