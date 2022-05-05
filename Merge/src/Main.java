import java.util.*;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) {
		ExcelPart result = new ExcelPart("C:\\Users\\louis\\Desktop\\Network\\Elysia_Bioscience_Data_Res.xlsx");
		List<String> keys = result.getKeys();	
		JsonPart parser = new JsonPart("C:\\Users\\louis\\Desktop\\Network\\Homo-spaiens.cyjs",keys);
		List<String> id = parser.getId();
		JsonPart2 parser2 = new JsonPart2("C:\\Users\\louis\\Desktop\\Network\\Homo-spaiens.cyjs",id);
		Map<String, String> nodes = parser.getNodes(); 
		Map<String, List<String>> data = result.getData();
		Map<String, List<String>> merge = mergeMap(data,nodes);
		System.out.println("Finito");
		for(Entry<String, List<String>> entry : merge.entrySet()) {
			String size = String.valueOf(entry.getValue().size());
			System.out.println(entry.getKey()+" : "+size +" "+entry.getValue());
		}
	}
	
	//Methode pour merger la map des résultats et la map des nodes
	public static Map<String, List<String>>  mergeMap(Map<String,List<String>> map1, Map<String, String> map2){
		Map<String, List<String>> mapmerge = new HashMap<String,List<String>>();
		List<String> templist = new ArrayList<String>();
		String tempkey = new String();
		
		for(Entry<String, String> entry : map2.entrySet()) {
			
			tempkey = entry.getKey();
			templist = map1.get(tempkey);
			templist.add(entry.getValue());
			mapmerge.put(tempkey, templist);
		}
		
		return mapmerge;
	}
}
