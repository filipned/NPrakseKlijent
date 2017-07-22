/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ClientRequest;
import transfer.ServerResponse;

/**
 *
 * @author FILIP
 */
public class Communication {
    
    private static Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;
    
    private static Communication instance;

    private Communication() {
        try {
            socket = new Socket("localhost", 9000);
            System.out.println("Uspjesno postavljen soket!");
            
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Uspjesno postavljen izlazni strim!");
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            System.out.println("Uspjesno postavljen ulazni stream!");
        } catch (IOException ex) {
            System.out.println("Greska pri inicijalizaciji soketa i strimova");
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Communication getInstance() {
        if(instance == null) {
            instance = new Communication();
        }
        return instance;
    }
    
    public void sendRequest(ClientRequest clr) throws IOException {
        out.writeObject(clr);
    }
    
    public ServerResponse recieveResponse() throws IOException, ClassNotFoundException {
        return (ServerResponse) in.readObject();
    }
}
