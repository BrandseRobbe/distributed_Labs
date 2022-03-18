package main.java;// Client Side
import java.io.*;
import java.net.*;

public class ClientSocket {
    public void run() {
        try {
            int serverPort = 4020;
            InetAddress host = InetAddress.getByName("localhost");
            System.out.println("Connecting to server on port " + serverPort);

            byte[] b = new byte[2000000];
            Socket socket = new Socket(host,serverPort);
            InputStream is = socket.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\seppe\\Desktop\\ontvangst.txt");
            is.read(b, 0, b.length);
            fileOutputStream.write(b, 0, b.length);

            //Socket socket = new Socket("127.0.0.1", serverPort);
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            PrintWriter toServer =
                    new PrintWriter(socket.getOutputStream(),true);
            BufferedReader fromServer =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            toServer.println("Hello from " + socket.getLocalSocketAddress());
            String line = fromServer.readLine();
            System.out.println("Client received: " + line + " from Server");
            toServer.close();
            fromServer.close();
            socket.close();
        }
        catch(UnknownHostException ex) {
            ex.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientSocket client = new ClientSocket();
        client.run();
    }
}