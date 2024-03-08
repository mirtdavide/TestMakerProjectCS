/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author soprasteria
 */
public class LoginFrame extends JFrame {
    String newLine = System.getProperty("line.separator");
    String serverName = "localhost";
    int portNumber = 6789;
    Socket socket;
    String userString;
    String recString;
    DataOutputStream outForServer;
    BufferedReader inFromServer;
   
    JFrame ip_frame=new JFrame();
    JTextField ip_tfield=new JTextField();
    JLabel ip_label=new JLabel();
    JButton ok_b=new JButton();
    
    
    
    
        public  void insert(){
            ip_frame.add(ip_tfield);
            ip_frame.add(ip_label);
            ip_frame.add(ok_b);
            
            ip_frame.setSize(250, 170);
            ip_frame.setLayout(null);
            ip_label.setText("IP");
            ip_label.setBounds(20,40,100,20);
            ip_tfield.setBounds(60,40,100,20);
            
            ip_frame.setVisible(true);
            ok_b.setText("CONNETTI");
            ok_b.setBounds(30, 70, 150, 30);
            ok_b.addActionListener(new InsertActions());
            LoginFrame.this.setVisible(false);
            
            
        }
    
    
    
    
        public Socket connect(String ip) {

            
            
            
     
            
          
            
        

        try {
            
             
             
             
             
            
            
            
            serverName=ip;
      
            socket = new Socket(serverName, portNumber);
            outForServer = new DataOutputStream(socket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during the connection!");
            System.exit(1);
        }
        LoginFrame.this.setVisible(true);

        return socket;
        
        
       
    }
    
    
    
    
    
    

    
    
    ImageIcon userimage_icon = new ImageIcon(getClass().getResource("/res/user.png"));

    JLabel user_label = new JLabel("User ID :");
    JLabel password_label = new JLabel("Password :");
    JLabel userimage_label = new JLabel(userimage_icon);
    JLabel checkuser_label = new JLabel("Vuoto");
    JLabel checkpassword_label = new JLabel("Vuoto");

    JButton login_button = new JButton("LOGIN");
    JButton register_button = new JButton("REGISTRATI");

    JTextField user_textfield = new JTextField();
    JPasswordField password_textfield = new JPasswordField();

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) d.getWidth();
    int y = (int) d.getHeight();

    int result;

    public LoginFrame() {
        
        setLayout(null);
        setLocation(x / 2 - 200, 100);
        setSize(400, 500);
        setVisible(true);
        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(user_textfield);
        add(password_textfield);
        add(user_label);
        add(userimage_label);
        add(password_label);
        add(checkuser_label);
        add(checkpassword_label);
        add(login_button);
        add(register_button);

        login_button.addActionListener(new LoginActions());
        login_button.setBounds(145, 380, 110, 20);
        register_button.addActionListener(new RegisterActions());
        register_button.setBounds(145, 410, 110, 20);

        user_textfield.addKeyListener(new IDTextFieldActions());
        user_textfield.setBounds(120, 300, 170, 20);
        password_textfield.addKeyListener(new PasswordTextFieldActions());
        password_textfield.setBounds(120, 340, 170, 20);

        user_label.setBounds(50, 300, 100, 20);
        checkuser_label.setVisible(false);
        checkuser_label.setBounds(300, 300, 100, 20);
        checkpassword_label.setVisible(false);
        checkpassword_label.setBounds(300, 340, 100, 20);

        userimage_label.setBounds(70, 100, 240, 170);
        password_label.setBounds(50, 340, 100, 20);

    }
    
    
    public class InsertActions implements ActionListener{

    
        public void actionPerformed(ActionEvent e) {
            
            
            if(ip_tfield.getText().isEmpty()){
                
                
            JOptionPane.showMessageDialog(LoginFrame.this, "Inserisci un ip!");
                
                
            }else{
                
                connect(ip_tfield.getText());
                ip_frame.setVisible(false);
                
                
            }
            
        }
        
        
        
    }
    
    
    

