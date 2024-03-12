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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.Timer;

/**
 *
 * @author soprasteria
 */
public class PlayTestFrame extends JFrame {

    String newLine = System.getProperty("line.separator");
    String serverName;
    int portNumber = 6789;
    Socket socket;
    String userString;
    String recString;
    DataOutputStream outForServer;
    BufferedReader inFromServer;

    public Socket connect(String ipr) {
        
        try {

            
            
            
            socket = new Socket(ipr, portNumber);
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
    int x = (int) d.getWidth();
    int y = (int) d.getHeight();

    BinaryFilesManagement bfm = new BinaryFilesManagement();
    ArrayList<Question> aq = new ArrayList();
    ArrayList<Answer> aa = new ArrayList();
    ArrayList<String> asc = new ArrayList();

    String arg;

    String testname;
    String us;
    int ndomande, nrisposte, idomande = 0, irisposte = 0;
    int m, s;
    double punti = 0;
    Timer t = new Timer(1000, new taskPerformer());

    ButtonGroup radiobutton_group = new ButtonGroup();
    ImageIcon next_icon = new ImageIcon(getClass().getResource("/res/next.png"));
    JLabel next_label = new JLabel(next_icon);
    JLabel timer_label = new JLabel("Tempo Rimanenete : 10:00");
    JLabel question_label = new JLabel("Domanda ");
    ImageIcon image_icon;
    JLabel image_label = new JLabel(image_icon);
    JRadioButton answer1_radiobutton = new JRadioButton("R1");
    JRadioButton answer2_radiobutton = new JRadioButton("R2");
    JRadioButton answer3_radiobutton = new JRadioButton("R3");
    JRadioButton answer4_radiobutton = new JRadioButton("R4");
    JCheckBox answer1_checkbox = new JCheckBox("R1");
    JCheckBox answer2_checkbox = new JCheckBox("R2");
    JCheckBox answer3_checkbox = new JCheckBox("R3");
    JCheckBox answer4_checkbox = new JCheckBox("R4");
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JLayeredPane lp = new JLayeredPane();
    String ip;

    public PlayTestFrame(String path, String n, String user,String ipr) {
        arg = path;
        testname = n;
        us = user;
        ip=ipr;
        System.out.println("IP PLAYTESTFRAME"+" "+ip);
        connect(ip);
       
        
        try {
            outForServer.writeBytes("READSCORE");
      
            outForServer.writeBytes(newLine);
            outForServer.writeBytes(arg);
          
            outForServer.writeBytes(newLine);
            outForServer.writeBytes(testname);
          
            outForServer.writeBytes(newLine);
            
            int size=Integer.parseInt(inFromServer.readLine());
     
            
            for(int i=0;i<size;i++){
       
                asc.add(inFromServer.readLine());
                
                
            }
            if(asc.size()==0){
                System.out.println("No Scores");
            }else{
                System.out.println(asc.get(0));

            }

        } catch (IOException ex) {
            
        }
        
        
        
        try {
            Question q;
            Answer a;
            System.out.println("sto iniziando");
            outForServer.writeBytes("READ");
            outForServer.writeBytes(newLine);
            outForServer.writeBytes(arg);
            outForServer.writeBytes(newLine);
            outForServer.writeBytes(testname);
            outForServer.writeBytes(newLine);
            
            int aqn=Integer.parseInt(inFromServer.readLine());
            int aan=Integer.parseInt(inFromServer.readLine());
            
            
            System.out.println("ciaooo ho finitoooo9");
            
            for(int i=0;i<aqn;i++){
               
             q = new Question(inFromServer.readLine(), Boolean.parseBoolean(inFromServer.readLine()), inFromServer.readLine(), Boolean.parseBoolean(inFromServer.readLine()));
             aq.add(q);
                System.out.println(aq.get(i));
      
            }
            
            
            
            for(int i=0;i<aan;i++){
          
                 a = new Answer(inFromServer.readLine(), Boolean.parseBoolean(inFromServer.readLine()));
                 aa.add(a);
                 System.out.println(aa.get(i));
        
            }
            
            
            
        } catch (Exception e) {
            
        }
        
        
        ndomande = aq.size();
        nrisposte = aa.size();

        switch (ndomande) {

            case 10:

                m = 10;
                break;

            case 20:
                m = 20;
                break;

            case 30:
                m = 30;
                break;

            case 40:
                m = 40;
                break;

            case 50:
                m = 50;
                break;

            case 60:
                m = 60;
                break;

            case 70:
                m = 70;
                break;

            case 80:
                m = 80;
                break;

            case 90:
                m = 90;
                break;

            case 100:
                m = 100;
                break;

        }

        s = 00;
        radiobutton_group.add(answer1_radiobutton);
        radiobutton_group.add(answer2_radiobutton);
        radiobutton_group.add(answer3_radiobutton);
        radiobutton_group.add(answer4_radiobutton);
        t.start();
        t.setRepeats(true);
        setVisible(true);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(x / 2 - 500, y / 2 - 380);
        setTitle("Test");

        lp.add(question_label, 2, 0);
        lp.add(answer1_radiobutton, 2, 0);
        lp.add(answer2_radiobutton, 2, 0);
        lp.add(answer3_radiobutton, 2, 0);
        lp.add(answer4_radiobutton, 2, 0);

        lp.add(answer1_checkbox, 2, 0);
        lp.add(answer2_checkbox, 2, 0);
        lp.add(answer3_checkbox, 2, 0);
        lp.add(answer4_checkbox, 2, 0);
        lp.add(next_label, 3, 0);

        lp.add(timer_label, 3, 0);
        add(lp);
        lp.setBounds(0, 0, 1000, 700);
        lp.add(image_label, 3, 0);
        lp.add(p1, 1, 0);
        lp.add(p2, 0, 0);
        lp.add(p3, 1, 0);

        p2.setBounds(500 - 310, 30, 600, 620);
        p2.setBorder(BorderFactory.createLineBorder(Color.black));
        p2.setBackground(Color.gray);

        next_label.setBounds(580, 447, 128, 128);
        timer_label.setBounds(10, 30, 170, 25);
        timer_label.setOpaque(true);
        timer_label.setBackground(Color.white);
        timer_label.setBorder(BorderFactory.createLineBorder(Color.black));
        p1.setBounds(500 - 260, 50, 500, 350);
        p1.setBorder(BorderFactory.createLineBorder(Color.black));
        p1.setBackground(Color.white);

        p3.setBounds(500 - 260, 425, 500, 200);
        p3.setBorder(BorderFactory.createLineBorder(Color.black));
        p3.setBackground(Color.white);

        image_label.setBounds(500 - 185, 150, 350, 200);
        image_label.setBorder(BorderFactory.createLineBorder(Color.black));
        next_label.addMouseListener(new NextImageActions());
        image_label.setVisible(false);

        question_label.setBounds(500 - 185, 80, 300, 20);

        answer1_radiobutton.setBounds(300, 445, 250, 20);
        answer1_radiobutton.setBackground(Color.white);
        answer2_radiobutton.setBounds(300, 485, 250, 20);
        answer2_radiobutton.setBackground(Color.white);
        answer3_radiobutton.setBounds(300, 525, 250, 20);
        answer3_radiobutton.setBackground(Color.white);
        answer4_radiobutton.setBounds(300, 565, 250, 20);
        answer4_radiobutton.setBackground(Color.white);

        answer1_checkbox.setVisible(false);
        answer2_checkbox.setVisible(false);
        answer3_checkbox.setVisible(false);
        answer4_checkbox.setVisible(false);

        answer1_checkbox.setBounds(300, 445, 250, 20);
        answer1_checkbox.setBackground(Color.white);
        answer2_checkbox.setBounds(300, 485, 250, 20);
        answer2_checkbox.setBackground(Color.white);
        answer3_checkbox.setBounds(300, 525, 250, 20);
        answer3_checkbox.setBackground(Color.white);
        answer4_checkbox.setBounds(300, 565, 250, 20);
        answer4_checkbox.setBackground(Color.white);

        question_label.setText(aq.get(0).getQuestiontext());


        if (aq.get(0).hasImage() == true) {
            image_icon = new ImageIcon(getClass().getResource("/img/" + aq.get(0).getImagepath()));
            image_label.setIcon(image_icon);
            image_label.setVisible(true);

        }

        if (aq.get(0).isTwoanswers() == true) {
            answer1_checkbox.setText(aa.get(0).getAnswertext());
            answer2_checkbox.setText(aa.get(1).getAnswertext());
            answer3_checkbox.setText(aa.get(2).getAnswertext());
            answer4_checkbox.setText(aa.get(3).getAnswertext());
            answer1_checkbox.setVisible(true);
            answer2_checkbox.setVisible(true);
            answer3_checkbox.setVisible(true);
            answer4_checkbox.setVisible(true);
        } else {
            answer1_radiobutton.setText(aa.get(0).getAnswertext());
            answer2_radiobutton.setText(aa.get(1).getAnswertext());
            answer3_radiobutton.setText(aa.get(2).getAnswertext());
            answer4_radiobutton.setText(aa.get(3).getAnswertext());
        }
        idomande = 1;
        irisposte = 4;

    }

    public class taskPerformer implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            if (s == 00 && m == 00) {
                PlayTestFrame.this.setEnabled(false);
                JOptionPane.showMessageDialog(PlayTestFrame.this, "Tempo Esaurito");
                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                String str = us + " : " + punti + " " + date;
                asc.add(str);
                
                try {
                    
                outForServer.writeBytes("WRITESCORE");
                outForServer.writeBytes(newLine);
                outForServer.writeBytes(arg);
                outForServer.writeBytes(newLine);
                outForServer.writeBytes(testname);
                outForServer.writeBytes(newLine);
                outForServer.writeBytes(String.valueOf(asc.size()));
                outForServer.writeBytes(newLine);
                for(int i=0;i<asc.size();i++){
                    outForServer.writeBytes(asc.get(i));
                    outForServer.writeBytes(newLine);
                }
                
                } catch (Exception ex) {
                    
                }
                
                
                
                try {
                    outForServer.writeBytes("END");
                    outForServer.writeBytes(newLine);
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(PlayTestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                PlayTestFrame.this.dispose();

                if (us.compareTo("Admin") == 0) {
                    new AdminFrame(ip);
                } else {
                    new UserFrame(us,ip);
                }

            }

            if (s == 00) {
                s = 59;
                m--;
            } else {
                s--;
            }

            if (s < 10) {
                timer_label.setText("Tempo Rimanente: " + String.valueOf(m) + ":" + "0" + String.valueOf(s));
            } else {
                timer_label.setText("Tempo Rimanente: " + String.valueOf(m) + ":" + String.valueOf(s));
            }

        }

    }

    public class NextImageActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {

            if (idomande == ndomande) {
                if (aq.get(ndomande - 1).isTwoanswers() == true) {

                    if (answer1_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 4).isCorrect() == true) {

                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer2_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 3).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer3_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 2).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }
                        }
                    }

