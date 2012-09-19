import files.control.FileMapCollection;
import threads.LookFolder;
import threads.LookFolderToRemovedFiles;


public class Executer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileMapCollection fmc = new FileMapCollection();
		
		String $folder = "c:\\compartilhar";
		
		// 4 Threads para verificar a as pastas
		for(int i = 0; i < 4; i++){
			LookFolder lf = new LookFolder($folder, fmc);
			lf.start();
		}
		
		LookFolderToRemovedFiles lfR = new LookFolderToRemovedFiles($folder, fmc);
		lfR.start();
	}

}
