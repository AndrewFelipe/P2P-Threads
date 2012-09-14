package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class Zip {

	private static final int BUFFER = 2048;

	/*
	 * fileToZip: path and name of file to zip
	 * nameFileZip: path and name of the file that will be zipped
	 */
	public void ZipFile(String fileToZip, String nameFileZip){
		
		try {
	         BufferedInputStream origin = null;
	         FileOutputStream dest = new 
	         FileOutputStream(fileToZip);
	         ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));

	         byte data[] = new byte[BUFFER];
	         // get a list of files from current directory
	         File f = new File(nameFileZip);
	         FileInputStream fi = new FileInputStream(f);
	         origin = new BufferedInputStream(fi, BUFFER);
	         ZipEntry entry = new ZipEntry(fileToZip);
	         out.putNextEntry(entry);
	         int count;
	         while((count = origin.read(data, 0, BUFFER)) != -1) {
	            out.write(data, 0, count);
	         }
	         origin.close();
	         
	         out.close();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
	
	/*
	 * Implementar esse metodo
	public void UnZipFile(){
		try {
	         final int BUFFER = 2048;
	         BufferedOutputStream dest = null;
	         FileInputStream fis = new 
		   FileInputStream(argv[0]);
	         CheckedInputStream checksum = new 
	           CheckedInputStream(fis, new Adler32());
	         ZipInputStream zis = new 
	           ZipInputStream(new 
	             BufferedInputStream(checksum));
	         ZipEntry entry;
	         while((entry = zis.getNextEntry()) != null) {
	            System.out.println("Extracting: " +entry);
	            int count;
	            byte data[] = new byte[BUFFER];
	            // write the files to the disk
	            FileOutputStream fos = new 
	              FileOutputStream(entry.getName());
	            dest = new BufferedOutputStream(fos, 
	              BUFFER);
	            while ((count = zis.read(data, 0, 
	              BUFFER)) != -1) {
	               dest.write(data, 0, count);
	            }
	            dest.flush();
	            dest.close();
	         }
	         zis.close();
	         System.out.println("Checksum: 
	           "+checksum.getChecksum().getValue());
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	}
	
	*/
}
