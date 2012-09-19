package pocp2p;
import java.net.*;
import java.io.*;

public class P2pClientThread extends Thread
{  private Socket           socket   = null;
   private P2pClient       client   = null;
   private DataInputStream  streamIn = null;

   public P2pClientThread(P2pClient _client, Socket _socket)
   {  client   = _client;
      socket   = _socket;
      open();  
      start();
   }
   public void open()
   {  try
      {  streamIn  = new DataInputStream(socket.getInputStream());
      }
      catch(IOException ioe)
      {  System.out.println("Erro enviando dados: " + ioe);
         client.stop();
      }
   }
   public void close()
   {  try
      {  if (streamIn != null) streamIn.close();
      }
      catch(IOException ioe)
      {  System.out.println("Erro recebendo dados: " + ioe);
      }
   }
   public void run()
   {  while (true)
      {  try
         {  client.handle(streamIn.readUTF());
         }
         catch(IOException ioe)
         {  System.out.println("Erro no listener: " + ioe.getMessage());
            client.stop();
         }
      }
   }
}

