package model;

import java.io.File;
import java.net.Socket;
import java.util.Collection;
import java.util.Random;

public class TorrentClient {
	private Socket socket = null;
	private TorrentServer server = null;
	
	public TorrentClient(TorrentServer srv){
		this.server  = srv;
	}
	
	public Collection<FileMap> getFilesToDownload(){
		if (!this.server.addClient(socket))
			return null;
	
		Collection<FileMap> files = this.server.getAllFilesToDownload();
		
		this.server.removeCliente(socket);
		
		return files;
	}

	public FileMap getRandomFileToDownload(){
		Collection<FileMap> files = this.getFilesToDownload();
		
		Random random = new Random();
		FileMap f = (FileMap) files.toArray()[random.nextInt(files.size())];
		
		return f;
	}
	
	public void downloadFile(String fileName) throws InterruptedException{
		System.out.println("Downloading File "+fileName);
		for (int i = 0; i <= 10; i++){
			System.out.print('=');
			Thread.sleep(60*2);
		}
			
	}
}
