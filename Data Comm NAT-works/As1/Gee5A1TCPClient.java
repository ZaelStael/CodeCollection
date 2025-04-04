import java.io.*;
import java.net.*;

public class Gee5A1TCPClient {
    public static void main(String argv[]) throws Exception {
        // Initializing strings and input reader
        String sentence;
        String modifiedSentence;
        String inputLine;
        String buffer = " ";

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        // Connecting to TCP port 33221
        Socket clientSocket = new Socket("localhost", 33221);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // Ensuring connection
        System.out.println("Connection Established.");

        outToServer.flush();
        // Requesting url
        System.out.println(
                "Enter the last two components in the domain name for a web server W as a string exactly in the form name.suf (for example, enter towson.edu for Towson University's web server W).");
        sentence = inFromUser.readLine();

        // Sending url to server
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();

        System.out.println("FROM SERVER: " + modifiedSentence);

        // Accessing entered webpage
        URI link = new URI("https://" + modifiedSentence);
        URL goLink = link.toURL();
        HttpURLConnection site = (HttpURLConnection) goLink.openConnection();

        // retrieving page contents
        site.setRequestMethod("GET");
        site.setRequestProperty("User", "Browser");

        // Establishing site input reader
        BufferedReader inFromSite = new BufferedReader(new InputStreamReader(site.getInputStream()));

        // Spacing out lines of server in preperation for site content
        outToServer.writeBytes("\n");

        /*
         * Printing site contents to client and preparing to send them to server for
         * printing
         */

        while ((inputLine = inFromSite.readLine()) != null) {
            System.out.println(inputLine);
            buffer += inputLine;

        }

        // sending site content
        PrintWriter yeet = new PrintWriter(clientSocket.getOutputStream());
        yeet.println(buffer);

        // Closing connection
        clientSocket.close();

    }

}
