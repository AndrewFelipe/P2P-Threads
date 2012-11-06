import java.util.HashMap;

import model.FileZip;

import files.control.FileMapCollection;
import threads.LookFolder;
import threads.LookFolderToRemovedFiles;
import threads.ZipFilesFolder;


public class Executer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileMapCollection fmc = new FileMapCollection();
		HashMap<String, FileZip> fzc = new HashMap<String, FileZip>();
		
		String $folder = "/home/andre/Downloads";
		
		try {
			while(true){
				LookFolder lf = new LookFolder($folder, fmc);
				lf.run();
				
				ZipFilesFolder zf = new ZipFilesFolder($folder, fzc);
				zf.run();
				
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
