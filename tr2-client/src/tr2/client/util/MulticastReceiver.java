package tr2.client.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

import tr2.client.ServerIPsListener;

public class MulticastReceiver extends NetworkConstants implements Runnable {

	private ServerIPsListener listener;
	
	public MulticastReceiver(ServerIPsListener listener) {
		this.listener = listener;
	}
	
	public void run() {
		ArrayList<String> ipList = new ArrayList<String>();
		MulticastSocket socket = null;
		DatagramPacket inPacket = null;
		byte[] inBuf = new byte[256];
		try {
			// Prepare to join multicast group
			socket = new MulticastSocket(NetworkConstants.MULTICAST_PORT);
			InetAddress address = InetAddress.getByName(NetworkConstants.MULTICAST_ADDRESS);

			socket.joinGroup(address);
			
			while (true) {
				inPacket = new DatagramPacket(inBuf, inBuf.length);
				socket.receive(inPacket);
				String message = new String(inBuf, 0, inPacket.getLength());
				System.out.println("From " + inPacket.getAddress() + " : " + message);
				ipList.add(inPacket.getAddress().getHostAddress());
				break;
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		listener.onIPListChangedListener(ipList);
	}
}
