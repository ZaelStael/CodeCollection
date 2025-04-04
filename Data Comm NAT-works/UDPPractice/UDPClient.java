import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        try {

            DatagramSocket client = new DatagramSocket();
            InetAddress add = InetAddress.getByName("localhost");
            String str = "Hello World";
            byte[] buff = str.getBytes();
            DatagramPacket p = new DatagramPacket(buff, buff.length, add, 33221);

            client.send(p);

        } catch (SocketException e) {

        } catch (IOException i) {

        }
    }

}
