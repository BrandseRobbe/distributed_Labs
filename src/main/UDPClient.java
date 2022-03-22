package main;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPClient {
    public void run() throws IOException {
        try {
            Scanner sc = new Scanner(System.in);

            // Create the socket object for carrying the data.
            DatagramSocket ds = new DatagramSocket();

            InetAddress ip = InetAddress.getLocalHost();
            byte buf[] = null;

            System.out.println("Connected to server.");

            // Loop while user not enters "exit"
            while (true)
            {


                String inp = sc.nextLine();

                // convert the String input into the byte array.
                buf = inp.getBytes();

                // Create the datagramPacket for sending the data.
                DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);

                // Invoke the send call to actually send
                // the data.
                ds.send(DpSend);

                // break the loop if user enters "exit"
                if (inp.equals("exit")){
                    break;
                }

            }
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        UDPClient client = new UDPClient();
        client.run();
    }
}
