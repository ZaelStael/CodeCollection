import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {

            DatagramSocket server = new DatagramSocket(33221);
            byte[] buf = new byte[256];
            DatagramPacket p = new DatagramPacket(buf, buf.length);
            server.receive(p);
            String resp = new String(p.getData());
            System.out.println("Response Data: " + resp);

        } catch (SocketException e) {

        } catch (IOException i) {

        }
    }

}
