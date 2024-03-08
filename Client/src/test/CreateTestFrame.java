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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CreateTestFrame extends JFrame {

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

    BinaryFilesManagement bfm = new BinaryFilesManagement();
    ArrayList<Answer> aa = new ArrayList();
    ArrayList<Question> aq = new ArrayList();

    JLabel checkquestiontext_label = new JLabel("Vuoto");
    JLabel checkanswer1text_label = new JLabel("Vuoto");
    JLabel checkanswer2text_label = new JLabel("Vuoto");
    JLabel checkanswer3text_label = new JLabel("Vuoto");
    JLabel checkanswer4text_label = new JLabel("Vuoto");
    JLabel checkimagepathtext_label = new JLabel("Non Trovata");

    ImageIcon correct_icon = new ImageIcon(getClass().getResource("/res/correct.png"));
    ImageIcon wrong_icon = new ImageIcon(getClass().getResource("/res/wrong.png"));

    JLabel button1_label = new JLabel(wrong_icon);
    JLabel button2_label = new JLabel(wrong_icon);
    JLabel button3_label = new JLabel(wrong_icon);
    JLabel button4_label = new JLabel(wrong_icon);

    int counter_checkbox;
    int questioncounter = 0;

    ImageIcon help_icon = new ImageIcon(getClass().getResource("/res/help.png"));
    JLabel help_label = new JLabel(help_icon);
    JLabel imagepreview_label;
    ImageIcon imagepreview_icon;
    String[] subjects_array = {"Storia", "Geografia", "Scienze", "Arte", "Cinema", "Matematica", "Sport", "Italiano", "Tecnologia e Informatica", "Altro"};
    String[] questionnumber_array = {"10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
    String[] typeofquestion_array = {"Risposta Singola", "Risposta Multipla(MAX 2 Risposte)"};
    JFrame basesettings_frame = new JFrame();
    JTextField testname_textfield = new JTextField();
    JCheckBox imageyn_checkbox = new JCheckBox("Immage SI/NO");
    JTextArea question_textarea = new JTextArea();
    JTextField answer1_textfield = new JTextField();
    JTextField answer2_textfield = new JTextField();
    JTextField answer3_textfield = new JTextField();
    JTextField answer4_textfield = new JTextField();
    JTextField imagepath_textfield = new JTextField("");
    JComboBox subjects_combobox = new JComboBox(subjects_array);
    JComboBox nquestions_combobox = new JComboBox(questionnumber_array);
    ButtonGroup answer_radiobuttongroup = new ButtonGroup();
    JCheckBox answer1_checkbox = new JCheckBox("Risposta 1");
    JCheckBox answer2_checkbox = new JCheckBox("Risposta 2");
    JCheckBox answer3_checkbox = new JCheckBox("Risposta 3");
    JCheckBox answer4_checkbox = new JCheckBox("Risposta 4");
    JRadioButton answer1_radiobutton = new JRadioButton("Risposta 1");
    JRadioButton answer2_radiobutton = new JRadioButton("Risposta 2");
    JRadioButton answer3_radiobutton = new JRadioButton("Risposta 3");
    JRadioButton answer4_radiobutton = new JRadioButton("Risposta 4");
    JLabel preview_label = new JLabel("Anteprima immagine");

    JLabel imagepath_label = new JLabel("Nome immagine");
    JComboBox typeofquestion_combobox = new JComboBox(typeofquestion_array);
    JLabel answer1_label = new JLabel("Testo Risposta 1");
    JLabel answer2_label = new JLabel("Testo Risposta 2");
    JLabel answer3_label = new JLabel("Testo Risposta 3");
    JLabel answer4_label = new JLabel("Testo Risposta 4");
    JLabel questiontext_label = new JLabel("Testo della domanda");
    JLabel testname_label = new JLabel("Titolo Test");
    JLabel subjectchoose_label = new JLabel("Argomento");
    JLabel checkname_label = new JLabel("Vuoto");

    JButton nextquestion_button = new JButton("NEXT");

    JLabel nquestionschoose_label = new JLabel("N. Domande");
    JButton continue_button = new JButton("CONTINUA");

    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) d.getWidth();
    int y = (int) d.getHeight();
    int nquestions;
    String testname;
    String subject;

    public CreateTestFrame() {

        imagepreview_label = new JLabel(imagepreview_icon);

        basesettings_frame.setVisible(true);
        basesettings_frame.setSize(500, 300);
        basesettings_frame.setLayout(null);
        basesettings_frame.add(subjects_combobox);
        basesettings_frame.add(nquestions_combobox);
        basesettings_frame.add(testname_textfield);
        basesettings_frame.add(testname_label);
        basesettings_frame.add(subjectchoose_label);
        basesettings_frame.add(nquestionschoose_label);
        basesettings_frame.add(continue_button);
        basesettings_frame.add(checkname_label);
        basesettings_frame.setLocation(x / 2 - 250, y / 2 - 150);
        basesettings_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        testname_label.setBounds(150, 60, 200, 20);
        testname_textfield.setBounds(150, 80, 200, 20);
        testname_textfield.addKeyListener(new TitleFieldActions());
        subjectchoose_label.setBounds(150, 100, 200, 20);
        subjects_combobox.setBounds(150, 120, 200, 20);
        nquestionschoose_label.setBounds(150, 140, 200, 20);
        checkname_label.setBounds(360, 80, 100, 20);
        checkname_label.setVisible(false);
        nquestions_combobox.setBounds(150, 160, 200, 20);
        continue_button.setBounds(200, 190, 100, 20);
        continue_button.addActionListener(new ContinueActions());

        answer_radiobuttongroup.add(answer1_radiobutton);
        answer_radiobuttongroup.add(answer2_radiobutton);
        answer_radiobuttongroup.add(answer3_radiobutton);
        answer_radiobuttongroup.add(answer4_radiobutton);

        setLayout(null);
        setLocation(x / 2 - 400, y / 2 - 300);
        setTitle("Crea Test");
        setSize(800, 600);
        setVisible(false);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(help_label);
        add(button1_label);
        add(button2_label);
        add(button3_label);
        add(button4_label);
        add(question_textarea);
        add(questiontext_label);
        add(typeofquestion_combobox);
        add(imageyn_checkbox);
        imageyn_checkbox.setVisible(false);
        add(answer1_radiobutton);
        add(answer2_radiobutton);
        add(answer3_radiobutton);
        add(answer4_radiobutton);
        add(answer1_checkbox);
        add(answer2_checkbox);
        add(answer3_checkbox);
        add(answer4_checkbox);
        add(answer1_textfield);
        add(answer2_textfield);
        add(answer3_textfield);
        add(answer4_textfield);
        add(answer1_label);
        add(answer2_label);
        add(answer3_label);
        add(answer4_label);
        add(imagepath_label);
        imagepath_label.setVisible(false);
        add(imagepath_textfield);
        imagepath_textfield.setVisible(false);
        add(imagepreview_label);
        imagepreview_label.setVisible(false);
        add(preview_label);
        add(checkquestiontext_label);
        add(checkanswer1text_label);
        add(checkanswer2text_label);
        add(checkanswer3text_label);
        add(checkanswer4text_label);
        add(checkimagepathtext_label);
        add(nextquestion_button);

        checkquestiontext_label.setBounds(400 - 190, 20, 100, 20);
        checkanswer1text_label.setBounds(400 - 160, 400, 100, 20);
        checkanswer2text_label.setBounds(400 - 160, 440, 100, 20);
        checkanswer3text_label.setBounds(400 - 160, 480, 100, 20);
        checkanswer4text_label.setBounds(400 - 160, 520, 100, 20);
        checkquestiontext_label.setVisible(false);
        checkanswer1text_label.setVisible(false);
        checkanswer2text_label.setVisible(false);
        checkanswer3text_label.setVisible(false);
        checkanswer4text_label.setVisible(false);
        checkimagepathtext_label.setVisible(false);
        checkimagepathtext_label.setBounds(570, 210, 100, 20);

        imagepreview_label.setBounds(405, 260, 300, 200);
        imagepreview_label.setBorder(BorderFactory.createLineBorder(Color.gray));
        preview_label.setVisible(false);
        preview_label.setBounds(405, 240, 150, 20);

        questiontext_label.setBounds(400 - 325, 20, 120, 20);
        question_textarea.setBounds(400 - 325, 50, 650, 100);
        question_textarea.addKeyListener(new QuestionTextAreaActions());
        question_textarea.setBorder(BorderFactory.createLineBorder(Color.black));
        typeofquestion_combobox.setBounds(400 - 325, 160, 230, 20);
        typeofquestion_combobox.addActionListener(new ComboBoxActions());

        imageyn_checkbox.setBounds(400, 160, 130, 20);
        imageyn_checkbox.addActionListener(new CheckBoxActions());

        button1_label.setBounds(400 - 160, 200, 16, 16);
        button2_label.setBounds(400 - 160, 240, 16, 16);
        button3_label.setBounds(400 - 160, 280, 16, 16);
        button4_label.setBounds(400 - 160, 320, 16, 16);
        answer1_radiobutton.setBounds(400 - 325, 200, 150, 20);
        answer1_radiobutton.addActionListener(new RadioButtonActions());
        answer2_radiobutton.addActionListener(new RadioButton2Actions());
        answer3_radiobutton.addActionListener(new RadioButton3Actions());
        answer4_radiobutton.addActionListener(new RadioButton4Actions());
        answer2_radiobutton.setBounds(400 - 325, 240, 150, 20);
        answer3_radiobutton.setBounds(400 - 325, 280, 150, 20);
        answer4_radiobutton.setBounds(400 - 325, 320, 150, 20);

        answer1_label.setBounds(400 - 325, 380, 150, 20);
        answer2_label.setBounds(400 - 325, 420, 150, 20);
        answer3_label.setBounds(400 - 325, 460, 150, 20);
        answer4_label.setBounds(400 - 325, 500, 150, 20);

        answer1_checkbox.setVisible(false);
        answer2_checkbox.setVisible(false);
        answer3_checkbox.setVisible(false);
        answer4_checkbox.setVisible(false);
        answer1_checkbox.addActionListener(new AnswerCheckboxActions());
        answer2_checkbox.addActionListener(new Answer2CheckboxActions());
        answer3_checkbox.addActionListener(new Answer3CheckboxActions());
        answer4_checkbox.addActionListener(new Answer4CheckboxActions());

        button1_label.setVisible(false);
        button2_label.setVisible(false);
        button3_label.setVisible(false);
        button4_label.setVisible(false);

        answer1_checkbox.setBounds(400 - 325, 200, 150, 20);
        answer2_checkbox.setBounds(400 - 325, 240, 150, 20);
        answer3_checkbox.setBounds(400 - 325, 280, 150, 20);
        answer4_checkbox.setBounds(400 - 325, 320, 150, 20);
        answer1_textfield.setBounds(400 - 325, 400, 150, 20);
        answer1_textfield.addKeyListener(new Answer1TextFieldActions());
        answer2_textfield.addKeyListener(new Answer2TextFieldActions());
        answer3_textfield.addKeyListener(new Answer3TextFieldActions());
        answer4_textfield.addKeyListener(new Answer4TextFieldActions());
        answer2_textfield.setBounds(400 - 325, 440, 150, 20);
        answer3_textfield.setBounds(400 - 325, 480, 150, 20);
        answer4_textfield.setBounds(400 - 325, 520, 150, 20);
        nextquestion_button.setBounds(725 - 100, 520 - 25, 100, 50);
        nextquestion_button.addActionListener(new NextQuestionButton());
        imagepath_label.setBounds(405, 190, 120, 20);
        help_label.setBounds(500, 190, 20, 20);
        help_label.addMouseListener(new HelpActions());
        help_label.setVisible(false);
        imagepath_textfield.setBounds(405, 210, 150, 20);
        imagepath_textfield.addKeyListener(new ImagePathTextFieldActions());
        CreateTestFrame.this.getRootPane().setDefaultButton(nextquestion_button);

        imagepath_textfield.setVisible(false);
        imagepath_label.setVisible(false);
        imagepreview_label.setVisible(false);

    }

    public class NextQuestionButton implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            Answer a1 = new Answer();
            Answer a2 = new Answer();
            Answer a3 = new Answer();
            Answer a4 = new Answer();
            Question q = new Question();

            if (questioncounter == nquestions - 1) {

                int checkcounter = 0;
                int counter = 5;
                int countercb = 2;

                if (checkanswer1text_label.getText().equals("Vuoto")) {
                    counter--;

                }

                if (checkanswer2text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkanswer3text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkanswer4text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkquestiontext_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (counter < 5) {
                    JOptionPane.showMessageDialog(CreateTestFrame.this, "Uno o piu campi sono vuoti");

                } else {
                    checkcounter++;
                    q.setQuestiontext(question_textarea.getText().trim());

                }

                if (imageyn_checkbox.isSelected()) {

                    if (checkimagepathtext_label.getText().equals("Non Trovata")) {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Immagine non Trovata");
                    } else {

                        q.setImage(true);
                        q.setImagepath(imagepath_textfield.getText());
                        checkcounter++;
                    }
                } else {
                    q.setImage(false);
                    checkcounter++;
                }

                a1.setCorrect(false);
                a2.setCorrect(false);
                a3.setCorrect(false);
                a4.setCorrect(false);

                if (answer1_checkbox.isSelected()) {
                    a1.setCorrect(true);

                    countercb--;
                }

                if (answer2_checkbox.isSelected()) {
                    countercb--;
                    a2.setCorrect(true);
                }

                if (answer3_checkbox.isSelected()) {
                    countercb--;
                    a3.setCorrect(true);
                }

                if (answer4_checkbox.isSelected()) {
                    countercb--;
                    a4.setCorrect(true);
                }

                Object selected = typeofquestion_combobox.getSelectedItem();
                if (selected.toString().equals("Risposta Multipla(MAX 2 Risposte)")) {
                    if (counter_checkbox == 0) {
                        checkcounter++;
                        q.setTwoanswers(true);
                        a1.setAnswertext(answer1_checkbox.getText());
                        a2.setAnswertext(answer2_checkbox.getText());
                        a3.setAnswertext(answer3_checkbox.getText());
                        a4.setAnswertext(answer4_checkbox.getText());
                        if (answer1_checkbox.isSelected()) {
                            a1.setCorrect(true);

                        }

                        if (answer2_checkbox.isSelected()) {

                            a2.setCorrect(true);
                        }

                        if (answer3_checkbox.isSelected()) {

                            a3.setCorrect(true);
                        }

                        if (answer4_checkbox.isSelected()) {

                            a4.setCorrect(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona 2 Risposte");
                    }

                } else if (selected.toString().equals("Risposta Singola")) {
                    if (answer_radiobuttongroup.getSelection() != null) {
                        a1.setAnswertext(answer1_radiobutton.getText());
                        a2.setAnswertext(answer2_radiobutton.getText());
                        a3.setAnswertext(answer3_radiobutton.getText());
                        a4.setAnswertext(answer4_radiobutton.getText());
                        if (answer1_radiobutton.isSelected()) {
                            a1.setCorrect(true);
                        }

                        if (answer2_radiobutton.isSelected()) {
                            a1.setCorrect(true);
                        }

                        if (answer3_radiobutton.isSelected()) {
                            a1.setCorrect(true);
                        }

                        if (answer4_radiobutton.isSelected()) {
                            a1.setCorrect(true);
                        }
                        checkcounter++;
                        q.setTwoanswers(false);
                    } else {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona una Risposta");
                    }
                }

                if (checkcounter == 3) {

                    JOptionPane.showMessageDialog(CreateTestFrame.this, "Questionario Creato");
                    answer1_checkbox.setText("Risposta 1");
                    answer1_radiobutton.setText("Risposta 1");
                    answer2_checkbox.setText("Risposta 2");
                    answer2_radiobutton.setText("Risposta 2");
                    answer3_checkbox.setText("Risposta 3");
                    answer3_radiobutton.setText("Risposta 3");
                    answer4_checkbox.setText("Risposta 4");
                    answer4_radiobutton.setText("Risposta 4");

                    answer1_checkbox.setSelected(false);
                    answer2_checkbox.setSelected(false);
                    answer3_checkbox.setSelected(false);
                    answer4_checkbox.setSelected(false);
                    answer_radiobuttongroup.clearSelection();
                    imageyn_checkbox.setSelected(false);
                    answer1_textfield.setText("");
                    answer2_textfield.setText("");
                    answer3_textfield.setText("");
                    answer4_textfield.setText("");
                    question_textarea.setText("");

                    checkanswer1text_label.setVisible(false);
                    checkanswer2text_label.setVisible(false);
                    checkanswer3text_label.setVisible(false);
                    checkanswer4text_label.setVisible(false);
                    checkquestiontext_label.setVisible(false);
                    imagepreview_label.setIcon(null);
                    imagepath_textfield.setText("");
                    preview_label.setVisible(false);
                    imagepreview_label.setVisible(false);
                    imagepath_label.setVisible(false);
                    imagepath_textfield.setVisible(false);
                    help_label.setVisible(false);
                    checkimagepathtext_label.setText("Non Trovata");
                    checkimagepathtext_label.setVisible(false);
                    aq.add(q);
                    aa.add(a1);
                    aa.add(a2);
                    aa.add(a3);
                    aa.add(a4);

                    try {
                        outForServer.writeBytes("WRITETEST");
                        outForServer.writeBytes(newLine);
                        outForServer.writeBytes(String.valueOf(aq.size()));
                        outForServer.writeBytes(newLine);
                        outForServer.writeBytes(String.valueOf(aa.size()));
                        outForServer.writeBytes(newLine);

                        for (int i = 0; i < aq.size(); i++) {
                            outForServer.writeBytes(aq.get(i).getQuestiontext());
                            outForServer.writeBytes(newLine);
                            outForServer.writeBytes(String.valueOf(false));
                            outForServer.writeBytes(newLine);
                            outForServer.writeBytes("");
                            outForServer.writeBytes(newLine);
                            outForServer.writeBytes(String.valueOf(aq.get(i).isTwoanswers()));
                            outForServer.writeBytes(newLine);
                        }

                        for (int i = 0; i < aa.size(); i++) {
                            outForServer.writeBytes(aa.get(i).getAnswertext());
                            outForServer.writeBytes(newLine);
                            outForServer.writeBytes(String.valueOf(aa.get(i).isCorrect()));
                            outForServer.writeBytes(newLine);
                        }

                        outForServer.writeBytes(subject);
                        outForServer.writeBytes(newLine);
                        outForServer.writeBytes(testname);
                        outForServer.writeBytes(newLine);
                        
                    } catch (Exception ex) {

                    }
                    try {
                    outForServer.writeBytes("END");
                    outForServer.writeBytes(newLine);
                    socket.close();
                    } catch (Exception e) {
                        
                    }
                    
                    CreateTestFrame.this.dispose();
                    new AdminFrame(serverName);
                }

            } else {
                int checkcounter = 0;
                int counter = 5;
                int countercb = 2;

                if (checkanswer1text_label.getText().equals("Vuoto")) {
                    counter--;

                }

                if (checkanswer2text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkanswer3text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkanswer4text_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (checkquestiontext_label.getText().equals("Vuoto")) {
                    counter--;
                }

                if (counter < 5) {
                    JOptionPane.showMessageDialog(CreateTestFrame.this, "Uno o piu campi sono vuoti");

                } else {
                    checkcounter++;
                    q.setQuestiontext(question_textarea.getText().trim());

                }

                if (imageyn_checkbox.isSelected()) {

                    if (checkimagepathtext_label.getText().equals("Non Trovata")) {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Immagine non Trovata");
                    } else {

                        q.setImage(true);
                        q.setImagepath(imagepath_textfield.getText());
                        checkcounter++;
                    }
                } else {
                    q.setImage(false);
                    checkcounter++;
                }

                a1.setCorrect(false);
                a2.setCorrect(false);
                a3.setCorrect(false);
                a4.setCorrect(false);

                if (answer1_checkbox.isSelected()) {
                    a1.setCorrect(true);

                    countercb--;
                }

                if (answer2_checkbox.isSelected()) {
                    countercb--;
                    a2.setCorrect(true);
                }

                if (answer3_checkbox.isSelected()) {
                    countercb--;
                    a3.setCorrect(true);
                }

                if (answer4_checkbox.isSelected()) {
                    countercb--;
                    a4.setCorrect(true);
                }

                Object selected = typeofquestion_combobox.getSelectedItem();
                if (selected.toString().equals("Risposta Multipla(MAX 2 Risposte)")) {
                    if (counter_checkbox == 0) {
                        checkcounter++;
                        a1.setAnswertext(answer1_checkbox.getText());
                        a2.setAnswertext(answer2_checkbox.getText());
                        a3.setAnswertext(answer3_checkbox.getText());
                        a4.setAnswertext(answer4_checkbox.getText());
                        q.setTwoanswers(true);
                        if (answer1_checkbox.isSelected()) {
                            a1.setCorrect(true);

                        }

                        if (answer2_checkbox.isSelected()) {

                            a2.setCorrect(true);
                        }

                        if (answer3_checkbox.isSelected()) {

                            a3.setCorrect(true);
                        }

                        if (answer4_checkbox.isSelected()) {

                            a4.setCorrect(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona 2 Risposte");
                    }

                } else if (selected.toString().equals("Risposta Singola")) {
                    if (answer_radiobuttongroup.getSelection() != null) {
                        a1.setAnswertext(answer1_radiobutton.getText());
                        a2.setAnswertext(answer2_radiobutton.getText());
                        a3.setAnswertext(answer3_radiobutton.getText());
                        a4.setAnswertext(answer4_radiobutton.getText());
                        if (answer1_radiobutton.isSelected()) {
                            a1.setCorrect(true);
                        }

                        if (answer2_radiobutton.isSelected()) {
                            a2.setCorrect(true);
                        }

                        if (answer3_radiobutton.isSelected()) {
                            a3.setCorrect(true);
                        }

                        if (answer4_radiobutton.isSelected()) {
                            a4.setCorrect(true);
                        }
                        checkcounter++;
                        q.setTwoanswers(false);
                    } else {
                        JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona una Risposta");
                    }
                }

                if (checkcounter == 3) {

                    JOptionPane.showMessageDialog(CreateTestFrame.this, "Domanda Aggiunta");
                    answer1_checkbox.setText("Risposta 1");
                    answer1_radiobutton.setText("Risposta 1");
                    answer2_checkbox.setText("Risposta 2");
                    answer2_radiobutton.setText("Risposta 2");
                    answer3_checkbox.setText("Risposta 3");
                    answer3_radiobutton.setText("Risposta 3");
                    answer4_checkbox.setText("Risposta 4");
                    answer4_radiobutton.setText("Risposta 4");

                    answer1_checkbox.setSelected(false);
                    answer2_checkbox.setSelected(false);
                    answer3_checkbox.setSelected(false);
                    answer4_checkbox.setSelected(false);
                    answer_radiobuttongroup.clearSelection();
                    imageyn_checkbox.setSelected(false);
                    answer1_textfield.setText("");
                    answer2_textfield.setText("");
                    answer3_textfield.setText("");
                    answer4_textfield.setText("");
                    question_textarea.setText("");
                    checkanswer1text_label.setVisible(false);
                    checkanswer2text_label.setVisible(false);
                    checkanswer3text_label.setVisible(false);
                    checkanswer4text_label.setVisible(false);
                    checkquestiontext_label.setVisible(false);
                    imagepreview_label.setIcon(null);
                    imagepath_textfield.setText("");
                    preview_label.setVisible(false);
                    imagepreview_label.setVisible(false);
                    help_label.setVisible(false);
                    imagepath_label.setVisible(false);
                    imagepath_textfield.setVisible(false);
                    checkimagepathtext_label.setText("Non Trovata");
                    checkimagepathtext_label.setVisible(false);
                    if (selected.toString().equals("Risposta Multipla(MAX 2 Risposte)")) {
                        typeofquestion_combobox.setSelectedIndex(0);
                        answer1_checkbox.setVisible(false);
                        answer2_checkbox.setVisible(false);
                        answer3_checkbox.setVisible(false);
                        answer4_checkbox.setVisible(false);

                        answer1_radiobutton.setVisible(true);
                        answer2_radiobutton.setVisible(true);
                        answer3_radiobutton.setVisible(true);
                        answer4_radiobutton.setVisible(true);
                    }
                    questioncounter++;
                    button1_label.setIcon(wrong_icon);
                    button2_label.setIcon(wrong_icon);
                    button3_label.setIcon(wrong_icon);
                    button4_label.setIcon(wrong_icon);
                    button1_label.setVisible(false);
                    button2_label.setVisible(false);
                    button3_label.setVisible(false);
                    button4_label.setVisible(false);
                    aq.add(q);
                    aa.add(a1);
                    aa.add(a2);
                    aa.add(a3);
                    aa.add(a4);

                    if (questioncounter == nquestions - 1) {
                        nextquestion_button.setText("FINE");
                    }

                }

            }

        }

    }

    public class AnswerCheckboxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer1_checkbox = (JCheckBox) ae.getSource();
            counter_checkbox = 2;

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

            if (counter_checkbox < 0) {
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);
                button1_label.setIcon(wrong_icon);
                button2_label.setIcon(wrong_icon);
                button3_label.setIcon(wrong_icon);
                button4_label.setIcon(wrong_icon);
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
                JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona MAX 2 campi!");
                counter_checkbox = 2;
            }

        }
    }

    public class Answer2CheckboxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer2_checkbox = (JCheckBox) ae.getSource();

            counter_checkbox = 2;

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

            if (counter_checkbox < 0) {
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);
                button1_label.setIcon(wrong_icon);
                button2_label.setIcon(wrong_icon);
                button3_label.setIcon(wrong_icon);
                button4_label.setIcon(wrong_icon);
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
                JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona MAX 2 campi!");

                counter_checkbox = 2;
            }

        }
    }

    public class Answer3CheckboxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer3_checkbox = (JCheckBox) ae.getSource();
            counter_checkbox = 2;

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

            if (counter_checkbox < 0) {
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);
                button1_label.setIcon(wrong_icon);
                button2_label.setIcon(wrong_icon);
                button3_label.setIcon(wrong_icon);
                button4_label.setIcon(wrong_icon);
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
                JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona MAX 2 campi!");

                counter_checkbox = 2;
            }

        }
    }

    public class Answer4CheckboxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer4_checkbox = (JCheckBox) ae.getSource();

            counter_checkbox = 2;

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_checkbox.isSelected()) {
                counter_checkbox = counter_checkbox - 1;
                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

            if (counter_checkbox < 0) {
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);
                button1_label.setIcon(wrong_icon);
                button2_label.setIcon(wrong_icon);
                button3_label.setIcon(wrong_icon);
                button4_label.setIcon(wrong_icon);
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
                JOptionPane.showMessageDialog(CreateTestFrame.this, "Seleziona MAX 2 Risposte!");

                counter_checkbox = 2;
            }

        }
    }

    public class QuestionTextAreaActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {
            if (question_textarea.getText().trim().isEmpty()) {
                checkquestiontext_label.setVisible(true);
                checkquestiontext_label.setText("Vuoto");
                checkquestiontext_label.setForeground(Color.red);
            } else {
                checkquestiontext_label.setForeground(Color.red);
                checkquestiontext_label.setVisible(true);
                checkquestiontext_label.setText("Ok");
                checkquestiontext_label.setForeground(Color.green);
            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (question_textarea.getText().trim().isEmpty()) {
                checkquestiontext_label.setVisible(true);
                checkquestiontext_label.setText("Vuoto");
                checkquestiontext_label.setForeground(Color.red);
            } else {
                checkquestiontext_label.setForeground(Color.red);
                checkquestiontext_label.setVisible(true);
                checkquestiontext_label.setText("Ok");
                checkquestiontext_label.setForeground(Color.green);
            }
        }
    }

    public class ComboBoxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            typeofquestion_combobox = (JComboBox) ae.getSource();

            Object selected = typeofquestion_combobox.getSelectedItem();
            if (selected.toString().equals("Risposta Singola")) {

                answer1_checkbox.setVisible(false);
                answer2_checkbox.setVisible(false);
                answer3_checkbox.setVisible(false);
                answer4_checkbox.setVisible(false);
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);

                answer1_radiobutton.setVisible(true);
                answer2_radiobutton.setVisible(true);
                answer3_radiobutton.setVisible(true);
                answer4_radiobutton.setVisible(true);
                answer_radiobuttongroup.clearSelection();
                counter_checkbox = 2;
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
            } else {
                button1_label.setVisible(false);
                button2_label.setVisible(false);
                button3_label.setVisible(false);
                button4_label.setVisible(false);
                counter_checkbox = 2;
                answer1_checkbox.setVisible(true);
                answer2_checkbox.setVisible(true);
                answer3_checkbox.setVisible(true);
                answer4_checkbox.setVisible(true);
                answer1_checkbox.setSelected(false);
                answer2_checkbox.setSelected(false);
                answer3_checkbox.setSelected(false);
                answer4_checkbox.setSelected(false);

                answer1_radiobutton.setVisible(false);
                answer2_radiobutton.setVisible(false);
                answer3_radiobutton.setVisible(false);
                answer4_radiobutton.setVisible(false);
                answer_radiobuttongroup.clearSelection();
            }
        }

    }

    public class Answer1TextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {
            if (answer1_textfield.getText().trim().isEmpty()) {
                checkanswer1text_label.setVisible(true);
                checkanswer1text_label.setText("Vuoto");
                checkanswer1text_label.setForeground(Color.red);
                answer1_checkbox.setText("Risposta 1");
                answer1_radiobutton.setText("Risposta 1");
            } else {
                checkanswer1text_label.setForeground(Color.red);
                checkanswer1text_label.setVisible(true);
                checkanswer1text_label.setText("Ok");
                checkanswer1text_label.setForeground(Color.green);
                answer1_checkbox.setText(answer1_textfield.getText());
                answer1_radiobutton.setText(answer1_textfield.getText());
            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (answer1_textfield.getText().trim().isEmpty()) {
                checkanswer1text_label.setVisible(true);
                checkanswer1text_label.setText("Vuoto");
                checkanswer1text_label.setForeground(Color.red);
                answer1_checkbox.setText("Risposta 1");
                answer1_radiobutton.setText("Risposta 1");
            } else {
                checkanswer1text_label.setForeground(Color.red);
                checkanswer1text_label.setVisible(true);
                checkanswer1text_label.setText("Ok");
                checkanswer1text_label.setForeground(Color.green);
                answer1_checkbox.setText(answer1_textfield.getText());
                answer1_radiobutton.setText(answer1_textfield.getText());
            }
        }
    }

    public class Answer2TextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {
            if (answer2_textfield.getText().trim().isEmpty()) {
                checkanswer2text_label.setVisible(true);
                checkanswer2text_label.setText("Vuoto");
                checkanswer2text_label.setForeground(Color.red);
                answer2_checkbox.setText("Risposta 2");
                answer2_radiobutton.setText("Risposta 2");
            } else {
                checkanswer2text_label.setForeground(Color.red);
                checkanswer2text_label.setVisible(true);
                checkanswer2text_label.setText("Ok");
                checkanswer2text_label.setForeground(Color.green);
                answer2_checkbox.setText(answer2_textfield.getText());
                answer2_radiobutton.setText(answer2_textfield.getText());
            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (answer2_textfield.getText().trim().isEmpty()) {
                checkanswer2text_label.setVisible(true);
                checkanswer2text_label.setText("Vuoto");
                checkanswer2text_label.setForeground(Color.red);
                answer2_checkbox.setText("Risposta 2");
                answer2_radiobutton.setText("Risposta 2");
            } else {
                checkanswer2text_label.setForeground(Color.red);
                checkanswer2text_label.setVisible(true);
                checkanswer2text_label.setText("Ok");
                checkanswer2text_label.setForeground(Color.green);
                answer2_checkbox.setText(answer2_textfield.getText());
                answer2_radiobutton.setText(answer2_textfield.getText());
            }
        }
    }

    public class Answer3TextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {
            if (answer3_textfield.getText().trim().isEmpty()) {
                checkanswer3text_label.setVisible(true);
                checkanswer3text_label.setText("Vuoto");
                checkanswer3text_label.setForeground(Color.red);
                answer3_checkbox.setText("Risposta 3");
                answer3_radiobutton.setText("Risposta 3");
            } else {
                checkanswer3text_label.setForeground(Color.red);
                checkanswer3text_label.setVisible(true);
                checkanswer3text_label.setText("Ok");
                checkanswer3text_label.setForeground(Color.green);
                answer3_checkbox.setText(answer3_textfield.getText());
                answer3_radiobutton.setText(answer3_textfield.getText());
            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (answer3_textfield.getText().trim().isEmpty()) {
                checkanswer3text_label.setVisible(true);
                checkanswer3text_label.setText("Vuoto");
                checkanswer3text_label.setForeground(Color.red);
                answer3_checkbox.setText("Risposta 3");
                answer3_radiobutton.setText("Risposta 3");
            } else {
                checkanswer3text_label.setForeground(Color.red);
                checkanswer3text_label.setVisible(true);
                checkanswer3text_label.setText("Ok");
                checkanswer3text_label.setForeground(Color.green);
                answer3_checkbox.setText(answer3_textfield.getText());
                answer3_radiobutton.setText(answer3_textfield.getText());
            }
        }
    }

    public class Answer4TextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {
            if (answer4_textfield.getText().trim().isEmpty()) {
                checkanswer4text_label.setVisible(true);
                checkanswer4text_label.setText("Vuoto");
                checkanswer4text_label.setForeground(Color.red);
                answer4_checkbox.setText("Risposta 4");
                answer4_radiobutton.setText("Risposta 4");

            } else {
                checkanswer4text_label.setForeground(Color.red);
                checkanswer4text_label.setVisible(true);
                checkanswer4text_label.setText("Ok");
                checkanswer4text_label.setForeground(Color.green);
                answer4_checkbox.setText(answer4_textfield.getText());
                answer4_radiobutton.setText(answer4_textfield.getText());
            }
        }

        public void keyPressed(KeyEvent ke) {
        }

        public void keyReleased(KeyEvent ke) {
            if (answer4_textfield.getText().trim().isEmpty()) {
                checkanswer4text_label.setVisible(true);
                checkanswer4text_label.setText("Vuoto");
                checkanswer4text_label.setForeground(Color.red);
                answer4_checkbox.setText("Risposta 4");
                answer4_radiobutton.setText("Risposta 4");
            } else {
                checkanswer4text_label.setForeground(Color.red);
                checkanswer4text_label.setVisible(true);
                checkanswer4text_label.setText("Ok");
                checkanswer4text_label.setForeground(Color.green);
                answer4_checkbox.setText(answer4_textfield.getText());
                answer4_radiobutton.setText(answer4_textfield.getText());
            }
        }
    }

    public class CheckBoxActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            imageyn_checkbox = (JCheckBox) ae.getSource();
            if (imageyn_checkbox.isSelected()) {
                imagepath_label.setVisible(true);
                imagepath_textfield.setVisible(true);
                imagepath_textfield.setText("");
                checkimagepathtext_label.setText("Non Trovata");
                imagepreview_label.setIcon(null);
                help_label.setVisible(true);

            } else {
                help_label.setVisible(false);
                imagepreview_label.setIcon(null);
                imagepath_textfield.setText("");
                preview_label.setVisible(false);
                imagepreview_label.setVisible(false);
                imagepath_label.setVisible(false);
                imagepath_textfield.setVisible(false);
                checkimagepathtext_label.setText("Non Trovata");
                checkimagepathtext_label.setVisible(false);

            }

        }

    }

    public class ImagePathTextFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

        }

        public void keyPressed(KeyEvent ke) {

        }

        public void keyReleased(KeyEvent ke) {

            checkimagepathtext_label.setVisible(true);

            try {
                if (imagepath_textfield.getText().endsWith(" ") || imagepath_textfield.getText().endsWith(".") || imagepath_textfield.getText().endsWith("/") || imagepath_textfield.getText().endsWith("\\") || imagepath_textfield.getText().startsWith("\\") || imagepath_textfield.getText().startsWith("/")) {

                    imagepreview_label.setIcon(null);
                    imagepreview_label.setVisible(false);
                } else {
                    imagepreview_icon = new ImageIcon(getClass().getResource("/img/" + imagepath_textfield.getText()));
                    imagepreview_label.setIcon(imagepreview_icon);
                }

                if (imagepath_textfield.getText().trim().isEmpty()) {
                    imagepreview_label.setVisible(false);
                    imagepreview_label.setIcon(null);
                    checkimagepathtext_label.setText("Non Trovata");
                    checkimagepathtext_label.setForeground(Color.red);
                } else {
                    if (imagepreview_label.getIcon() == null) {
                        imagepreview_label.setVisible(false);
                        preview_label.setVisible(true);
                    } else {
                        imagepreview_label.setVisible(true);
                    }

                }

            } catch (Exception e) {
                imagepreview_label.setIcon(null);
                imagepreview_label.setVisible(false);
                checkimagepathtext_label.setText("Non Trovata");
                checkimagepathtext_label.setForeground(Color.red);

            }

            if (imagepreview_label.getIcon() == null) {

                checkimagepathtext_label.setVisible(true);
                checkimagepathtext_label.setText("Non Trovata");
                checkimagepathtext_label.setForeground(Color.red);
            } else if (imagepreview_label.isVisible()) {
                if (!imagepath_textfield.getText().trim().isEmpty()) {
                    checkimagepathtext_label.setForeground(Color.red);
                    checkimagepathtext_label.setVisible(true);
                    checkimagepathtext_label.setText("Ok");
                    checkimagepathtext_label.setForeground(Color.green);
                }

            }

        }

    }

    public class ContinueActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            Object selected = subjects_combobox.getSelectedItem();
            File folder = new File("test_files/" + selected.toString());
            File[] listOfFiles = folder.listFiles();

            if (!checkname_label.getText().equals("Vuoto")) {
                String s = testname_textfield.getText() + ".dat";

                if (listOfFiles.length == 0) {
                    selected = subjects_combobox.getSelectedItem();
                    Object n = nquestions_combobox.getSelectedItem();
                    subject = selected.toString();
                    nquestions = Integer.parseInt(n.toString());
                    testname = testname_textfield.getText().trim();
                    
                    basesettings_frame.dispose();
                    CreateTestFrame.this.setVisible(true);
                }

                boolean flag = false;

                for (int i = 0; i < listOfFiles.length; i++) {

                    if (s.equals(listOfFiles[i].getName())) {
                        flag = true;

                    }

                }

                if (flag == true) {
                    JOptionPane.showMessageDialog(basesettings_frame, "Questo nome è già stato usato per questo argomento");
                } else {

                    selected = subjects_combobox.getSelectedItem();
                    Object n = nquestions_combobox.getSelectedItem();
                    subject = selected.toString();
                    nquestions = Integer.parseInt(n.toString());
                    testname = testname_textfield.getText().trim();
                    basesettings_frame.dispose();
                    CreateTestFrame.this.setVisible(true);
                }

            } else {
                JOptionPane.showMessageDialog(basesettings_frame, "Campo Nome Vuoto");
            }
        }

    }

    public class TitleFieldActions implements KeyListener {

        public void keyTyped(KeyEvent ke) {

            if (testname_textfield.getText().isEmpty()) {

                checkname_label.setVisible(true);
                checkname_label.setText("Vuoto");
                checkname_label.setForeground(Color.red);
            } else {
                checkname_label.setVisible(true);
                checkname_label.setForeground(Color.red);
                checkname_label.setText("Ok");
                checkname_label.setForeground(Color.green);

            }
        }

        public void keyPressed(KeyEvent ke) {

        }

        public void keyReleased(KeyEvent ke) {
            if (testname_textfield.getText().trim().isEmpty()) {

                checkname_label.setVisible(true);
                checkname_label.setText("Vuoto");
                checkname_label.setForeground(Color.red);
            } else {
                checkname_label.setVisible(true);
                checkname_label.setForeground(Color.red);
                checkname_label.setText("Ok");
                checkname_label.setForeground(Color.green);

            }
        }

    }

    public class HelpActions implements MouseListener {

        public void mouseClicked(MouseEvent me) {

            JOptionPane.showMessageDialog(CreateTestFrame.this, "Inserisci un'immagine nella cartella /src/img dell'applicazione corrente cercala con nome ed estensione nel field di ricerca immagini, \n se la tua immagine è lì verrà visualizzata la sua anteprima. es(blackhole.jpg)");

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

    public class RadioButtonActions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer1_radiobutton = (JRadioButton) ae.getSource();

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_radiobutton.isSelected()) {

                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_radiobutton.isSelected()) {

                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_radiobutton.isSelected()) {

                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_radiobutton.isSelected()) {

                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

        }

    }

    public class RadioButton2Actions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer2_radiobutton = (JRadioButton) ae.getSource();

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_radiobutton.isSelected()) {

                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_radiobutton.isSelected()) {

                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_radiobutton.isSelected()) {

                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_radiobutton.isSelected()) {

                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }

        }

    }

    public class RadioButton3Actions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer3_radiobutton = (JRadioButton) ae.getSource();

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_radiobutton.isSelected()) {

                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_radiobutton.isSelected()) {

                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_radiobutton.isSelected()) {

                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_radiobutton.isSelected()) {

                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }
        }

    }

    public class RadioButton4Actions implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            answer4_radiobutton = (JRadioButton) ae.getSource();

            button1_label.setVisible(true);
            button2_label.setVisible(true);
            button3_label.setVisible(true);
            button4_label.setVisible(true);

            if (answer1_radiobutton.isSelected()) {

                button1_label.setIcon(correct_icon);
            } else {
                button1_label.setIcon(wrong_icon);
            }
            if (answer2_radiobutton.isSelected()) {

                button2_label.setIcon(correct_icon);
            } else {
                button2_label.setIcon(wrong_icon);
            }
            if (answer3_radiobutton.isSelected()) {

                button3_label.setIcon(correct_icon);
            } else {
                button3_label.setIcon(wrong_icon);
            }
            if (answer4_radiobutton.isSelected()) {

                button4_label.setIcon(correct_icon);
            } else {
                button4_label.setIcon(wrong_icon);
            }
        }

    }

}
