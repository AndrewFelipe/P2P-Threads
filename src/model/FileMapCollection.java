package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import files.control.MapsGen;

public class FileMapCollection {
	
	protected HashMap<String, FileMap> listOfFilesMap = new HashMap<String, FileMap>(); 
	protected HashMap<String, FileMap> listOfFilesToRemove = new HashMap<String, FileMap>(); 
	
	private Lock lock = new ReentrantLock();
	private Condition hasFile = lock.newCondition(); 
	private Condition hasFileToRemove = lock.newCondition();
	
	public void setFile(File f){
		
		MapsGen mg = new MapsGen();
		lock.lock();
		try{
			String id = f.getName();
			if(this.listOfFilesMap.get(id) != null) // Se já existe, nao insere
				return;
			
			System.out.println(f.getName());
			
			FileMap fm = new FileMap(f, id);			
			fm.setMap(mg.getMapFileFromFile(f));
			
			this.listOfFilesMap.put(id, fm);
			hasFile.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	/**
	 * @return FileMap wasn't mapped
	 */
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
			
			while (fileToMap==null){
				try {
					hasFile.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
			
		return fileToMap;
	}
	
	/**
	 * @return Collection<FileMap> wasn't mapped
	 */
	public Collection<FileMap> getAllFileToMap(){
		Collection<FileMap> listOfFiles = null;
		ArrayList<FileMap> filesToMap = new ArrayList<FileMap>();
		
		while(filesToMap.size() == 0){ // Se n tiver arquivo, aguarda no lock ate inserir outro
			try {
				hasFile.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		lock.lock();
		listOfFiles = listOfFilesMap.values();
		lock.unlock();
			
		for (FileMap fm : listOfFiles) {
			if(fm.isMapped() == false){
				fm.setMapped(true);
				filesToMap.add(fm);
			}
		}
		
		return filesToMap;
	}
	
	public Collection<FileMap> getAllFiles(){
		lock.lock();
		
		Collection<FileMap> allFiles = null;
		
		try{
			allFiles = this.listOfFilesMap.values();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}

		return allFiles;
	}
	
	public void setFileToRemove(String $id){
		lock.lock();
		
		try{
			this.listOfFilesToRemove.put($id, listOfFilesMap.get($id));
			this.listOfFilesMap.remove($id);
			
			System.out.println("File to remove: " + $id);
			
			hasFileToRemove.signalAll();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}
	}
	
	/*
	 * Get all Files to Remove
	 */
	public Collection<FileMap> getFilesToRemove(){
		Collection<FileMap> allFilesToRemove = null;
		lock.lock();
		
		try{
			while(listOfFilesToRemove.size() == 0){
				lock.unlock(); // solta o lock...
				hasFileToRemove.await(); // Aguarda
				lock.lock(); // Se saiu do await ja faz o lock de novo
			}
			
			allFilesToRemove = this.listOfFilesMap.values();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			lock.unlock();
		}		
		
		return allFilesToRemove;
	}
}
