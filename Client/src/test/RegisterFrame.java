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
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class RegisterFrame extends JFrame {

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
    ImageIcon userimage_icon = new ImageIcon(getClass().getResource("/res/adduser2.png"));
    JLabel user_label = new JLabel("User ID :");
    JLabel password_label = new JLabel("Password :");
    JLabel confirmpassword_label = new JLabel("Conf. Password :");
    JLabel userimage_label = new JLabel(userimage_icon);
    JLabel returnlogin_label = new JLabel("Torna al Login");
    JLabel checkid_label = new JLabel("Vuoto");
    JLabel checkpassword_label = new JLabel("Vuoto");
    JLabel checkconfirmpassword_label = new JLabel("Vuoto");

    JButton register_button = new JButton("REGISTRATI");

    JTextField user_textfield = new JTextField();
    JPasswordField password_textfield = new JPasswordField();
    JPasswordField confirmpassword_textfield = new JPasswordField();

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int counter = 3;
    int x = (int) d.getWidth();
    int y = (int) d.getHeight();

    public RegisterFrame() {
        setLayout(null);
        setLocation(x / 2 - 200, 100);
        setSize(400, 500);
        setVisible(true);
        setResizable(false);
        setTitle("Register");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(user_textfield);
        add(password_textfield);
        add(confirmpassword_textfield);
        add(user_label);
        add(userimage_label);
        add(password_label);
        add(confirmpassword_label);
        add(returnlogin_label);
        add(checkid_label);
        add(checkpassword_label);
        add(checkconfirmpassword_label);
        add(register_button);

        register_button.addActionListener(new RegisterActions());
        register_button.setBounds(140, 410, 120, 20);

        user_textfield.addKeyListener(new IDTextFieldActions());
        user_textfield.setBounds(120, 300, 170, 20);
        password_textfield.addKeyListener(new PasswordTextFieldActions());
        password_textfield.setBounds(120, 340, 170, 20);
        confirmpassword_textfield.addKeyListener(new ConfirmPasswordTextFieldActions());
        confirmpassword_textfield.setBounds(120, 380, 170, 20);

        user_label.setBounds(50, 300, 100, 20);
        userimage_label.setBounds(70, 100, 240, 170);
        password_label.setBounds(50, 340, 100, 20);
        confirmpassword_label.setBounds(20, 380, 120, 20);
        returnlogin_label.setForeground(Color.gray);
        returnlogin_label.addMouseListener(new ReturnLoginLabelActions());
        returnlogin_label.setBounds(310, 450, 120, 20);
        checkid_label.setBounds(300, 300, 100, 20);
        checkid_label.setVisible(false);
        checkpassword_label.setBounds(300, 340, 100, 20);
        checkpassword_label.setVisible(false);
        checkconfirmpassword_label.setBounds(300, 380, 100, 20);
        checkconfirmpassword_label.setVisible(false);

    }

    public class RegisterActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            int counter = 3;
            if (checkid_label.getText().equals("Vuoto")) {
                counter--;
            }
            if (checkpassword_label.getText().equals("Vuoto")) {
                counter--;
            }
            if (checkconfirmpassword_label.getText().equals("Vuoto")) {
                counter--;
            }

            if (counter == 3) {
                if (String.valueOf(password_textfield.getPassword()).equals(String.valueOf(confirmpassword_textfield.getPassword()))) {
                    
                    try {
                        outForServer.writeBytes("REGISTER");
                        outForServer.writeBytes(newLine);
                        outForServer.writeBytes(user_textfield.getText());
                        outForServer.writeBytes(newLine);
                        outForServer.writeBytes(String.valueOf(password_textfield.getPassword()));
                    
                        outForServer.writeBytes(newLine);
                    } catch (IOException ex) {
                        
                    }
                     
                    
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Registrazione effettuata con successo, ritorno al Login");
                    try {
                        outForServer.writeBytes("END");
                        outForServer.writeBytes(newLine);
                        socket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(RegisterFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                    RegisterFrame.this.dispose();
                    new LoginFrame().connect(serverName);
                } else {
                    JOptionPane.showMessageDialog(RegisterFrame.this, "Le password non coincidono");
                }

            } else {
                JOptionPane.showMessageDialog(RegisterFrame.this, "Uno o più campi sono vuoti");
            }

        }

    }

    public class ReturnLoginLabelActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            try {
                    outForServer.writeBytes("END");
                    outForServer.writeBytes(newLine);
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            RegisterFrame.this.dispose();
            new LoginFrame().connect(serverName);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            setCursor(Cursor.HAND_CURSOR);
        }

        public void mouseExited(MouseEvent me) {
            setCursor(Cursor.DEFAULT_CURSOR);
        }

    }

    public class IDTextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

            if (user_textfield.getText().trim().isEmpty()) {

                checkid_label.setVisible(true);
                checkid_label.setText("Vuoto");
                checkid_label.setForeground(Color.red);
            } else {
                checkid_label.setForeground(Color.red);
                checkid_label.setVisible(true);
                checkid_label.setText("Ok");
                checkid_label.setForeground(Color.green);

            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (user_textfield.getText().trim().isEmpty()) {

                checkid_label.setVisible(true);
                checkid_label.setText("Vuoto");
                checkid_label.setForeground(Color.red);
            } else {
                checkid_label.setForeground(Color.red);
                checkid_label.setVisible(true);
                checkid_label.setText("Ok");
                checkid_label.setForeground(Color.green);

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

    public class ConfirmPasswordTextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

            if (String.valueOf(confirmpassword_textfield.getPassword()).trim().isEmpty()) {

                checkconfirmpassword_label.setVisible(true);
                checkconfirmpassword_label.setText("Vuoto");
                checkconfirmpassword_label.setForeground(Color.red);
            } else {
                checkconfirmpassword_label.setForeground(Color.red);
                checkconfirmpassword_label.setVisible(true);
                checkconfirmpassword_label.setText("Ok");
                checkconfirmpassword_label.setForeground(Color.green);

            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (String.valueOf(confirmpassword_textfield.getPassword()).trim().isEmpty()) {

                checkconfirmpassword_label.setVisible(true);
                checkconfirmpassword_label.setText("Vuoto");
                checkconfirmpassword_label.setForeground(Color.red);
            } else {
                checkconfirmpassword_label.setForeground(Color.red);
                checkconfirmpassword_label.setVisible(true);
                checkconfirmpassword_label.setText("Ok");
                checkconfirmpassword_label.setForeground(Color.green);

            }
        }

    }

}
