package threads;

import java.io.File;
import java.util.HashMap;

import jomp.runtime.OMP;
import model.FileMap;
import model.FileZip;
import model.Zip;
import files.control.FileMapCollection;

public class ZipFilesFolder {

	public void main(String args[]) {
		HashMap<String, FileZip> FMC = new HashMap<String, FileZip>();
		String folder = "/home/andre/Documents";
		File dir = new File(folder);
		HashMap<String, File> fileToZip = new HashMap<String, File>();
	
		int myid;
		
		File[] files = dir.listFiles();
		
		for (int i=0; i<files.length;i++) {
			File file = files[i];
			if(!FMC.containsKey(file.getAbsolutePath())){
				fileToZip.put(file.getAbsolutePath(), file); 
			}
		}
		
		File[] filesToZip = filesToZip = (File[]) fileToZip.values().toArray(files);
		
		Zip zip = new Zip();
		OMP.setNumThreads(10);

// OMP PARALLEL BLOCK BEGINS
{
  __omp_Class0 __omp_Object0 = new __omp_Class0();
  // shared variables
  __omp_Object0.filesToZip = filesToZip;
  __omp_Object0.zip = zip;
  __omp_Object0.files = files;
  __omp_Object0.dir = dir;
  __omp_Object0.FMC = FMC;
  // firstprivate variables
  try {
    jomp.runtime.OMP.doParallel(__omp_Object0);
  } catch(Throwable __omp_exception) {
    System.err.println("OMP Warning: Illegal thread exception ignored!");
    System.err.println(__omp_exception);
  }
  // reduction variables
  // shared variables
  filesToZip = __omp_Object0.filesToZip;
  zip = __omp_Object0.zip;
  files = __omp_Object0.files;
  fileToZip = __omp_Object0.flToZip;
  FMC = __omp_Object0.FMC;
  dir = __omp_Object0.dir;
}
// OMP PARALLEL BLOCK ENDS

	}

// OMP PARALLEL REGION INNER CLASS DEFINITION BEGINS
private class __omp_Class0 extends jomp.runtime.BusyTask {
  public HashMap FMC;
public File dir;
// shared variables
  File[] filesToZip;
  Zip zip;
  File [ ] files;
  HashMap flToZip;
  HashMap fmc;
  String folder;
  // firstprivate variables
  // variables to hold results of reduction

  public void go(int __omp_me) throws Throwable {
  // firstprivate variables + init
  // private variables
  int myid;
  // reduction variables, init to default
    // OMP USER CODE BEGINS

		{
                         { // OMP FOR BLOCK BEGINS
                         // copy of firstprivate variables, initialized
                         // copy of lastprivate variables
                         // variables to hold result of reduction
                         boolean amLast=false;
                         {
                           // firstprivate variables + init
                           // [last]private variables
                           // reduction variables + init to default
                           // -------------------------------------
                           jomp.runtime.LoopData __omp_WholeData2 = new jomp.runtime.LoopData();
                           jomp.runtime.LoopData __omp_ChunkData1 = new jomp.runtime.LoopData();
                           __omp_WholeData2.start = (long)(0);
                           __omp_WholeData2.stop = (long)( filesToZip.length);
                           __omp_WholeData2.step = (long)(1);
                           jomp.runtime.OMP.setChunkStatic(__omp_WholeData2);
                           while(!__omp_ChunkData1.isLast && jomp.runtime.OMP.getLoopStatic(__omp_me, __omp_WholeData2, __omp_ChunkData1)) {
                           for(;;) {
                             if(__omp_WholeData2.step > 0) {
                                if(__omp_ChunkData1.stop > __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start >= __omp_WholeData2.stop) break;
                             } else {
                                if(__omp_ChunkData1.stop < __omp_WholeData2.stop) __omp_ChunkData1.stop = __omp_WholeData2.stop;
                                if(__omp_ChunkData1.start > __omp_WholeData2.stop) break;
                             }
                             for(int i = (int)__omp_ChunkData1.start; i < __omp_ChunkData1.stop; i += __omp_ChunkData1.step) {
                               // OMP USER CODE BEGINS
{
				File file = filesToZip[i];
				System.out.println("File Zipping: "+file.getAbsolutePath());
				File FileZip =  zip.ZipFile(file.getAbsolutePath(), file.getAbsolutePath()+".zip");
				if(FileZip != null){
					FileZip file_zip = new FileZip(file, file.getAbsolutePath());
					file_zip.setZipped(true);
					FMC.put(file.getAbsoluteFile(), file_zip);
				}
			}
                               // OMP USER CODE ENDS
                               if (i == (__omp_WholeData2.stop-1)) amLast = true;
                             } // of for 
                             if(__omp_ChunkData1.startStep == 0)
                               break;
                             __omp_ChunkData1.start += __omp_ChunkData1.startStep;
                             __omp_ChunkData1.stop += __omp_ChunkData1.startStep;
                           } // of for(;;)
                           } // of while
                           // call reducer
                           jomp.runtime.OMP.doBarrier(__omp_me);
                           // copy lastprivate variables out
                           if (amLast) {
                           }
                         }
                         // set global from lastprivate variables
                         if (amLast) {
                         }
                         // set global from reduction variables
                         if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
                         }
                         } // OMP FOR BLOCK ENDS

		}
    // OMP USER CODE ENDS
  // call reducer
  // output to _rd_ copy
  if (jomp.runtime.OMP.getThreadNum(__omp_me) == 0) {
  }
  }
}
// OMP PARALLEL REGION INNER CLASS DEFINITION ENDS

}

