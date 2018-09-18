package kol2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {

	public ArrayList<String> messages;
	public ArrayList<ServerThread> serverThreads;
	
	public ServerMain() {
		try {
			messages = new ArrayList<String>();
			serverThreads = new ArrayList<ServerThread>();
			
			ServerSocket s = new ServerSocket(2018);
			while(true) {
				Socket socket = s.accept();
				ServerThread t = new ServerThread(socket, this);
				Thread thread = new Thread(t);
				thread.start();
				serverThreads.add(t);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerMain serv = new ServerMain();
	}
	
	public void notifyThreads(String message, String name) {
		for(int i = 0; i < serverThreads.size(); i++) {
			if(serverThreads.get(i).clientName.compareTo(name)!=0) {
				System.out.println("Sending the message: " + message + " , from " + name);
				serverThreads.get(i).SendMessage(name + " : " + message);
			}
		}
	}
	
	public void hello(String message) {
		System.out.println(message);
	}

}
