/**
 * Ahmed Rajgoli Shoaib Shakeel
 * B00878695
 */
import java.io.*;
import java.net.*;
public class MultiEchoClient {
	private static final int PORT = 1234;
	private static Socket link;
	private static BufferedReader in;
	private static PrintWriter out;
	private static BufferedReader kbd;
	private static String name;

	public static String getName() {
		return name;
	}

	public static void main(String[] args) throws IOException {

		try
		{
			link = new Socket("127.0.0.1", PORT);
			in = new BufferedReader(new InputStreamReader(link.getInputStream()));
			out = new PrintWriter(link.getOutputStream(), true);
			kbd = new BufferedReader(new InputStreamReader(System.in));
			String message;
			boolean flag = true;

			do
			{
//				modifications for the assignment
				message = "";

				if (flag)
				{
					System.out.println("Enter name:");
					name = kbd.readLine();
					out.println(name + " has joined");
					ClientHandler handler = new ClientHandler(link);
					handler.start();
					flag = false;
				}
				else
				{
					System.out.println("Enter message (BYE to exit):");
					message = kbd.readLine();
					out.println("Message from " + name + ": " + message);
				}
			}
			while (!message.equals("BYE"));
		}

		catch(UnknownHostException e)
		{
			System.exit(1);
		}

		catch (IOException e)
		{
			System.exit(1);
		}
//		ends

		finally
		{
			try
			{
				if (link != null)
				{
					System.out.println("Closing down connection ...");
					link.close();
				}
			}
			catch (IOException e)
			{
				System.exit(1);
			}
		}

	}//end main
}// end class MultiEchoClient
	
	
