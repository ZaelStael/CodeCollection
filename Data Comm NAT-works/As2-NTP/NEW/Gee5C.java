package NEW;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

public class Gee5C {
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
