package kr.ac.sungkyul.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeServer {

	private static final int SERVER_PORT = 1000;
	private static final int BUFFER_SIZE = 1024;

	public static void main(String[] args) {

		DatagramSocket socket = null;

		try {

			socket = new DatagramSocket(SERVER_PORT);

			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
			socket.receive(receivePacket);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String data = format.format(new Date());
			

			// 데이터 수신
//			data= new String(receivePacket.getData(), 0, receivePacket.getLength(), "utf-8");
			System.out.println("수신 완료" );

			// 데이터 송신
			byte[] sendData = data.getBytes(StandardCharsets.UTF_8);
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
			new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort()));

			socket.send(sendPacket);

		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
			if(socket!=null && socket.isClosed()){
				socket.close();
			}
			}catch(Exception e){
				e.printStackTrace();
			}
		}

	}

}
