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
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class SelectFrame extends JFrame {

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
            
            socket = new Socket(ip, portNumber);
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

    ImageIcon art_icon2 = new ImageIcon(getClass().getResource("/res/palette256w.png"));
    ImageIcon art_icon = new ImageIcon(getClass().getResource("/res/palette128w.png"));
    ImageIcon cinema_icon2 = new ImageIcon(getClass().getResource("/res/cinema256.png"));
    ImageIcon cinema_icon = new ImageIcon(getClass().getResource("/res/cinema128.png"));
    ImageIcon geography_icon = new ImageIcon(getClass().getResource("/res/world128.png"));
    ImageIcon geography_icon2 = new ImageIcon(getClass().getResource("/res/world256.png"));
    ImageIcon italian_icon2 = new ImageIcon(getClass().getResource("/res/lit256.png"));
    ImageIcon italian_icon = new ImageIcon(getClass().getResource("/res/lit128.png"));
    ImageIcon maths_icon = new ImageIcon(getClass().getResource("/res/maths128.png"));
    ImageIcon maths_icon2 = new ImageIcon(getClass().getResource("/res/maths256.png"));
    ImageIcon science_icon2 = new ImageIcon(getClass().getResource("/res/science256.png"));
    ImageIcon science_icon = new ImageIcon(getClass().getResource("/res/science128w.png"));
    ImageIcon sports_icon2 = new ImageIcon(getClass().getResource("/res/sports256w.png"));
    ImageIcon sports_icon = new ImageIcon(getClass().getResource("/res/sports128w.png"));
    ImageIcon history_icon2 = new ImageIcon(getClass().getResource("/res/hat256.png"));
    ImageIcon history_icon = new ImageIcon(getClass().getResource("/res/hat128.png"));
    ImageIcon ct_icon = new ImageIcon(getClass().getResource("/res/computer128w.png"));
    ImageIcon ct_icon2 = new ImageIcon(getClass().getResource("/res/computer256w.png"));
    ImageIcon other_icon = new ImageIcon(getClass().getResource("/res/other128.png"));
    ImageIcon other_icon2 = new ImageIcon(getClass().getResource("/res/other256.png"));

    JLabel art_label = new JLabel(art_icon);
    JLabel cinema_label = new JLabel(cinema_icon);
    JLabel geography_label = new JLabel(geography_icon);
    JLabel italian_label = new JLabel(italian_icon);
    JLabel maths_label = new JLabel(maths_icon);
    JLabel science_label = new JLabel(science_icon);
    JLabel sports_label = new JLabel(sports_icon);
    JLabel history_label = new JLabel(history_icon);
    JLabel ct_label = new JLabel(ct_icon);
    JLabel other_label = new JLabel(other_icon);
    JLabel select_label = new JLabel("Seleziona l' argomento");

    JLabel art = new JLabel("Arte");
    JLabel cinema = new JLabel("Cinema");
    JLabel geography = new JLabel("Geografia");
    JLabel italian = new JLabel("Italiano");
    JLabel maths = new JLabel("Matematica");
    JLabel science = new JLabel("Scienza");
    JLabel sports = new JLabel("Sport");
    JLabel history = new JLabel("Storia");
    JLabel ct = new JLabel("Tecnologia e Informatica");
    String ip;
    int mod;
    String us;

    public SelectFrame(String user, int mode,String ipr) {
        ip=ipr;
        us = user;
        mod = mode;
        connect(ip);

        setResizable(false);
        setLayout(null);
        setVisible(true);
        setSize(1150, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Seleziona Argomento");
        setLocation(x / 2 - 575, y / 2 - 300);

        add(science_label);
        add(art_label);
        add(sports_label);
        add(select_label);
        add(ct_label);
        add(maths_label);
        add(history_label);
        add(geography_label);
        add(cinema_label);
        add(italian_label);
        add(other_label);

        science_label.setBounds(210, 90, 256, 256);
        science_label.addMouseListener(new ScienceActions());
        art_label.setBounds(10, 90, 256, 256);
        art_label.addMouseListener(new ArtActions());
        sports_label.setBounds(410, 90, 256, 256);
        sports_label.addMouseListener(new SportsActions());
        ct_label.setBounds(610, 90, 256, 256);
        ct_label.addMouseListener(new CTActions());
        maths_label.setBounds(810, 90, 256, 256);
        maths_label.addMouseListener(new MathsActions());
        history_label.setBounds(10, 290, 256, 256);
        history_label.addMouseListener(new HistoryActions());
        geography_label.setBounds(210, 290, 256, 256);
        geography_label.addMouseListener(new GeoActions());
        cinema_label.setBounds(410, 290, 256, 256);
        cinema_label.addMouseListener(new CinemaActions());
        italian_label.setBounds(610, 290, 256, 256);
        italian_label.addMouseListener(new LitActions());
        other_label.setBounds(810, 290, 256, 256);
        other_label.addMouseListener(new OtherActions());

        select_label.setFont(new Font("Arial", Font.ROMAN_BASELINE, 26));
        select_label.setBounds(500 - 150, 10, 300, 100);

    }

    public class ArtActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            list.addMouseListener(new ListList(list, "Arte", f));
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");

            try {
                outForServer.writeBytes("SELECTTESTARTE");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);

        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            art_label.setIcon(art_icon2);
        }

        public void mouseExited(MouseEvent me) {
            art_label.setIcon(art_icon);
        }

    }

    public class ScienceActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            list.addMouseListener(new ListList(list, "Scienze", f));
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTSCIENZE");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            science_label.setIcon(science_icon2);
        }

        public void mouseExited(MouseEvent me) {
            science_label.setIcon(science_icon);
        }

    }

    public class SportsActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            list.addMouseListener(new ListList(list, "Sport", f));
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTSPORT");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            sports_label.setIcon(sports_icon2);
        }

        public void mouseExited(MouseEvent me) {
            sports_label.setIcon(sports_icon);
        }

    }

    public class CTActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            list.addMouseListener(new ListList(list, "Tecnologia e Informatica", f));
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTCT");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);

        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            ct_label.setIcon(ct_icon2);
        }

        public void mouseExited(MouseEvent me) {
            ct_label.setIcon(ct_icon);
        }

    }

    public class MathsActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            list.addMouseListener(new ListList(list, "Matematica", f));
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTMATH");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            maths_label.setIcon(maths_icon2);
        }

        public void mouseExited(MouseEvent me) {
            maths_label.setIcon(maths_icon);
        }

    }

    public class HistoryActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            list.addMouseListener(new ListList(list, "Storia", f));
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTSTORY");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            history_label.setIcon(history_icon2);
        }

        public void mouseExited(MouseEvent me) {
            history_label.setIcon(history_icon);
        }

    }

    public class GeoActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            list.addMouseListener(new ListList(list, "Geografia", f));
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTGEO");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            geography_label.setIcon(geography_icon2);
        }

        public void mouseExited(MouseEvent me) {
            geography_label.setIcon(geography_icon);
        }

    }

    public class CinemaActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();

            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            list.addMouseListener(new ListList(list, "Cinema", f));
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTCINEMA");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            cinema_label.setIcon(cinema_icon2);
        }

        public void mouseExited(MouseEvent me) {
            cinema_label.setIcon(cinema_icon);
        }

    }

    public class LitActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            list.addMouseListener(new ListList(list, "Italiano", f));
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTITA");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

            } catch (Exception e) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent me) {

        }

        public void mouseReleased(MouseEvent me) {

        }

        public void mouseEntered(MouseEvent me) {

            italian_label.setIcon(italian_icon2);

        }

        public void mouseExited(MouseEvent me) {

            italian_label.setIcon(italian_icon);
        }

    }

    public class OtherActions implements MouseListener {

        public void mouseClicked(MouseEvent e) {
            JFrame f = new JFrame();
            JList list = new JList();
            JScrollPane jsp = new JScrollPane(list);
            f.addWindowListener(new WindowL());
            f.getContentPane().add(jsp, BorderLayout.CENTER);
            f.setVisible(false);
            list.addMouseListener(new ListList(list, "Altro", f));
            f.setLocation(x / 2 - 150, y / 2 - 250);
            f.setSize(300, 500);
            f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            f.setTitle("Seleziona Test");
            try {
                outForServer.writeBytes("SELECTTESTALTRO");
                outForServer.writeBytes(newLine);
                int n = Integer.parseInt(inFromServer.readLine());
                String[] tests = new String[n];
                for (int i = 0; i < n; i++) {
                    tests[i] = inFromServer.readLine();
                }
                list.setListData(tests);
                jsp = new JScrollPane(list);

                f.getContentPane().add(jsp, BorderLayout.CENTER);

                System.out.println("finito sta cl");
                
            } catch (Exception ex) {

            }

            f.setVisible(true);
            SelectFrame.this.setEnabled(false);
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {


            other_label.setIcon(other_icon2);
        }

        public void mouseExited(MouseEvent e) {
            other_label.setIcon(other_icon);
        }

    }

    public class WindowL implements WindowListener {

        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            SelectFrame.this.setEnabled(true);
        }

        public void windowClosed(WindowEvent e) {
            SelectFrame.this.setEnabled(true);
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

    public class ListList implements MouseListener {

        String p;
        JList l;
        JFrame f;

        public ListList(JList list, String path, JFrame fr) {
            JList l = list;
            p = path;
            f = fr;
        }

        public void mouseClicked(MouseEvent e) {
            l = (JList) e.getSource();
            if (e.getClickCount() == 2) {

                // Double-click detected
                int index = l.locationToIndex(e.getPoint());
                if (index < 0) {
                    JOptionPane.showMessageDialog(SelectFrame.this, "Non sono presenti test per questa materia");
                } else {
                    String testname = l.getModel().getElementAt(index).toString();

                    String[] str = new String[2];
                    str = testname.split(Pattern.quote("."));
                    SelectFrame.this.dispose();
                    if (mod == 1) {
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SelectFrame.this.dispose();
                        f.dispose();
                        System.out.println(str[0]);
                        System.out.println(p);
                        new PlayTestFrame(p, str[0], us,ip);
                    } else {
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SelectFrame.this.dispose();
                        f.dispose();
                        new ScoresFrame(p, str[0], us,serverName).connect(serverName);

                    }

                }

            } else if (e.getClickCount() == 3) {

                // Triple-click detected
                int index = l.locationToIndex(e.getPoint());
                if (index < 0) {
                    JOptionPane.showMessageDialog(SelectFrame.this, "Non sono presenti test per questa materia");
                } else {
                    String testname = l.getModel().getElementAt(index).toString();
                    String[] str = new String[2];
                    str = testname.split(Pattern.quote("."));
                    SelectFrame.this.dispose();
                    if (mod == 1) {
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                        new PlayTestFrame(p, str[0], us,ip);
                        f.dispose();
                        SelectFrame.this.dispose();
                    } else {
                        try {
                            outForServer.writeBytes("END");
                            outForServer.writeBytes(newLine);
                            socket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ScoresFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        SelectFrame.this.dispose();
                        f.dispose();
                        new ScoresFrame(p, str[0], us,serverName).connect(serverName);

                    }
                }
            }
        }

        public void mousePressed(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }

    }

}
