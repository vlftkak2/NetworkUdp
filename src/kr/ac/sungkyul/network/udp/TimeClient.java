package kr.ac.sungkyul.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class TimeClient {

	private static final String SERVER_IP = "192.168.30.1";
	private static final int SERVER_PORT = 1000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {

		DatagramSocket socket = null;

		try {

			socket = new DatagramSocket();

			String message = "";

			byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
					new InetSocketAddress(SERVER_IP, SERVER_PORT));

			socket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			socket.receive(receivePacket);

			String data = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);

			System.out.println(data);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (socket != null && socket.isClosed()) {
				socket.close();

			}
		}

	}

}
