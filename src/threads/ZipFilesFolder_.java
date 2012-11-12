package threads;

import java.io.File;
import java.util.HashMap;

import jomp.runtime.OMP;
import model.FileMap;
import model.FileZip;
import model.Zip;
import files.control.FileMapCollection;

public class ZipFilesFolder_ {
	/*
	private static File dir;
	
	public HashMap FMC;
	public HashMap fileToZip;
	
	public ZipFilesFolder(String folder, HashMap fmc, HashMap flToZip){
		FMC = fmc;
		dir = new File(folder);
		fileToZip = flToZip;
	}
	*/
	public void run() {
		HashMap<String, FileZip> FMC = new HashMap<String, FileZip>();
		String folder = "/home/andre/Faculdade/";
		File dir = new File(folder);
		HashMap<String, File> fileToZip = new HashMap<String, File>();
	
		int myid;
		
		OMP.setNumThreads(10);
		File[] files = dir.listFiles();
		
		
		for (int i=0; i<files.length;i++) {
			File file = files[i];
			if(!FMC.containsKey(file.getAbsolutePath())){
				fileToZip.put(file.getAbsolutePath(), file); 
			}
		}
		
		File[] filesToZip = (File[]) fileToZip.values().toArray(files);
		
		Zip zip = new Zip();
		
		//omp parallel private(myid) shared(filesToZip, zip)
		{
			//omp for
			for(int i=0; i < filesToZip.length; i++){
				File file = filesToZip[i];
				System.out.println(file.getAbsolutePath());
				File FileZip =  zip.ZipFile(file.getAbsolutePath(), file.getAbsolutePath()+".zip");
				if(FileZip != null){
					FileZip file_zip = new FileZip(file, file.getAbsolutePath());
					file_zip.setZipped(true);
					FMC.put(file.getAbsolutePath(), file_zip);
				}
			}
		}
	}
}
