package model;

import java.io.File;

public class FileMap {
	protected String id;
	protected File f;
	protected boolean isMapped = false;
	
	public FileMap(File f, String id){
		this.f = f;
		this.id = id;
	}
	
	public File getFile(){
		return f;
	}
	
	public boolean isMapped(){
		return isMapped;
	}
	
	public void setMapped(boolean mapped){
		this.isMapped = mapped;
	}
}
