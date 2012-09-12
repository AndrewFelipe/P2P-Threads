package files.control;

import java.io.File;

import model.MapFile;

public class MapsGen {
	private static final int QTDSEG = 5;
	
	public MapFile getMapFileFromFile(File file){
		MapFile m = new MapFile();
		m.setFilename(file.getName());
		m.setQtdSegments(QTDSEG);
		return m;
	}
}
