import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;



public class Main {
	static String json = "";
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		File file = new File(".\\ressources\\SARS-CoV.cyjs");//OUverture
		FileWriter test = new FileWriter(".\\ressources\\test.json");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line = reader.readLine();
        
        
        while(line != null) {
            json += line;
            test.write(line);
            line = reader.readLine();
            if(line != null) {
            	json+="\n";
            	test.write("\n");
            }
            
        }
        reader.close();
        test.close();

		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> value = mapper.readValue(json, HashMap.class);
		Map<String,Map<String,Object>> element = (Map<String, Map<String, Object>>) value.get("elements");
	}

}
