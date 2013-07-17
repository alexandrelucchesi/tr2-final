package tr2.server.http.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import tr2.server.common.util.NetworkConstants;

/**
 * @author lycog
 */
public class MulticastSender implements Runnable {

	public void speak(String message) {

		DatagramSocket socket = null;
		DatagramPacket outPacket = null;
		byte[] outBuf;

		try {
			socket = new DatagramSocket();

			outBuf = message.getBytes();

			// Send to multicast IP address and port
			InetAddress address = InetAddress.getByName(NetworkConstants.CLIENT_MULTICAST_ADDRESS);
			outPacket = new DatagramPacket(outBuf, outBuf.length, address,
					NetworkConstants.CLIENT_MULTICAST_PORT);

			socket.send(outPacket);

			System.out.println("You : " + message);
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	@Override
	public void run() {
		while(true) {
			speak("HTTP HELLO");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}