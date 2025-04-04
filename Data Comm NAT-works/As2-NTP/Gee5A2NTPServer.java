import java.net.*;
import java.io.*;
import java.util.*;

public class Gee5A2NTPServer {

    private int NTPPort = 1000;
    private byte[] NTPData = new byte[48];

    private long seventyOffset;
    private long transmitMillis;

    private final byte referenceOffset = 16;
    private final byte originateOffset = 24;
    private final byte receiveOffset = 32;
    private final byte transmitOffset = 40;
    private long transmitTimestamp;

    private DatagramPacket NTPPacket;
    private DatagramSocket NTPSocket;

    // public Gee5NTPServer() {

    // try {
    // System.out.println("Server started!");

    // seventyOffset = 70 * 365;
    // seventyOffset += 17;
    // seventyOffset *= 24;
    // seventyOffset *= 60;
    // seventyOffset *= 60;
    // seventyOffset *= 1000;

    // NTPPacket = new DatagramPacket(NTPData, NTPData.length);
    // NTPSocket = new DatagramSocket(NTPPort);

    // while (true) {

    // NTPSocket.receive(NTPPacket);

    // String rcvd = "from address: " + NTPPacket.getAddress() + ", port: " +
    // NTPPacket.getPort();
    // System.out.println(rcvd);
    // NTPData = NTPPacket.getData();
    // transmitTimestamp = toLong(transmitOffset);
    // initPacket();
    // DatagramPacket echo = new DatagramPacket(NTPData, NTPData.length,
    // NTPPacket.getPort());
    // NTPSocket.send(echo);
    // }
    // } catch (SocketException e) {
    // System.err.println("Can't open socket");
    // System.exit(1);
    // } catch (IOException e) {
    // System.err.println("Communication error!");
    // e.printStackTrace();
    // } catch (IllegalArgumentException e) {
    // System.err.println("Argument/Address error!");
    // e.printStackTrace();
    // }

    // }

    public Gee5A2NTPServer() {
        try {

            NTPPacket = new DatagramPacket(NTPData, NTPData.length);
            DatagramSocket NTPSocket = new DatagramSocket(NTPPort);

            NTPSocket.receive(NTPPacket);

            InetAddress clientAddress = NTPPacket.getAddress();
            int clientPort = NTPPacket.getPort();

            // Generate a random delay
            Random random = new Random();
            int delay = 1 + random.nextInt(10000); // Random delay between 1 and 10,000 ms
            System.out.println("d = " + delay);
            Thread.sleep(delay);

            // Initialize and send the response packet
            initPacket();
            DatagramPacket responsePacket = new DatagramPacket(NTPData, NTPData.length, clientAddress, clientPort);
            NTPSocket.send(responsePacket);

            System.out.println("Stopping client thread");
            NTPSocket.close();
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("Error handling client request");
            e.printStackTrace();
        }
    }

    private void setOrigTime() {
        toBytes(transmitTimestamp, originateOffset);
    }

    private void setReferenceTime() {
        toBytes(transmitTimestamp, referenceOffset);
    }

    private void setReceiveTime() {
        GregorianCalendar startCal = new GregorianCalendar();
        long startMillis = startCal.getTimeInMillis();
        transmitMillis = startMillis + seventyOffset;
        toBytes(transmitMillis, receiveOffset);
    }

    private void setTransmitTime() {
        GregorianCalendar startCal = new GregorianCalendar();
        long startMillis = startCal.getTimeInMillis();
        transmitMillis = startMillis + seventyOffset;
        toBytes(transmitMillis, transmitOffset);

    }

    public void toBytes(long n, int offset) {
        long intPart = 0;
        long fracPart = 0;
        intPart = n / 1000;
        fracPart = ((n % 1000) / 1000) * 0X100000000L;

        NTPData[offset + 0] = (byte) (intPart >>> 24);
        NTPData[offset + 1] = (byte) (intPart >>> 16);
        NTPData[offset + 2] = (byte) (intPart >>> 8);
        NTPData[offset + 3] = (byte) (intPart);

        NTPData[offset + 4] = (byte) (fracPart >>> 24);
        NTPData[offset + 5] = (byte) (fracPart >>> 16);
        NTPData[offset + 6] = (byte) (fracPart >>> 8);
        NTPData[offset + 7] = (byte) (fracPart);
    }

    public long toLong(int offset) {
        long intPart = ((((long) NTPData[offset + 3]) & 0xFF)) + ((((long) NTPData[offset + 2]) & 0xFF) << 8)
                + ((((long) NTPData[offset + 1]) & 0xFF) << 16) + ((((long) NTPData[offset + 0]) & 0xFF) << 24);
        long fracPart = ((((long) NTPData[offset + 7]) & 0xFF)) + ((((long) NTPData[offset + 6]) & 0xFF) << 8)
                + ((((long) NTPData[offset + 5]) & 0xFF) << 16) + ((((long) NTPData[offset + 4]) & 0xFF) << 24);

        long millisLong = (intPart * 1000) + (fracPart * 1000) / 0x100000000L;
        return millisLong;
    }

    private void initPacket() {
        try {
            NTPData[0] = 0x1C;
            for (int i = 1; i < 16; i++) {
                NTPData[i] = 0;
            }

            setReferenceTime();
            setOrigTime();
            setReceiveTime();
            setTransmitTime();
        } catch (Exception e) {

        }
    }

    public void run() {
        try {
            InetAddress clientAddress = NTPPacket.getAddress();
            int clientPort = NTPPacket.getPort();

            // Generate a random delay
            Random random = new Random();
            int delay = 1 + random.nextInt(10000); // Random delay between 1 and 10,000 ms
            System.out.println("d = " + delay);
            Thread.sleep(delay);

            // Initialize and send the response packet
            initPacket();
            DatagramPacket responsePacket = new DatagramPacket(NTPData, NTPData.length, clientAddress, clientPort);
            NTPSocket.send(responsePacket);

            System.out.println("Stopping client thread");
        } catch (InterruptedException e) {
            System.err.println("Thread interrupted");
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            System.err.println("Error handling client request");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Gee5A2NTPServer b = new Gee5A2NTPServer();
        // b.run();

    }

}