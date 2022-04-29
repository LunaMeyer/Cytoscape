import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CyjsToJson {
	File file;
	FileWriter test;
	BufferedReader reader;
	
	public CyjsToJson(String path, String path2)throws IOException {
		file = new File(path);
		test = new FileWriter(path2);
		reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
        
        while(line != null) {
            test.write(line+"\n");
            line = reader.readLine();
        }
        reader.close();
        test.close();
	}

}
