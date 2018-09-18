package kol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

	Socket socket;
	ServerMain server;
	String clientName;
	BufferedReader reader;
	PrintWriter writer;
	
	public ServerThread(Socket socket, ServerMain serverMain) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
		server = serverMain;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
			writer.println("Welcome to the chat room! :)");
			clientName = reader.readLine();
			if(clientName==null || clientName.compareTo("Quit")==0) {
				socket.close();
                reader.close();
                writer.close();
				return;
			}
			server.hello(clientName + " joined the chat room.");
			String message = "";
			String name = "";
			String[] mess;
			while(true) {
				message = reader.readLine();
				if(message==null || message.compareTo("Quit")==0) {
					socket.close();
					reader.close();
	                writer.close();
					return;
				}
				mess = message.split(":");
				server.notifyThreads(mess[1], mess[0]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SendMessage(String message) {
		System.out.println("Sending a message to " + clientName);
		writer.println(message);
	}

}