    public class LoginActions implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            int counter = 2;
            
            if (checkuser_label.getText().equals("Vuoto")) {
                counter--;
            }
            if (checkpassword_label.getText().equals("Vuoto")) {
                counter--;
            }

            if (counter == 2) {
                try {
                    outForServer.writeBytes("LOGIN");
                    outForServer.writeBytes(newLine);
                    outForServer.writeBytes(user_textfield.getText());
                    outForServer.writeBytes(newLine);
                    outForServer.writeBytes(String.valueOf(password_textfield.getPassword()));
                    outForServer.writeBytes(newLine);
                    
                    result = Integer.parseInt(inFromServer.readLine());
                    
                } catch (IOException ex) {
                    
                }
                if (result >= 0) {
                    String user="";
                    try {
                      user=inFromServer.readLine();
                    } catch (IOException ex) {
                        
                    }
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login effettuato con successo, benvenuto " + user);
                    
                    
                        if(user.equals("Admin")){
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            
                        }
                            LoginFrame.this.dispose();
                            new AdminFrame(ip_tfield.getText());
                        }else{
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            
                        }
                            LoginFrame.this.dispose();
                            new UserFrame(user,ip_tfield.getText());
                        }
                    
                    
                    
                } if(result==-1){
                    
                    //Mettere switch alla fine
                    
                    JOptionPane.showMessageDialog(LoginFrame.this, "Utente non trovato");
                }if(result==-2){
                    JOptionPane.showMessageDialog(LoginFrame.this, "Qualcuno è già connesso con quello user");
                    
                }
            } else {
                JOptionPane.showMessageDialog(LoginFrame.this, "Uno o piu campi sono vuoti");
            }
        }
    }

    public class RegisterActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            try {
                outForServer.writeBytes("END");
                outForServer.writeBytes(newLine);
                socket.close();
            } catch (IOException ex) {
                
            }
            LoginFrame.this.dispose();
            
            new RegisterFrame().connect(ip_tfield.getText());
        }

    }

    public class IDTextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

            if (user_textfield.getText().trim().isEmpty()) {

                checkuser_label.setVisible(true);
                checkuser_label.setText("Vuoto");
                checkuser_label.setForeground(Color.red);
            } else {
                checkuser_label.setForeground(Color.red);
                checkuser_label.setVisible(true);
                checkuser_label.setText("Ok");
                checkuser_label.setForeground(Color.green);

            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (user_textfield.getText().trim().isEmpty()) {

                checkuser_label.setVisible(true);
                checkuser_label.setText("Vuoto");
                checkuser_label.setForeground(Color.red);
            } else {
                checkuser_label.setForeground(Color.red);
                checkuser_label.setVisible(true);
                checkuser_label.setText("Ok");
                checkuser_label.setForeground(Color.green);

            }
        }

    }

    public class PasswordTextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

            if (String.valueOf(password_textfield.getPassword()).trim().isEmpty()) {

                checkpassword_label.setVisible(true);
                checkpassword_label.setText("Vuoto");
                checkpassword_label.setForeground(Color.red);
            } else {
                checkpassword_label.setVisible(true);
                checkpassword_label.setForeground(Color.red);
                checkpassword_label.setText("Ok");
                checkpassword_label.setForeground(Color.green);

            }
        }

        public void keyPressed(KeyEvent ke) {

        }

        public void keyReleased(KeyEvent ke) {
            if (String.valueOf(password_textfield.getPassword()).trim().isEmpty()) {

                checkpassword_label.setVisible(true);
                checkpassword_label.setText("Vuoto");
                checkpassword_label.setForeground(Color.red);
            } else {
                checkpassword_label.setVisible(true);
                checkpassword_label.setForeground(Color.red);
                checkpassword_label.setText("Ok");
                checkpassword_label.setForeground(Color.green);

            }
        }

    }

}
