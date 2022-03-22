package main;

import java.net.*;
import java.io.*;

public class ServerSideSocket {
    public void run() {
        try {

            // Setup server
            int serverPort = 4020;
            ServerSocket server = new ServerSocket(serverPort);

            while(true) {
                System.out.println("Waiting for client on port " + server.getLocalPort() + "...");

                // Wait for client to connect
                Socket client = server.accept();
                // serverSocket.setSoTimeout(10000);

                System.out.println("New client connected: " + client.getInetAddress().getHostAddress());

                // create a new thread object
                ClientHandler clientSock = new ClientHandler(client);

                // This thread will handle the client separately
                new Thread(clientSock).start();
            }
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerSideSocket srv = new ServerSideSocket();
        srv.run();
    }
}