import java.io.File;
import java.util.HashMap;

import model.FileZip;

import files.control.FileMapCollection;
import threads.LookFolder;
import threads.LookFolderToRemovedFiles;
import threads.ZipFilesFolder;
import threads.ZipFilesFolder_;


public class Executer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		
		FileMapCollection fmc = new FileMapCollection();
		HashMap<String, File> listOfFilesFromFolder = new HashMap<String, File>();
		HashMap<String, FileZip> fzc = new HashMap<String, FileZip>();
		
		String $folder = "/home/andre/Documents";
		
		try {
			while(true){
				LookFolder lf = new LookFolder();
				lf.main(args);
				//lf.run();
				
				ZipFilesFolder zf = new ZipFilesFolder();
				zf.main(args);
				//zf.run();
				
				Thread.sleep(250);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//LookFolderToRemovedFiles lfR = new LookFolderToRemovedFiles($folder, fmc);
		//lfR.start();
		 

	}

}
