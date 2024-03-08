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
public class BinaryFilesManagement {

    FileOutputStream fos;
    FileInputStream fis;
    ObjectOutputStream oos;
    ObjectInputStream ois;

    public BinaryFilesManagement() {

    }

    public void writeQuestionFile(ArrayList<Question> aq, String path, String s) {

        try {
            fos = new FileOutputStream("test_files/" + path + "/" + s + ".dat");
            oos = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        for (int i = 0; i < aq.size(); i++) {
            try {
                oos.writeObject(aq.get(i));
            } catch (IOException ex) {
                ex.getMessage();
            }
        }

        try {
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    public ArrayList readQuestionFile(String path, String s) {

        ArrayList<Question> aq = new ArrayList();
        try {
            fis = new FileInputStream("test_files/" + path + "/" + s + ".dat");
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }

        boolean flag = false;

        do {

            try {
                aq.add((Question) ois.readObject());

            } catch (ClassNotFoundException ex) {

            } catch (EOFException ex) {
                flag = true;
            } catch (IOException ex) {
                ex.getMessage();
            }

        } while (!flag);

        try {
            ois.close();
            fis.close();
        } catch (IOException ex) {
            ex.getMessage();
        }

        return aq;
    }

    public void writeAnswerFile(ArrayList<Answer> aa, String path, String s) {

        try {
            fos = new FileOutputStream("test_files/" + path + "A" + "/" + s + "Answers" + ".dat");
            oos = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        for (int i = 0; i < aa.size(); i++) {
            try {
                oos.writeObject(aa.get(i));
            } catch (IOException ex) {
                ex.getMessage();
            }
        }

        try {
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.getMessage();
        }

    }

    public ArrayList readAnswerFile(String path, String s) {

        ArrayList<Answer> aa = new ArrayList();
        try {
            fis = new FileInputStream("test_files/" + path + "A" + "/" + s + "Answers" + ".dat");
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }

        boolean flag = false;

        do {

            try {
                aa.add((Answer) ois.readObject());

            } catch (ClassNotFoundException ex) {

            } catch (EOFException ex) {
                flag = true;
            } catch (IOException ex) {
                ex.getMessage();
            }

        } while (!flag);

        try {
            ois.close();
            fis.close();
        } catch (IOException ex) {
            ex.getMessage();
        }

        return aa;
    }

    public void writeScoresFile(String arg, String subj, ArrayList<String> as) {
        try {
            fos = new FileOutputStream("test_files/" + arg + "S" + "/" + subj + "Scores" + ".dat");
            oos = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }

        for (int i = 0; i < as.size(); i++) {
            try {
                oos.writeObject(as.get(i));
            } catch (IOException ex) {
                ex.getMessage();
            }
        }

        try {
            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    public void createScoresFile(String arg, String subj) {
        try {
            fos = new FileOutputStream("test_files/" + arg + "S" + "/" + subj + "Scores" + ".dat");
            oos = new ObjectOutputStream(fos);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }

    public ArrayList readScoresFile(String path, String s) {

        ArrayList<String> as = new ArrayList();
        try {
            fis = new FileInputStream("test_files/" + path + "S" + "/" + s + "Scores" + ".dat");
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        }

        boolean flag = false;

        do {

            try {
                as.add((String) ois.readObject());

            } catch (ClassNotFoundException ex) {

            } catch (EOFException ex) {
                flag = true;
            } catch (IOException ex) {
                ex.getMessage();
            } catch (NullPointerException ex) {
                System.out.println("null");
                return as;
            }

        } while (!flag);

        try {
            ois.close();
            fis.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        return as;
    }

}
