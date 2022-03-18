package main.java;// Server Side
import java.net.*;
import java.io.*;

public class ServerSideSocket {
    public void run() {
        try {
            int serverPort = 4020;
            ServerSocket serverSocket = new ServerSocket(serverPort);
            Socket server = serverSocket.accept();
            serverSocket.setSoTimeout(10000);
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\seppe\\Desktop\\examentelecom.txt");
            byte b[] = new byte[2000000];  // needs to be bigger than filesize
            fileInputStream.read(b, 0, b.length);
            OutputStream os = server.getOutputStream();
            os.write(b, 0, b.length);

            while(true) {
                System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");

//                Socket server = serverSocket.accept();
                System.out.println("Just connected to " + server.getRemoteSocketAddress());

                PrintWriter toClient =
                        new PrintWriter(server.getOutputStream(),true);
                BufferedReader fromClient =
                        new BufferedReader(
                                new InputStreamReader(server.getInputStream()));
                String line = fromClient.readLine();
                System.out.println("Server received: " + line);
                toClient.println("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
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