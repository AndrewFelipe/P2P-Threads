package threads;

import java.io.File;
import java.util.HashMap;

import model.FileMap;

import jomp.runtime.OMP;

import files.control.FileMapCollection;


public class LookFolder{
	private static File dir;
	
	private FileMapCollection FMC;
	private String folderPath = "";
	
	public LookFolder(String folder, FileMapCollection fmc){
		FMC = fmc;
		dir = new File(folder);
	}
	
	public void run() {
		
		int myid;
		
		OMP.setNumThreads(10);
		
		//omp parallel sections
		{
			//omp section 
			{
				File[] files = dir.listFiles();
				
				//omp critical
				{
					for(File f : files){
						FMC.setFile(f);
					}
				}
			}
			
			//omp section 
			{
				new File(folderPath);
				HashMap<String, File> listOfFilesFromFolder = new HashMap<String, File>(); 
				
				File[] files = dir.listFiles();
				for(File f : files){
					listOfFilesFromFolder.put(f.getName(), f);
				}
				
				//omp critical
				{
					for (FileMap file : FMC.getAllFiles()){
						if(!listOfFilesFromFolder.containsKey(file.getFile().getName())){
							//listOfFilesFromFolder.put(file.getFile().getName(), file.getFile());
							System.out.println("file to Remove: "+file.getFile().getName());
						}
					}
				}
			}
		}
			
	}
	
}
