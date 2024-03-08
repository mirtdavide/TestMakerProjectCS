/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author soprasteria
 */
public class DataManagement {
    String s;
    FileReader f;
    BufferedReader fbuf;
    FileWriter fw;
    BufferedWriter fwbuf;
    ArrayList <User> user_array=new ArrayList();
    
    public DataManagement(){
        readUserFile();
    }
    
    public int findUser(String id,String p){
        for(int i=0;i<user_array.size();i++){
            if(id.equals(user_array.get(i).getId())&&p.equals(user_array.get(i).getPassword())){
                return i;
            }
        }
        return -1;
    }
    
    
    
    public void writeUserFile(String id,String p){
        
        String s=id+";"+p+";"+"false";
        try {
            fw=new FileWriter("Users.txt");
            fwbuf=new BufferedWriter(fw);
        } catch (IOException ex) {
            ex.getMessage();
        }
        try {
            for(int i=0;i<user_array.size();i++){
                fwbuf.append(user_array.get(i).getId()+";"+user_array.get(i).getPassword()+";"+user_array.get(i).isAdmin());
                fwbuf.newLine();
            }
            fwbuf.append(s);
            
            fwbuf.newLine();
            
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        try {
            fwbuf.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        try {
            fw.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void readUserFile(){

        String s="";
        
        try {
            f=new FileReader("Users.txt");
            fbuf=new BufferedReader(f);
        } catch (FileNotFoundException ex) {
           ex.getMessage();
        }
        
        try {
            s=fbuf.readLine();
        } catch (IOException ex) {
           ex.getMessage();
        }
        
        do{
          String[] strarray=s.split(";");
          String id=strarray[0];
          String password=strarray[1];
          boolean admin=Boolean.parseBoolean(strarray[2]);
          user_array.add(new User(id,password,admin));
          
            try {
                s=fbuf.readLine();
            } catch (IOException ex) {
                ex.getMessage();
            }
            
        }while(s!=null);
        
        try {
            fbuf.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        try {
            f.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
    }
    
    
    
}
