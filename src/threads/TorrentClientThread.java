package threads;

import model.TorrentClient;

public class TorrentClientThread extends Thread{
	
	TorrentClient client = null;
	
	public TorrentClientThread(TorrentClient clt){
		this.client = clt;
	}
	
	public void run(){
		try {
			while(true){
				String fileName = this.client.getRandomFileToDownload().getFile().getName();
				System.out.println("Selecionado download: " + fileName);
				this.client.downloadFile(fileName);
				sleep(60*5);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
