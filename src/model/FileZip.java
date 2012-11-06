package model;

import java.io.File;

public class FileZip {

	protected String id;
	protected File f;
	protected boolean isZipped = false;
	MapFile map;
	
	public FileZip(File f, String id){
		this.f = f;
		this.id = id;
	}
	
	public File getFile(){
		return f;
	}
	
	public boolean isZipped(){
		return isZipped;
	}
	
	public void setZipped(boolean zipped){
		this.isZipped = zipped;
	}

	public MapFile getMap() {
		return map;
	}

	public void setMap(MapFile map) {
		this.map = map;
	}
	
}
