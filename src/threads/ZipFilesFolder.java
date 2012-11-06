package threads;

import java.io.File;
import java.util.HashMap;

import jomp.runtime.OMP;
import model.FileMap;
import model.FileZip;
import model.Zip;
import files.control.FileMapCollection;

public class ZipFilesFolder {
	private static File dir;
	
	private HashMap FMC;
	private String folderPath = "";
	
	public ZipFilesFolder(String folder, HashMap fmc){
		FMC = fmc;
		dir = new File(folder);
	}
	
	public void run() {
	
		int myid;
		
		OMP.setNumThreads(10);
		File[] files = dir.listFiles();
		
		HashMap<String, File> fileToZip = new HashMap<String, File>();
		
		for (File file : files) {
			if(!FMC.containsKey(file.getAbsolutePath())){
				fileToZip.put(file.getAbsolutePath(), file); 
			}
		}
		
		File[] filesToZip = (File[]) fileToZip.values().toArray();
		
		Zip zip = new Zip();
		
		//omp parallel private(myid) shared(filesToZip)
		{
			//omp for
			for(int i=0; i < filesToZip.length; i++){
				File file = filesToZip[i];
				File FileZip =  zip.ZipFile(file.getAbsolutePath(), file.getAbsolutePath()+".zip");
				if(FileZip != null){
					FileZip file_zip = new FileZip(file, file.getAbsolutePath());
					file_zip.setZipped(true);
					FMC.put(file.getAbsoluteFile(), file_zip);
				}
			}
		}
	}
}
