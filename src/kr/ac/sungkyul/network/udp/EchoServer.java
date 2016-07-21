package kr.ac.sungkyul.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class EchoServer {

	private static final int PORT = 1000;

	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {

		DatagramSocket socket = null;
		try {
			// 1. 소켓생성
			// System.out.println("수신대기");
			socket = new DatagramSocket(PORT);

			while (true) {
				// 2. 수신대기
				DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);

				socket.receive(receivePacket); // blocking 데이터가 와야 블락킹이 풀린다.

				// 3. 데이터수신
				String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), "utf-8");
				System.out.println("수신 : " + message);

				// 4. 데이터 송신
				byte[] sendData = message.getBytes(StandardCharsets.UTF_8);
				DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
						new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort()));

				socket.send(sendPacket);
			}

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}

	}

}
