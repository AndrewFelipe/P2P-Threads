package model;

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
	public void setQtdSegments(int qtdSegments) {
		this.qtdSegments = qtdSegments;
	}
	
}
