package threads;

import java.io.File;

import model.FileMapCollection;

public class LookFolder extends Thread{
	public static File dir = new File("/home/andre/");
	
	public FileMapCollection FMC;
	
	public LookFolder(FileMapCollection fmc){
		this.FMC = fmc;
	}

	public void run(){
		try {
			while(true){
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
