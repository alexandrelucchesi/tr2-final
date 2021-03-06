package tr2.server.common.tcp;

public interface TCPController {
	
	public void notifyDisconnected(String address);
	
	public void notifyMessageReceived(String message, String localAddress, String address);

	public void notifyConnected(String address);
	
}
