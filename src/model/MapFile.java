package model;

import java.io.File;
import java.util.ArrayList;

public class MapFile {
	String filename;
	String crc;
	long size;
	int qtdSegments;
		
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	
	public int getQtdSegments() {
		return qtdSegments;
	}
	public void setQtdSegments(int qtdSeg) {
		qtdSegments = qtdSeg;
		
	}

	
	
}
