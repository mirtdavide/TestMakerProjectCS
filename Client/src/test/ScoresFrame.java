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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Davide
 */
public class ScoresFrame extends JFrame{
    
    
    
        
    
    
    
        String newLine = System.getProperty("line.separator");
    String serverName = "localhost";
    int portNumber = 6789;
    Socket socket;
    String userString;
    String recString;
    DataOutputStream outForServer;
    BufferedReader inFromServer;
    
    
    
    
    
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

        return socket;

    }
    
    
    
    
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x= (int) d.getWidth();
    int y= (int) d.getHeight();
    JFrame f = new JFrame();
    JList list = new JList();
    JScrollPane jsp = new JScrollPane(list);
    ArrayList as=new ArrayList();
    BinaryFilesManagement bfm=new BinaryFilesManagement();
    
    String p,str;
    public ScoresFrame(String path,String s,String user,String ip){
        
            serverName=ip;
        
            connect(ip);
            
            p=path;
            str=s;
            
            try {
                outForServer.writeBytes("READSCORE");
                outForServer.writeBytes(newLine);
                outForServer.writeBytes(p);
                outForServer.writeBytes(newLine);
                outForServer.writeBytes(str);
                outForServer.writeBytes(newLine);
                
                
                int size=Integer.parseInt(inFromServer.readLine());
                
                for(int i=0;i<size;i++){
                    as.add(inFromServer.readLine());
                }
                
                
                
            } catch (IOException ex) {
                Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Punteggi");
            f.addWindowListener(new Wl(user));
            if(!as.isEmpty()){
                String[] tests = new String[as.size()];
            for (int i = 0; i < as.size(); i++) {
                tests[i] = (String) as.get(i);
            }

            list.setListData(tests);
            jsp = new JScrollPane(list);

            f.getContentPane().add(jsp, BorderLayout.CENTER);

            f.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(ScoresFrame.this, "Non sono presenti punteggi per questo test");
                try {
                    outForServer.writeBytes("END");
                    outForServer.writeBytes(newLine);
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                ScoresFrame.this.dispose();
                new UserFrame(user,serverName);
            }
            
            
            

        
        
    }
    
    public class Wl implements WindowListener{
        
        String userr;
        public Wl(String u){
            userr=u;
        }
        
        public void windowOpened(WindowEvent e) {
        }


        public void windowClosing(WindowEvent e) {
            
        }

      
        public void windowClosed(WindowEvent e) {
            new UserFrame(userr,serverName);
           
        }


        public void windowIconified(WindowEvent e) {
         
        }


        public void windowDeiconified(WindowEvent e) {
        
        }


        public void windowActivated(WindowEvent e) {
         
        }


        public void windowDeactivated(WindowEvent e) {
          
        }
        
    }
}
