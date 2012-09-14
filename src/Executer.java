import model.FileMapCollection;
import threads.LookFolder;


public class Executer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileMapCollection fmc = new FileMapCollection();
		
		// 4 Threads para verificar a as pastas
		for(int i = 0; i < 4; i++){
			LookFolder lf = new LookFolder(fmc);
			lf.start();
		}
	}

}
