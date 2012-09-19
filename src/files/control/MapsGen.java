package files.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import model.MapFile;

public class MapsGen {
	Properties properties = new Properties();
	
	public MapFile getMapFileFromFile(File file){
		//properties.load(new FileInputStream("model/p2pthreads.properties"));
		MapFile m = new MapFile();
		m.setFilename(file.getName());
		//int qtdSeg = Integer.valueOf(properties.getProperty("qtdSegment"));
		int qtdSeg = 5;
		m.setQtdSegments(qtdSeg);
		m.setSize(file.length());		
		return m;
	}
}
