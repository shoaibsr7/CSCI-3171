/**
 * Ahmed Rajgoli Shoaib Shakeel
 * B00878695
 */

import java.io.*;
import java.net.*;

public class ServerHandler extends Thread{

	private Socket client;
	private BufferedReader in;
	private PrintWriter out;

	public ServerHandler(Socket socket){
		client = socket;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public void run()
	{
		try
		{
			String received;
			do
			{
				received = in.readLine();
				System.out.println(received);

//				modification for the assignment
				for (ServerHandler server : MultiEchoServer.getClientHandlers())
				{
					server.out.println(received);
				}
//				ends
			}
			while ((received != null) || !received.equals(": BYE"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		finally{
			try{
				if(client!=null){
					System.out.println("Closing down connection ... ");
					client.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}//end run
}//end ClientHandler class
