package pocp2p;
import java.net.*;
import java.io.*;

public class P2pClient implements Runnable {
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private P2pClientThread client = null;

	public P2pClient(String serverName, int serverPort) {
		System.out.println("Estabelecendo conexão. Aguarde....");
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Conectado: " + socket);
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host desconhecido: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Exceção inexperada: " + ioe.getMessage());
		}
	}

	public void run() {
		while (thread != null) {
			try {
				streamOut.writeUTF(console.readLine());
				streamOut.flush();
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
			}
		}
	}

	public void handle(String msg) {
		if (msg.equals(".bye")) {
			System.out.println("Saindo. Aperte ESC para sair ...");
			stop();
		} else
			System.out.println(msg);
	}

	public void start() throws IOException {
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null) {
			client = new P2pClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
		try {
			if (console != null)
				console.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			System.out.println("Erro ao finalizar ...");
		}
		client.close();
		client.stop();
	}

	public static void main(String args[]) {
		P2pClient client = null;
		client = new P2pClient("localhost", 6668);
	}
}