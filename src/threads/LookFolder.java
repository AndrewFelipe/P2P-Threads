package threads;

import java.io.File;
import java.util.HashMap;

import model.FileMap;
import model.FileZip;

import jomp.runtime.OMP;

import files.control.FileMapCollection;


public class LookFolder {
	
	public void main(String[] args) {
		FileMapCollection FMC = new FileMapCollection();
		HashMap<String, File> listOfFilesFromFolder = new HashMap<String, File>();
		
		String folder = "/home/andre/Documents";
		File dir = new File(folder);
		int myid;
		
		OMP.setNumThreads(10);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.listOfFilesFromFolder = listOfFilesFromFolder;
  __omp_Object0.FMC = FMC;
  __omp_Object0.dir = dir;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  myid = __omp_Object0.myid;
  listOfFilesFromFolder = __omp_Object0.listOfFilesFromFolder;
  FMC = __omp_Object0.FMC;
  dir = __omp_Object0.dir;
}
// OMP PARALLEL BLOCK ENDS

			
	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  public File dir;
public FileMapCollection FMC;
  public HashMap listOfFilesFromFolder;
// shared variables
  int myid;
  //HashMap listFiles;
  FileMapCollection fmc;
  String folder;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  // reduction variables, init to default
    // OMP USER CODE BEGINS

                          { // OMP SECTIONS BLOCK BEGINS
                          // copy of firstprivate variables, initialized
                          // copy of lastprivate variables
                          // variables to hold result of reduction
                          boolean amLast=false;
                          {
                            // firstprivate variables + init
                            // [last]private variables
                            // reduction variables + init to default
                            // -------------------------------------
                            __ompName_1: while(true) {
                            switch((int)jomp.runtime.OMP.getTicket(__omp_me)) {
                            // OMP SECTION BLOCK 0 BEGINS
                              case 0: {
                            // OMP USER CODE BEGINS
 
			{
				File[] files = this.dir.listFiles();
                                 // OMP CRITICAL BLOCK BEGINS
                                 synchronized (jomp.runtime.OMP.getLockByName("")) {
                                 // OMP USER CODE BEGINS

				{
					for(int i=0; i < files.length; i++){
						this.FMC.setFile(files[i]);
					}
				}
                                 // OMP USER CODE ENDS
                                 }
                                 // OMP CRITICAL BLOCK ENDS

			}
                            // OMP USER CODE ENDS
                              };  break;
                            // OMP SECTION BLOCK 0 ENDS
                            // OMP SECTION BLOCK 1 BEGINS
                              case 1: {
                            // OMP USER CODE BEGINS
 
			{
				//new File(folderPath);
				File[] files = this.dir.listFiles();
				for(int j=0; j<files.length; j++){
					this.listOfFilesFromFolder.put(files[j].getName(), files[j]);
				}
                                 // OMP CRITICAL BLOCK BEGINS
                                 synchronized (jomp.runtime.OMP.getLockByName("")) {
                                 // OMP USER CODE BEGINS

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
                                 // OMP USER CODE ENDS
                                 }
                                 // OMP CRITICAL BLOCK ENDS

			}
                            // OMP USER CODE ENDS
                            amLast = true;
                              };  break;
                            // OMP SECTION BLOCK 1 ENDS

                              default: break __ompName_1;
                            } // of switch
                            } // of while
                            // call reducer
                            jomp.runtime.OMP.resetTicket(__omp_me);
                            jomp.runtime.OMP.doBarrier(__omp_me);
                            // copy lastprivate variables out
                            if (amLast) {
                            }
                          }
                          // update lastprivate variables
                          if (amLast) {
                          }
                          // update reduction variables
                          if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                          }
                          } // OMP SECTIONS BLOCK ENDS

    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

