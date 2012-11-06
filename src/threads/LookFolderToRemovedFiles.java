package threads;

import java.io.File;
import java.util.HashMap;

import files.control.FileMapCollection;

import model.FileMap;

public class LookFolderToRemovedFiles extends Thread{
	
	private FileMapCollection FMC;
	private String folderPath = "";
	private File dir;
	
	public LookFolderToRemovedFiles(String folder, FileMapCollection fmc){
		this.FMC = fmc;
		this.dir = new File(folder);
	}

	public void run(){
		try {
			while(true){
				new File(this.folderPath);
				HashMap<String, File> listOfFilesFromFolder = new HashMap<String, File>(); 
				
				File[] files = dir.listFiles();
				for(File f : files){
					listOfFilesFromFolder.put(f.getName(), f);
				}
				
				for (FileMap file : this.FMC.getAllFiles()){
					if(!listOfFilesFromFolder.containsKey(file.getFile().getName())){
						// Gravar em algum lugar para remover os arquivos...
						System.out.println("file to Remove: "+file.getFile().getName());
					}
				}
				
				sleep(60*30);
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
