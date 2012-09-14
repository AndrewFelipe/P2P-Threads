package model;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FileMapCollection {
	
	protected HashMap<String, FileMap> listOfFilesMap = new HashMap<String, FileMap>(); 
	
	private Lock lock = new ReentrantLock();
	private Condition hasFile = lock.newCondition(); 
	
	public void setFile(File f){
		
		lock.lock();
		try{
			//int id = this.listOfFilesMap.size();
			String id = f.getName();
			if(this.listOfFilesMap.get(id) != null) // Se já existe, nao insere
				return;
			
			FileMap fm = new FileMap(f, id);
			
			this.listOfFilesMap.put(id, fm);
			hasFile.signalAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	public FileMap getFileToMap(){
		Collection<FileMap> listOfFiles = null;
		FileMap fileToMap = null;
		
		while(fileToMap == null){ // Se n tiver arquivo, aguarda no lock ate inserir outro
			lock.lock();
			listOfFiles = listOfFilesMap.values();
			lock.unlock();
				
			for (FileMap fm : listOfFiles) {
				if(fm.isMapped() == false){
					fm.setMapped(true);
					fileToMap = fm;
					break;
				}
			}
			
			if (fileToMap==null){
				try {
					hasFile.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			
		return fileToMap;
	}

	public Collection<FileMap> getAllFiles(){
		lock.lock();
		
		try{
			return this.listOfFilesMap.values();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}

		return null;
	}
}
