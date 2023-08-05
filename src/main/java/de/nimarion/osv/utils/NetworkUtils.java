package de.nimarion.osv.utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.List;
import java.io.IOException;

public class NetworkUtils {

    public static int getRandomFreePort() throws IOException {
        try (ServerSocket socket = new ServerSocket(0)) {
            return socket.getLocalPort();
        }
    }

    public static void broadcastUDP(byte[] data, String ip, int port, InetSocketAddress bindNetworkInterfaceAddress) {
        try {
            InetAddress address = InetAddress.getByName(ip);
            DatagramSocket socket = new DatagramSocket(null);
            socket.bind(bindNetworkInterfaceAddress);
            socket.setBroadcast(true);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcastUDP(byte[] data, List<String> ips, int port, InetSocketAddress bindNetworkInterfaceAddress) {
        for (String ip : ips) {
            broadcastUDP(data, ip, port, bindNetworkInterfaceAddress);
        }
    }

}
