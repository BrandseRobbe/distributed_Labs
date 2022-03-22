package main;

import java.io.*;
import java.net.*;

public class UDPServer {
    public void run() {
        try {

            // Setup server
            int serverPort = 1234;
            DatagramSocket server = new DatagramSocket(serverPort);
            byte[] receive = new byte[65535];

            DatagramPacket DpReceive = null;

            while(true) {
                System.out.println("Waiting for client on port " + server.getLocalPort() + "...");

                DpReceive = new DatagramPacket(receive, receive.length);

                // Revieve the data in byte buffer.
                server.receive(DpReceive);

                System.out.println("Client:-" + data(receive));

                // Exit the server if the client sends "exit"
                if (data(receive).toString().equals("exit"))
                {
                    System.out.println("Client sent exit.....EXITING");
                    break;
                }

                // Clear the buffer after every message.
                receive = new byte[65535];
            }
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public static void main(String[] args) {
        UDPServer server = new UDPServer();
        server.run();
    }

}
