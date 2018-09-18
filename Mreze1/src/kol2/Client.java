package kol2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public String name;
	
	public Client() {
		try {
			Socket socket = new Socket("localhost", 2018);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

			String greet = reader.readLine();
			System.out.println(greet);
			System.out.println("Please enter your name");
			Scanner s = new Scanner(System.in);
			String name = s.nextLine();
			writer.println(name);
			if(name.compareTo("Quit")==0 || name==null) {
				return;
			}
			while(true) {
				if(System.in.available()!=0) {
					
					String message = s.nextLine();
					if(message.compareTo("Quit")==0 || message==null) {
						return;
					}
					writer.println(name + ":" + message);
				}
				if(reader.ready()) {
					String message = reader.readLine();
					System.out.println(message);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = new Client();
	}

}
