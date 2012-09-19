package model;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.concurrent.Semaphore;

import pocChat.ChatServerThread;

public class TorrentServer {
	private static Semaphore clients;
	private FileMapCollection FMC = null;
	
	public TorrentServer(Semaphore clts, FileMapCollection fmc){
		this.clients = clts;
		this.FMC = fmc;
	}
	
	public boolean addClient(Socket socket) {
		while(!clients.tryAcquire(1)){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public void removeCliente(Socket socket){
		clients.release(1);
	}

	public Collection<FileMap> getAllFilesToDownload() {
		return this.FMC.getAllFiles();
	}
	
	
}
