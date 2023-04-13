/**
 * Ahmed Rajgoli Shoaib Shakeel
 * B00878695
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class MultiEchoServer{

	//declare the ServerSocket variable and the port number for the server(constant)
	private static ServerSocket serverSock;
	private static final int PORT = 1234;
	private static ArrayList<ServerHandler> list = new ArrayList<>();

	public static ArrayList<ServerHandler> getClientHandlers()
	{
		return list;
	}

	public static void main(String[] args) throws IOException{
		try{
			serverSock = new ServerSocket(PORT);
		}
		catch(IOException e){
			System.out.println("Can't listen on " + PORT);
			System.exit(1);
		}
		do{
			Socket client = null;
			System.out.println("Listening for connection ...");

			try{
				client = serverSock.accept();
				System.out.println("New client accepted");
				ServerHandler handler = new ServerHandler(client);
				handler.start();

//			modification for the assignment
				list.add(handler);
//			ends

			}

			catch(IOException e){
				System.out.println("Accept failed");
				System.exit(1);
			}
			System.out.println("Connection successful");
			System.out.println("Listening for input ...");

		} while (true);

	}//end main
}//end class MultiEchoServer
