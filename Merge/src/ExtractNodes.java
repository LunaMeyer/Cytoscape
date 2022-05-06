
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class ExtractNodes {
	private JsonFactory factory = new JsonFactory();
	private File src;
	private JsonParser parser;
	String tokenValue;
	private  Map<String, String> nodes = new HashMap<String,String>();
	private int ii = 0;
	
	public ExtractNodes(String pathtofile, List<String> keys){
		this.src = new File(pathtofile);
		try {
			parser = factory.createJsonParser(src);
			parser.nextToken();
			
			while (parser.nextToken()!=null) {
				JsonToken token = parser.getCurrentToken();
				
				if (token.equals(JsonToken.START_OBJECT)) {
					tokenValue= parser.getCurrentName();
					if ("data".equalsIgnoreCase(tokenValue)) {
						inData(parser, keys);
					}
				}
				if(token.equals(JsonToken.END_ARRAY)) {
					break;
				}
					
			}
			
			System.out.println(" trouvé : " + ii + " canonical names");
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void inData(JsonParser parser, List<String> keys) {
		JsonToken token2;
		String tokenValue = null, tokenName, key = null;

		
		try {
			while (parser.nextToken()!=null) {
				token2 = parser.getCurrentToken();
				if (token2.equals(JsonToken.END_OBJECT)) {
					if(key == null) {
					return;}
					else {
						nodes.put(key, tokenValue);
						return;}
				} 
				else {
					
					tokenName= parser.getCurrentName();
					if ("stringdb_canonical_name".equals(tokenName)) {
						parser.nextToken();
						if(keys.contains(parser.getText())) {
						key = parser.getText();
						ii++;}
					}
					if("id".equals(tokenName)) {
						parser.nextToken();
						tokenValue=parser.getText();
						
					}
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<String> getId(){
		List<String> idlist = new Vector<String>();
		for(Entry<String, String> entry : nodes.entrySet()) {
			idlist.add(entry.getValue());
		}
		return idlist;
	}
	
	public Map<String, String> getNodes(){
		return nodes;
	}
	
}
