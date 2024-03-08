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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class AdminFrame extends JFrame{
    
    ImageIcon admin_icon=new ImageIcon(getClass().getResource("/res/admin.png"));
    JLabel admin_label=new JLabel(admin_icon);
    
    JLabel menu_label=new JLabel("Menu");
    JButton create_button=new JButton("CREA TEST");
    
    JButton try_button=new JButton("PROVA TEST");
    JButton logout_button=new JButton("LOGOUT");
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x= (int) d.getWidth();
    int y= (int) d.getHeight();
    String ip;
    
    public AdminFrame(String ipr){
        ip=ipr;
        setLayout(null);
        setLocation(x/2-400,y/2-225);
        setTitle("Admin Menu");
        setSize(800,370);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menu_label.setFont(new Font("Arial", Font.PLAIN, 26));
        
        add(admin_label);
        add(menu_label);
        add(create_button);
        
        add(try_button);
        add(logout_button);
        
        
        admin_label.setBounds(50,20,256,256);
        menu_label.setBounds(515,80,400,30);
        create_button.setBounds(400,130,300,35);
        create_button.addActionListener(new CreateActions());
        logout_button.addActionListener(new LogoutActions());
        try_button.addActionListener(new TryActions());
        try_button.setBounds(400,180,300,35);
        logout_button.setBounds(400,230,300,35);
        
        
        
        
    }
    
    public class LogoutActions implements ActionListener{

        
        public void actionPerformed(ActionEvent e) {
            AdminFrame.this.dispose();
            new LoginFrame().connect(ip);
        }
        
    }
    
    public class CreateActions implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            AdminFrame.this.dispose();
            new CreateTestFrame().connect(ip);
        }
    }
    
    public class TryActions implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            AdminFrame.this.dispose();
            new SelectFrame("Admin",1,ip);
        }
    }
    
}
