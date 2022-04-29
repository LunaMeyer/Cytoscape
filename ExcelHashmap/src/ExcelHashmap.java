import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ExcelHashmap{

 public static void main(String args[]){
   HashMap<Object, Object> myHashMap = new HashMap<Object,Object>();
	 System.out.println("test hehe");

	 try {
	 File file = new File("../ressources/Elysia_Bioscience_Data_Res.xlsx");   //creating a new file instance
	 FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
	 XSSFWorkbook wb = new XSSFWorkbook(fis);
	 XSSFSheet sheet = wb.getSheetAt(0);
   Iterator<Row> itr = sheet.iterator();    //iterating over excel file  
   System.out.println("ueue");
   Vector<Object> cellArray = new Vector<Object>();
   while (itr.hasNext())                 
   {
   cellArray = new Vector<Object>();  
   Row row = itr.next();  
   Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
   while (cellIterator.hasNext())  
   {  
   Cell cell = cellIterator.next(); 
  //  System.out.println(cell);
  //  System.out.println(cell.getCellType()); 

   switch (cell.getCellType())               
   {  
   case STRING:    //field that represents string cell type  
    cellArray.add(cell);
   break;  
   case NUMERIC:    //field that represents number cell type  
    cellArray.add(cell);
   break;  
   default:  
   }  
  }
  // System.out.println(cellArray);
  myHashMap.put(cellArray.get(0),cellArray.subList(1,cellArray.size()-1));
    }  
      }catch(Exception e)
      {
        e.printStackTrace();
      }
      for (HashMap.Entry<Object, Object> entry : myHashMap.entrySet()) {
        Object key = entry.getKey();
        System.out.println(key);
        Object value = entry.getValue();
        System.out.println(value);
    }

			}
    }
