package pocp2p;

import java.net.*;
import java.io.*;

import threads.LookFolder;
import threads.LookFolderToRemovedFiles;
import files.control.FileMapCollection;

public class P2pServer implements Runnable {
	private P2pServerThread clients[] = new P2pServerThread[50];
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;

	public P2pServer(int port) {
		try {
			System.out.println("Procurando porta " + port + ", aguarde  ...");
			server = new ServerSocket(port);
			System.out.println("Server iniciado: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("N�o foi poss�vel alocar uma porta " + port
					+ ": " + ioe.getMessage());
		}
	}

	public void run() {
		while (thread != null) {
			try {
				System.out.println("Aguardando um client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Erro do servidor: " + ioe);
				stop();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	private int findClient(int ID) {
		for (int i = 0; i < clientCount; i++)
			if (clients[i].getID() == ID)
				return i;
		return -1;
	}

	public synchronized void handle(int ID, String input) {
		if (input.equals(".bye")) {
			clients[findClient(ID)].send(".bye");
			remove(ID);
		} else
			for (int i = 0; i < clientCount; i++)
				clients[i].send(ID + ": " + input);
	}

	public synchronized void remove(int ID) {
		int pos = findClient(ID);
		if (pos >= 0) {
			P2pServerThread toTerminate = clients[pos];
			System.out.println("Removendo client thread " + ID + " at " + pos);
			if (pos < clientCount - 1)
				for (int i = pos + 1; i < clientCount; i++)
					clients[i - 1] = clients[i];
			clientCount--;
			try {
				toTerminate.close();
			} catch (IOException ioe) {
				System.out.println("Erro ao finializar thread: " + ioe);
			}
			toTerminate.stop();
		}
	}

	private void addThread(Socket socket) {
		if (clientCount < clients.length) {
			System.out.println("Cliente aceito: " + socket);
			clients[clientCount] = new P2pServerThread(this, socket);
			try {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
			} catch (IOException ioe) {
				System.out.println("Erro inicializando thread: " + ioe);
			}
		} else
			System.out.println("Cliente recusado: m�ximo " + clients.length
					+ " ultrapassado.");
	}

	public static void main(String args[]) {
		P2pServer server = null;
		server = new P2pServer(6668);

		FileMapCollection fmc = new FileMapCollection();

		String $folder = "c:\\compartilhar";

		// 4 Threads para verificar a as pastas
		for (int i = 0; i < 4; i++) {
			LookFolder lf = new LookFolder($folder, fmc);
			lf.run();
		}

		LookFolderToRemovedFiles lfR = new LookFolderToRemovedFiles($folder,fmc);
		lfR.start();
	}
}