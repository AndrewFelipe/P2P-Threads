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
		try {
			properties.load(new FileInputStream("p2pthreads.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MapFile m = new MapFile();
		m.setFilename(file.getName());
		int qtdSeg = Integer.valueOf(properties.getProperty("qtdSegment"));
		m.setQtdSegments(qtdSeg);
		m.setSize(file.length());		
		return m;
	}
}
