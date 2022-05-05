import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
public class JsonPart2 {
	private JsonFactory factory = new JsonFactory();
	private File src;
	private JsonParser parser;
	String tokenValue = null;
	private  Map<String, List<String>> edges = new HashMap<String,List<String>>();
	private int i=0;
	
	//Classe pour créer la hashmap des edges
	public JsonPart2(String pathtofile, List<String> id){
		this.src = new File(pathtofile);
		try {
			parser = factory.createJsonParser(src);
			parser.nextToken();
			
			while (parser.nextToken()!=null) {
				JsonToken token = parser.getCurrentToken();
				if (token.equals(JsonToken.START_ARRAY)){
					tokenValue= parser.getCurrentName();
					if ("edges".equalsIgnoreCase(tokenValue)) {
						System.out.println("Fonctionne");
						step1(parser,id);
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Edges ajoutés : "+String.valueOf(i));
	}
	
	private void step1(JsonParser parser, List<String> id) {
		String tokenValue = null;
		try {
			while (parser.nextToken()!=null) {
				tokenValue = parser.getCurrentName();
				if ("data".equalsIgnoreCase(tokenValue)) {
					inData(parser, id);
				}
			}
		}
		catch (IOException e) {
				e.printStackTrace();
			}
	}
		
	private void inData(JsonParser parser, List<String> id) {
		JsonToken token3;
		String tokenValue = null, key = null;
		List<String> temp = new ArrayList<String>();
		int ii = 0;

		
		try {
			while (parser.nextToken()!=null) {
				token3 = parser.getCurrentToken();
				if (token3.equals(JsonToken.END_OBJECT)) {
					if(ii == 0) {
					return;}
					else {
						edges.put(key, temp);
						ii=0;
						i++;
						return;}
				} 
				else if("id".equals(parser.getCurrentName())){
					parser.nextToken();
					key = parser.getText();}
				else if("source".equals(parser.getCurrentName())) {
						parser.nextToken();
						tokenValue=parser.getText();
						temp.add(tokenValue);
						if(id.contains(tokenValue)){ii++;}
						}
				else if("target".equals(parser.getCurrentName())) {
						parser.nextToken();
						tokenValue=parser.getText();
						temp.add(tokenValue);
						}
				else if("stringdb_score".equals(parser.getCurrentName())) {
					parser.nextToken();
					tokenValue=parser.getText();
					temp.add(tokenValue);
					}
				}
			}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<String,List<String>> getEdges(){
		return edges;
	}
}
