/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author office
 */
public class ServerThread extends Thread {

    String newLine = System.getProperty("line.separator");
    ServerSocket server_me;
    Socket client;
    String recString;
    String modifString;
    BufferedReader inFromClient;
    DataOutputStream outForClient;
    DataManagement dm;
    BinaryFilesManagement bfm;

    public ServerThread(Socket socket) {

        this.client = socket;

    }

    public void run() {
        try {
            communicate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public void communicate() {

        try {
            inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outForClient = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            System.err.println("Error during opening the comunication channel");
        }

        out:
        for (;;) {
            try {

                dm = new DataManagement();
                bfm = new BinaryFilesManagement();

                int result;
                int t;
                recString = inFromClient.readLine();
                System.out.println(recString);
                switch (recString) {
                    case "LOGIN":
                        
                        String u=inFromClient.readLine();
                        String p=inFromClient.readLine();
                        
                        int r = dm.findUser(u,p);
                        
                        
                        
                        
                        if(r>=0){

                            t=MultiServer.a.add(u, p);
                            
                            
                            
                            
                            
                            
                            switch(t){
                                
                                case 1:
                                    
                           
                                    
                                    
                                    break;
                                    
                                case -2:
                                    
                                    
                          
                                    r=-2;
                                    
                                    break;
                                
                                
                            }
                        }
                        
                        
                            
                            
                        
                        
                        
                        
                        
                        
                        outForClient.writeBytes(String.valueOf(r));
                        outForClient.writeBytes(newLine);
                        
                        
                        
                        
                        
                        
                        
                        if (r >= 0) {
                            if (dm.user_array.get(r).isAdmin() == true) {
                                outForClient.writeBytes("Admin");
                                outForClient.writeBytes(newLine);
                            } else {
                                outForClient.writeBytes(dm.user_array.get(r).getId());
                                outForClient.writeBytes(newLine);
                            }
                        }
                        break;

                    case "END":

                        break out;

                        
                        
                    case "READSCORE":
                        
                        ArrayList asr=new ArrayList();
                        
                        asr=bfm.readScoresFile(inFromClient.readLine(), inFromClient.readLine());
                        
                        
                        outForClient.writeBytes(String.valueOf(asr.size()));
                        outForClient.writeBytes(newLine);
                        
                        for(int i=0;i<asr.size();i++){
                      
                            outForClient.writeBytes((String) asr.get(i));
                            outForClient.writeBytes(newLine);
                            
                        }
                        
                        
                        break;
                        
                    case "WRITESCORE":
                        ArrayList as=new ArrayList();
                        String argsc=inFromClient.readLine();
            
                        System.out.println(argsc);
                        String testnamesc=inFromClient.readLine();
              
                        System.out.println(testnamesc);
                        int sn=Integer.parseInt(inFromClient.readLine());
                  
                        System.out.println(sn);
                        
                        for(int i=0;i<sn;i++){
                            String scr=inFromClient.readLine();
                            System.out.println(scr);
                            as.add(scr);
                        }
                        
                        bfm.writeScoresFile(argsc, testnamesc, as);
                        
                        
                        break;
                        
                    case "WRITETEST":
                        Answer a;
                        Question q;

                        ArrayList<Question> aq = new ArrayList();
                        ArrayList<Answer> aa = new ArrayList();
                        try {
                            int aqn = Integer.parseInt(inFromClient.readLine());
                            int aan = Integer.parseInt(inFromClient.readLine());
                            

                            for (int i = 0; i < aqn; i++) {
                                q = new Question(inFromClient.readLine(), Boolean.parseBoolean(inFromClient.readLine()), inFromClient.readLine(), Boolean.parseBoolean(inFromClient.readLine()));
                                System.out.println(q);
                                aq.add(q);
                              
                            }

                            for (int i = 0; i < aan; i++) {
                                a = new Answer(inFromClient.readLine(), Boolean.parseBoolean(inFromClient.readLine()));
                                aa.add(a);
                            }

                            String subject = inFromClient.readLine();
                            String testname = inFromClient.readLine();
                            
                            bfm.writeQuestionFile(aq, subject, testname);
                            bfm.writeAnswerFile(aa, subject, testname);
                            bfm.createScoresFile(subject, testname);
                         
                        } catch (Exception e) {

                        }

                        break out;

                    case "READ":
                            
                        ArrayList<Answer> aar=new ArrayList();
                        ArrayList<Question> aqr=new ArrayList();
                        String arg=inFromClient.readLine();
                        String testname=inFromClient.readLine();
                        aqr = bfm.readQuestionFile(arg, testname);
                        aar = bfm.readAnswerFile(arg, testname);
                        
                        outForClient.writeBytes(String.valueOf(aqr.size()));
                        outForClient.writeBytes(newLine);
                         outForClient.writeBytes(String.valueOf(aar.size()));
                        outForClient.writeBytes(newLine);
                        
                        for(int i=0;i<aqr.size();i++){
                     
                            outForClient.writeBytes(aqr.get(i).questiontext);
                            outForClient.writeBytes(newLine);
                            outForClient.writeBytes(String.valueOf(false));
                            outForClient.writeBytes(newLine);
                            outForClient.writeBytes("");
                            outForClient.writeBytes(newLine);
                            outForClient.writeBytes(String.valueOf(aqr.get(i).isTwoanswers()));
                            outForClient.writeBytes(newLine);
                            
                        }
                        
                        for(int i=0;i<aar.size();i++){
          
                            outForClient.writeBytes(aar.get(i).getAnswertext());
                            outForClient.writeBytes(newLine);
                            outForClient.writeBytes(String.valueOf(aar.get(i).isCorrect()));
                            outForClient.writeBytes(newLine);
                        }
                        
                        
                        
                            
                          break;
                    case "SELECTTESTARTE":
                        
                        File folderart = new File("test_files/Arte");
                        File[] listOfFilesart = folderart.listFiles();
                        String[] testsart = new String[listOfFilesart.length];
                        for (int i = 0; i < listOfFilesart.length; i++) {
                             testsart[i] = listOfFilesart[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesart.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesart.length;i++){
                            outForClient.writeBytes(testsart[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        case "SELECTTESTSCIENZE":
                        
                        File foldersc = new File("test_files/Scienze");
                        File[] listOfFilessc = foldersc.listFiles();
                        String[] testssc = new String[listOfFilessc.length];
                        for (int i = 0; i < listOfFilessc.length; i++) {
                             testssc[i] = listOfFilessc[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilessc.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilessc.length;i++){
                            outForClient.writeBytes(testssc[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        case "SELECTTESTSPORT":
                        
                        File foldersp = new File("test_files/Sport");
                        File[] listOfFilessp = foldersp.listFiles();
                        String[] testssp = new String[listOfFilessp.length];
                        for (int i = 0; i < listOfFilessp.length; i++) {
                             testssp[i] = listOfFilessp[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilessp.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilessp.length;i++){
                            outForClient.writeBytes(testssp[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        
                        case "SELECTTESTALTRO":
                      
                        File folderal = new File("test_files/Altro");
                        File[] listOfFilesal = folderal.listFiles();
                      
                        String[] testsal = new String[listOfFilesal.length];
                        for (int i = 0; i < listOfFilesal.length; i++) {
                             testsal[i] = listOfFilesal[i].getName();
                             System.out.println(testsal[i]);
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesal.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesal.length;i++){
                            outForClient.writeBytes(testsal[i]);
                            outForClient.writeBytes(newLine);
                            
                        }
                            
                        
                        break;
                        
                        case "SELECTTESTITA":
                        
                        File folderi = new File("test_files/Italiano");
                        File[] listOfFilesi = folderi.listFiles();
                        String[] testsi = new String[listOfFilesi.length];
                        for (int i = 0; i < listOfFilesi.length; i++) {
                             testsi[i] = listOfFilesi[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesi.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesi.length;i++){
                            outForClient.writeBytes(testsi[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        case "SELECTTESTCINEMA":
                        
                        File folderc = new File("test_files/Cinema");
                        File[] listOfFilesc = folderc.listFiles();
                        String[] testsc = new String[listOfFilesc.length];
                        for (int i = 0; i < listOfFilesc.length; i++) {
                             testsc[i] = listOfFilesc[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesc.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesc.length;i++){
                            outForClient.writeBytes(testsc[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        case "SELECTTESTGEO":
                        
                        File folderg = new File("test_files/Geografia");
                        File[] listOfFilesg = folderg.listFiles();
                        String[] testsg = new String[listOfFilesg.length];
                        for (int i = 0; i < listOfFilesg.length; i++) {
                             testsg[i] = listOfFilesg[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesg.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesg.length;i++){
                            outForClient.writeBytes(testsg[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        
                        case "SELECTTESTSTORY":
                        
                        File folderh = new File("test_files/Storia");
                        File[] listOfFilesh = folderh.listFiles();
                        String[] testsh = new String[listOfFilesh.length];
                        for (int i = 0; i < listOfFilesh.length; i++) {
                             testsh[i] = listOfFilesh[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesh.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesh.length;i++){
                            outForClient.writeBytes(testsh[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        
                        case "SELECTTESTMATH":
                        
                        File folderm = new File("test_files/Matematica");
                        File[] listOfFilesm = folderm.listFiles();
                        String[] testsm = new String[listOfFilesm.length];
                        for (int i = 0; i < listOfFilesm.length; i++) {
                             testsm[i] = listOfFilesm[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilesm.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilesm.length;i++){
                            outForClient.writeBytes(testsm[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                        
                        case "SELECTTESTCT":
                        
                        File foldert = new File("test_files/Tecnologia e Informatica");
                        File[] listOfFilest = foldert.listFiles();
                        String[] testst = new String[listOfFilest.length];
                        for (int i = 0; i < listOfFilest.length; i++) {
                             testst[i] = listOfFilest[i].getName();
                        }
                        
                        outForClient.writeBytes(String.valueOf(listOfFilest.length));
                        outForClient.writeBytes(newLine);
                        for(int i=0;i<listOfFilest.length;i++){
                            outForClient.writeBytes(testst[i]);
                            outForClient.writeBytes(newLine);
                        }
                        
                        break;
                        
                      
                    case "REGISTER":
                        dm.writeUserFile(inFromClient.readLine(), inFromClient.readLine());

                        break;
                }

            } catch (Exception e) {
                e.getCause();
            }
        }

        try {

            outForClient.close();
            inFromClient.close();

        } catch (Exception e) {
            System.err.println("Error during closing the communication channel");
        }

    }
}
