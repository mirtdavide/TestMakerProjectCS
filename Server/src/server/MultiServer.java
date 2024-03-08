package server;

import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author office
 */
public class MultiServer {
    
    
    
    
    
    static ArrayUser a=new ArrayUser();
    
    ServerSocket server_me;
    Socket socket;
    
    public void start(){
        try{
            server_me=new ServerSocket(6789);
            
        }catch(Exception e){
            System.err.println("Error during socket opening");
            System.exit(1);
        }
        
        for(;;){
            System.out.println("Server waiting...");
            try{
                socket=server_me.accept();
                
                
                System.out.println("CLIENT Connected");
            }catch(Exception e){
                System.err.println("Error during connection with client...");
                System.exit(1);
            }
            System.out.println("Server socket "+""+socket);
            ServerThread serverThread=new ServerThread(socket);
            serverThread.start();
                    
            
        }
    }
    
    
}
