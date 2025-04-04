import java.io.IOException;
import java.net.*;
import java.text.DecimalFormat;

public class Gee5A2NTPClient {

        public static void main(String[] args) throws SocketException {

                NtpMessage buf = new NtpMessage();

                try {
                        String serverName; // Initialize connection name
                        String dummy;

                        serverName = "localhost";

                        DatagramSocket dSocket = new DatagramSocket();
                        InetAddress addy = InetAddress.getByName(serverName);
                        // byte[] buf = new NtpMessage().toByteArray();

                        dummy = "First Message'";
                        byte[] buff = dummy.getBytes();

                        byte[] m = buf.toByteArray();
                        DatagramPacket NTPPacket = new DatagramPacket(m, m.length, addy, 1000);

                        NtpMessage.encodeTimestamp(NTPPacket.getData(), 40,
                                        (System.currentTimeMillis() / 1000.0) + 2208988800.0);

                        System.out.println(dSocket.getPort());
                        System.out.println(NTPPacket.getPort());
                        dSocket.send(NTPPacket);

                        // NTPPacket = new DatagramPacket(buff, buff.length, addy, 33221);
                        // dSocket.send(NTPPacket);

                        System.out.println("NTP request sent, waiting for response...");
                        NTPPacket = new DatagramPacket(m, m.length);
                        dSocket.receive(NTPPacket);
                        NTPPacket = new DatagramPacket(m, m.length);

                        double destinationTimestamp = (System.currentTimeMillis() / 1000.0) + 2208988800.0;
                        NtpMessage msg = new NtpMessage(NTPPacket.getData());

                        double roundTripDelay = (destinationTimestamp - msg.originateTimestamp)
                                        - (msg.transmitTimestamp - msg.receiveTimestamp);
                        double localClockOffset = ((msg.receiveTimestamp - msg.originateTimestamp)
                                        - (msg.transmitTimestamp - destinationTimestamp)) / 2;

                        System.out.println("NTP Server: " + serverName);
                        System.out.println(msg.toString());

                        System.out.println("Dst. Timestamp: " + msg.timestampToString(destinationTimestamp));

                        System.out.println(
                                        "ROund-trip delay: " + new DecimalFormat("0.00").format(roundTripDelay * 1000));

                        System.out.println("Local clock offset: "
                                        + new DecimalFormat("0.00").format(localClockOffset * 1000));

                        dSocket.close();

                } catch (SocketException e) {
                        System.err.println("Can't open socket");
                        System.exit(1);
                } catch (IOException e) {
                        System.err.println("Communication error!");
                        e.printStackTrace();
                }
        }

        static void printUsage() {
                System.out.println(
                                "NtpClient - an NTP client for Java.\n" +
                                                "\n" +
                                                "This program connects to an NTP server and prints the response to the console.\n"
                                                +
                                                "\n" +
                                                "\n" +
                                                "Usage: java NtpClient server\n" +
                                                "\n" +
                                                "\n" +
                                                "This program is copyright (c) Adam Buckley 2004 and distributed under the terms\n"
                                                +
                                                "of the GNU General Public License.  This program is distributed in the hope\n"
                                                +
                                                "that it will be useful, but WITHOUT ANY WARRANTY; without even the implied\n"
                                                +
                                                "warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU\n"
                                                +
                                                "General Public License available at http://www.gnu.org/licenses/gpl.html for\n"
                                                +
                                                "more details.");

        }

}
