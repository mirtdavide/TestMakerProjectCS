/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Davide
 */
public class UserFrame extends JFrame{
    
    
    
    
    ImageIcon userimage_icon=new ImageIcon(getClass().getResource("/res/user.png"));
    JButton play_button=new JButton("GIOCA TEST");
    JButton scores_button=new JButton("GUARDA I PUNTEGGI");
    JButton logout_button=new JButton("LOGOUT");
    JLabel userimage_label=new JLabel(userimage_icon);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x= (int) d.getWidth();
    int y= (int) d.getHeight();
    
    
    String ip;
    String user;
    
    
    
    public UserFrame(String User,String ipr){
        ip=ipr;
        user=User;
        setLayout(null);
        setLocation(x/2-200,100);
        setSize(350,400);
        setVisible(true);
        setResizable(false);
        setTitle("User Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(userimage_label);
        userimage_label.setBounds(175-120,40,240,170);
        add(play_button);
        
        add(scores_button);
        add(logout_button);
        logout_button.addActionListener(new LogoutActions());
        play_button.addActionListener(new PlayActions());
        play_button.setBounds(175-100,230,200,25);
        
        scores_button.setBounds(175-100,265,200,25);
        scores_button.addActionListener(new ScoresActions());
        logout_button.setBounds(175-100,295,200,25);
        
        
        
    }
    
    
    public class LogoutActions implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            
            UserFrame.this.dispose();
            new LoginFrame().connect(ip);
        }
        
    }
    
    
    public class PlayActions implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            UserFrame.this.dispose();
            new SelectFrame(user,1,ip).connect(ip);
        }
        
    }
    
    public class ScoresActions implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            UserFrame.this.dispose();
            
            new SelectFrame(user,2,ip).connect(ip);
        }
        
    }
    
    
}
