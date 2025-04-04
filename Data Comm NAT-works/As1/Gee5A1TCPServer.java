import java.io.*;
import java.net.*;

public class Gee5A1TCPServer {
    public static void main(String argv[]) throws Exception {
        String clientSentence;
        String capitalizedSentence;
        Boolean on = true;

        // Connecting to TCP port 33221
        ServerSocket welcomeSocket = new ServerSocket(33221);

        while (on) {
            // Accepting client connection
            Socket connectionSocket = welcomeSocket.accept();

            // Establishing Input from client and output to client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            // receiving url from client
            clientSentence = inFromClient.readLine();
            // Ensuring connection and reiterating recieved url
            System.out.println("Client connected.");
            System.out.println("URL recieved = " + clientSentence);

            capitalizedSentence = clientSentence.toUpperCase() + '\n';

            // Sending capital url back to client
            outToClient.writeBytes(capitalizedSentence);

            // Writing recieved site contents
            while ((clientSentence = inFromClient.readLine()) != null) {
                System.out.println(clientSentence);
            }

            // Closing connections
            connectionSocket.close();
            welcomeSocket.close();

            on = false;

        }
    }
}
