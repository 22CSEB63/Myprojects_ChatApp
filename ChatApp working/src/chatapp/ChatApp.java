/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class ChatApp extends Frame implements Runnable, ActionListener{

    TextField textfield;
    TextArea textarea;
    Button send;
    ServerSocket serversocket;
    Socket socket;
    DataInputStream input;
    DataOutputStream output;
    Thread chat;
    
    ChatApp(){
        textfield = new TextField();
        textarea = new TextArea();
        send = new Button("Send");
        
        send.addActionListener(this);
        
        try
        {
            serversocket = new ServerSocket(12000);
            socket = serversocket.accept();
            
            input= new DataInputStream(socket.getInputStream());
            output= new DataOutputStream(socket.getOutputStream());
        }
        catch (Exception e){
            
        }
        
        chat= new Thread(this);
        chat.setDaemon(true);
        chat.start();
        
        add(textfield);
        add(textarea);
        add(send);
        
        setSize(500,500);
        setTitle("Yasho");
        setLayout(new FlowLayout());
        setVisible(true);
        
    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e){
        String msg = textfield.getText();
        textarea.append("Yasho:"+msg+"\n");
        textfield.setText("");
        try{
        output.writeUTF(msg);
        output.flush();
        }
        catch (IOException ex){
            
        }
    }
    public static void main(String[] args) {
        new ChatApp();
    }

    @Override
    public void run() {
            while(true){
            try{
                String msg= input.readUTF();
                textarea.append("Varun:"+msg+"\n");
            }
            catch(Exception e){
                
            }
        }
    }

    
    
    
}
