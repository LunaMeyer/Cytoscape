import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ExcelHashmap{

 public static void main(String args[]){
   HashMap<String, List<String>> myHashMap = new HashMap<String,List<String>>();
	//  System.out.println("test hehe");
  String key = "Accession";

	 try {
	 File file = new File("../ressources/Elysia_Bioscience_Data_Res.xlsx");   //creating a new file instance
	 FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
	 XSSFWorkbook wb = new XSSFWorkbook(fis);
	 XSSFSheet sheet = wb.getSheetAt(0);
   Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
   
   System.out.println("ueue");
   Vector<String> cellArray = new Vector<String>();

   Vector<String> keys = new Vector<String>();
   Row keysRow = itr.next();
   Iterator<Cell> keyCellIterator = keysRow.cellIterator();
   
   while (keyCellIterator.hasNext())  
    {
    Cell keyCell = keyCellIterator.next(); 
    keys.add(String.valueOf(keyCell));
    }
    int keyIndex = keys.indexOf(key);
    System.out.println(keys);
    String keyValue = keys.get(keyIndex);
    keys.remove(keyIndex);

   while (itr.hasNext())                 
    {
    cellArray = new Vector<String>();  
    Row row = itr.next();  
    Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
    while (cellIterator.hasNext())  
      {  
      Cell cell = cellIterator.next(); 
      cellArray.add(String.valueOf(cell));
      }
      cellArray.remove(0);
      String indexCell = cellArray.get(keyIndex);
      cellArray.remove(keyIndex);
      myHashMap.put(indexCell,cellArray);
    }  
      }catch(Exception e)
        {
          e.printStackTrace();
        }

      for (HashMap.Entry<String, List<String>> entry : myHashMap.entrySet()) 
       {
        String hashKey = entry.getKey();
        // System.out.println(hashKey);
        List<String> value = entry.getValue();
        // System.out.println(value);
       }

			}
    }
