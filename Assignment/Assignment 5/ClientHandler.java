/**
 * Ahmed Rajgoli Shoaib Shakeel
 * B00878695
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    private BufferedReader input;

    public ClientHandler(Socket socket) throws IOException{
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run()
    {
        try
        {
            String reply;
            do
            {
                reply = input.readLine();

                if (!reply.contains("Message from " + MultiEchoClient.getName() + ":")
                        && !reply.contains(MultiEchoClient.getName() + " has joined"))
                {
                    System.out.println(reply);

                    if (reply.contains("Message from ") || reply.contains("has joined"))
                    {
                        System.out.println("Enter message (BYE to exit):");
                    }
                }
            }
            while (!reply.contains(": BYE"));

            System.out.println("Closing down connection ...");
        }
        catch (IOException e)
        {
            System.exit(1);
        }

        catch (UnknownError e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                input.close();
            }
            catch (Exception e)
            {
                System.exit(1);;
            }
        }
    }

}

