package threads;

import java.io.File;
import java.util.HashMap;

import model.FileMap;

import jomp.runtime.OMP;

import files.control.FileMapCollection;


public class LookFolder_{
	private static File dir;
	
	private FileMapCollection FMC;
	private String folderPath = "";
	private HashMap listOfFilesFromFolder;
	
	public LookFolder_(String folder, FileMapCollection fmc, HashMap listFiles){
		this.FMC = fmc;
		this.dir = new File(folder);
		this.listOfFilesFromFolder = listFiles;
	}
	
	public void run() {
		
		int myid;
		
		OMP.setNumThreads(10);
		 
		
		//omp parallel sections shared(listOfFilesFromFolder)
		{
			//omp section 
			{
				File[] files = this.dir.listFiles();
				
				//omp critical
				{
					for(int i=0; i < files.length; i++){
						this.FMC.setFile(files[i]);
					}
				}
			}
			
			//omp section 
			{
				//new File(folderPath);
				File[] files = this.dir.listFiles();
				for(int j=0; j<files.length; j++){
					this.listOfFilesFromFolder.put(files[j].getName(), files[j]);
				}
				
				//omp critical
				{
					FileMap[] allFiles =  (FileMap[]) this.FMC.getAllFiles().toArray();
					for (int h=0; h<allFiles.length; h++){
						FileMap file = allFiles[h];
						if(!this.listOfFilesFromFolder.containsKey(file.getFile().getName())){
							//listOfFilesFromFolder.put(file.getFile().getName(), file.getFile());
							System.out.println("file to Remove: "+file.getFile().getName());
						}
					}
				}
			}
		}
			
	}
	
}
