package tr2.server.main;

import java.io.IOException;
import java.net.UnknownHostException;

import tr2.server.http.HttpServerDispatcher;
import tr2.server.http.UserDB;
import tr2.server.sync.controller.Controller;

public class Main {

	public static void main(String[] args) {
		
		try {
			System.out.println("[HTTP SERVER] Starting server...");
			UserDB.instance();
			new Controller();

			Thread dispatcher = new Thread(HttpServerDispatcher.instance());
			dispatcher.start();
			System.out.println("[HTTP SERVER] Server started successfully!");
			
//			try {
//				Thread.sleep(50000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			
//			HashMap<String, User> users = new HashMap<String, User>();
//			users.put("jacopo", new User("jacopo", UserType.ADMIN));
//			users.put("alexandre", new User("alexandre", UserType.USER));
//			UserDB.setUsers(users);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
