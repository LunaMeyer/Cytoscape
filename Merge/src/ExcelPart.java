import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ExcelPart {
	
	 private HashMap<String, List<String>> myHashMap = new HashMap<String,List<String>>();
	 private Vector<String> cellArray = new Vector<String>(); 
	 private Cell cell; private File file;
	 private String key = "Accession";int keyIndex = 0;
	 boolean check = true;
	 
	 public ExcelPart(String pathtofile) {
		   
		  
		   try {
			 this.file = new File(pathtofile);   
			 FileInputStream fis = new FileInputStream(file); 
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
			 XSSFSheet sheet = wb.getSheetAt(0);
			 Iterator<Row> itr = sheet.iterator(); 	 
			 
			 cellArray = new Vector<String>();

			   Vector<String> keys = new Vector<String>();
			   Row keysRow = itr.next();
			   Iterator<Cell> keyCellIterator = keysRow.cellIterator();
			   
			   while (keyCellIterator.hasNext())  
			    {
			    Cell keyCell = keyCellIterator.next(); 
			    keys.add(String.valueOf(keyCell));
			    }
			    keyIndex = keys.indexOf(key);
			    keys.remove(keyIndex);
			
			   while (itr.hasNext()) {
			    cellArray = new Vector<String>();  
			    Row row = itr.next();  
			    
			    for (int i=1;i<=keys.size()+1;i++) {  
			      cell = row.getCell(i,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL); 
			      cellArray.add(String.valueOf(cell));
			      }
			      String indexCell = cellArray.get(keyIndex);
			      cellArray.remove(keyIndex);
			      myHashMap.put(indexCell,cellArray);
			    }  
			 
			 	wb.close();
			 }
		  
			 catch(Exception e) {
		          e.printStackTrace();
		     }
			 
	}
	 public List<String> getKeys(){
		 List<String> keys = new ArrayList<String>();
		 for (HashMap.Entry<String, List<String>> entry : myHashMap.entrySet()) {
			 keys.add(entry.getKey());
		 }
		 return keys;
	 }
	 
	 public HashMap<String, List<String>> getData(){
		 return myHashMap;
	 }
}