                    if (answer4_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 1).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                        }
                    }
                } else {

                    if (answer1_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 4).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer2_radiobutton.isSelected()) {

                        if (aa.get(nrisposte - 3).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                        }
                    }

                    if (answer3_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 2).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                            if (aa.get(nrisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer4_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 1).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(nrisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 3).getAnswertext());
                            }

                            if (aa.get(nrisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 2).getAnswertext());
                            }

                            if (aa.get(nrisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(nrisposte - 4).getAnswertext());
                            }

                        }
                    }

                }

                if (aq.get(ndomande - 1).isTwoanswers() == true) {

                    if (answer1_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 4).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer2_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 3).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer3_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 2).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer4_checkbox.isSelected()) {
                        if (aa.get(nrisposte - 1).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }
                } else {

                    if (answer1_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 4).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer2_radiobutton.isSelected()) {

                        if (aa.get(nrisposte - 3).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer3_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 2).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer4_radiobutton.isSelected()) {
                        if (aa.get(nrisposte - 1).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                }

                if (punti < 0) {
                    punti = 1;
                }

                JOptionPane.showMessageDialog(PlayTestFrame.this, "Hai completato il test facendo punti " + punti);
                String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                String str = us + " : " + punti + " " + date;
                asc.add(str);
                
                
               
                
                
                try {
                    outForServer.writeBytes("WRITESCORE");
                    outForServer.writeBytes(newLine);
                    outForServer.writeBytes(arg);
                    outForServer.writeBytes(newLine);
                    outForServer.writeBytes(testname);
                    outForServer.writeBytes(newLine);
                    outForServer.writeBytes(String.valueOf(asc.size()));
                    outForServer.writeBytes(newLine);
                    
                    for(int i=0;i<asc.size();i++){
                        outForServer.writeBytes(asc.get(i));
                        outForServer.writeBytes(newLine);
                    
                    }
                    
                    
                    
                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(PlayTestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                PlayTestFrame.this.dispose();
                if (us.compareTo("Admin") == 0) {
                    new AdminFrame(serverName);
                } else {
                    new UserFrame(us,serverName);
                }

            } else {

                if (aq.get(idomande - 1).isTwoanswers() == true) {

                    if (answer1_checkbox.isSelected()) {
                        if (aa.get(irisposte - 4).isCorrect() == true) {

                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer2_checkbox.isSelected()) {
                        if (aa.get(irisposte - 3).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer3_checkbox.isSelected()) {
                        if (aa.get(irisposte - 2).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }
                        }
                    }

                    if (answer4_checkbox.isSelected()) {
                        if (aa.get(irisposte - 1).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                        }
                    }
                } else {

                    if (answer1_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 4).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer2_radiobutton.isSelected()) {

                        if (aa.get(irisposte - 3).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                        }
                    }

                    if (answer3_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 2).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                            if (aa.get(irisposte - 1).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 1).getAnswertext());
                            }

                        }
                    }

                    if (answer4_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 1).isCorrect() == true) {
                            JOptionPane.showMessageDialog(PlayTestFrame.this, "Corretto");

                        } else {
                            if (aa.get(irisposte - 3).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 3).getAnswertext());
                            }

                            if (aa.get(irisposte - 2).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 2).getAnswertext());
                            }

                            if (aa.get(irisposte - 4).isCorrect() == true) {
                                JOptionPane.showMessageDialog(PlayTestFrame.this, "Sbagliato, la risposta giusta e' " + aa.get(irisposte - 4).getAnswertext());
                            }

                        }
                    }

                }

                question_label.setText(aq.get(idomande).getQuestiontext());

                if (aq.get(idomande).hasImage() == true) {
                    image_icon = new ImageIcon(getClass().getResource("/img/" + aq.get(idomande).getImagepath()));
                    image_label.setIcon(image_icon);
                    image_label.setVisible(true);

                } else {
                    image_label.setVisible(false);
                }

                if (aq.get(idomande).isTwoanswers() == true) {
                    answer1_checkbox.setText(aa.get(irisposte).getAnswertext());
                    answer2_checkbox.setText(aa.get(irisposte + 1).getAnswertext());
                    answer3_checkbox.setText(aa.get(irisposte + 2).getAnswertext());
                    answer4_checkbox.setText(aa.get(irisposte + 3).getAnswertext());
                    answer1_checkbox.setVisible(true);
                    answer2_checkbox.setVisible(true);
                    answer3_checkbox.setVisible(true);
                    answer4_checkbox.setVisible(true);
                    answer1_radiobutton.setVisible(false);
                    answer2_radiobutton.setVisible(false);
                    answer3_radiobutton.setVisible(false);
                    answer4_radiobutton.setVisible(false);

                } else {
                    answer1_radiobutton.setText(aa.get(irisposte).getAnswertext());
                    answer2_radiobutton.setText(aa.get(irisposte + 1).getAnswertext());
                    answer3_radiobutton.setText(aa.get(irisposte + 2).getAnswertext());
                    answer4_radiobutton.setText(aa.get(irisposte + 3).getAnswertext());
                    answer1_radiobutton.setVisible(true);
                    answer2_radiobutton.setVisible(true);
                    answer3_radiobutton.setVisible(true);
                    answer4_radiobutton.setVisible(true);
                    answer1_checkbox.setVisible(false);
                    answer2_checkbox.setVisible(false);
                    answer3_checkbox.setVisible(false);
                    answer4_checkbox.setVisible(false);

                }
                if (aq.get(idomande - 1).isTwoanswers() == true) {

                    if (answer1_checkbox.isSelected()) {
                        if (aa.get(irisposte - 4).isCorrect() == true) {

                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer2_checkbox.isSelected()) {
                        if (aa.get(irisposte - 3).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer3_checkbox.isSelected()) {
                        if (aa.get(irisposte - 2).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }

                    if (answer4_checkbox.isSelected()) {
                        if (aa.get(irisposte - 1).isCorrect() == true) {
                            punti = punti + 0.5;

                        } else {
                            punti = punti - 0.5;

                        }
                    }
                } else {

                    if (answer1_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 4).isCorrect() == true) {
                            punti = punti + 1;

                          

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer2_radiobutton.isSelected()) {

                        if (aa.get(irisposte - 3).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer3_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 2).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                    if (answer4_radiobutton.isSelected()) {
                        if (aa.get(irisposte - 1).isCorrect() == true) {
                            punti = punti + 1;

                        } else {
                            punti = punti - 0.25;

                        }
                    }

                }

                radiobutton_group.clearSelection();
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);

                idomande++;
                irisposte = irisposte + 4;
            }

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

}
