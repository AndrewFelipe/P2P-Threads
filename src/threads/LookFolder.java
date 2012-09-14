package threads;

import java.io.File;

import model.FileMapCollection;

public class LookFolder extends Thread{
	private File dir;
	
	private FileMapCollection FMC;
	private String folderPath = "";
	
	public LookFolder(String folder, FileMapCollection fmc){
		this.FMC = fmc;
		this.dir = new File(folder);
	}

	public void run(){
		try {
			while(true){
				new File(this.folderPath);
				
				File[] files = dir.listFiles();
				for(File f : files){
					FMC.setFile(f);
				}
				sleep(60*5);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
