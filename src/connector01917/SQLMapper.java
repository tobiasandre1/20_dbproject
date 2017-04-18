package connector01917;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SQLMapper {

	public String getStatement(int i, String filename){
		
		Properties props = new Properties();
		try {
			File file = new File(filename);
			FileInputStream in = new FileInputStream(file);
			props.load(in);
			String res = props.getProperty(Integer.toString(i));
			in.close();
			return res; 
		} catch (IOException e) {
			throw new IllegalStateException("Unable to load properties");
		}
	}
	
}
