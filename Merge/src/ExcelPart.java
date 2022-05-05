//Import nécessaire pour faire fonctionner la lecture du xls
import java.util.*;
import java.io.*;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ExcelPart {
	
	//Cette Map contient les résultats de l'expréience sous la forme Uniprot : [Liste de résultats]
	 private HashMap<String, List<String>> myHashMap = new HashMap<String,List<String>>();
	 private Vector<String> cellArray = new Vector<String>(); 
	 private Cell cell; private File file;
	//Nom de la colonne contenant les codes Uniprot
	 private String key = "Accession";int keyIndex = 0;
	 boolean check = true;
	 
	 public ExcelPart(String pathtofile) {
		   
		  
		   try {
			   //Ouverture du fihier et creation d'un reader pour le excel
			   //Pathtofile correspont au chemain d'accès du fichier de résultats
			 this.file = new File(pathtofile);   
			 FileInputStream fis = new FileInputStream(file); 
			 XSSFWorkbook wb = new XSSFWorkbook(fis);
			 XSSFSheet sheet = wb.getSheetAt(0);
			 Iterator<Row> itr = sheet.iterator(); 	 
			
			  cellArray = new Vector<String>();

			   Vector<String> keys = new Vector<String>();
			   Row keysRow = itr.next();
			   Iterator<Cell> keyCellIterator = keysRow.cellIterator();
			//Lecture de la première ligne du excel pour obtenir le nom des colonnes qui est placé dans le vecteur keys
			   while (keyCellIterator.hasNext())  
			    {
			    Cell keyCell = keyCellIterator.next(); 
			    keys.add(String.valueOf(keyCell));
			    }
			    keyIndex = keys.indexOf(key);
			    keys.remove(keyIndex);
			//Lecture du reste des lignes du fichier
			   while (itr.hasNext()) {
			    cellArray = new Vector<String>();  
			    Row row = itr.next();  
	
			    for (int i=1;i<=keys.size()+1;i++) {  
			      cell = row.getCell(i,Row.MissingCellPolicy.RETURN_BLANK_AS_NULL); 
			      cellArray.add(String.valueOf(cell));
			      }
				   //Une fois la ligne enregistrée dans le vecteur cellArray, le code Uniprot en est extrait grace à son index
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
	//Méethode pour obtenir les code Unirpot, nécessaire pour un premier filtrage des nodes
	 public List<String> getKeys(){
		 List<String> keys = new ArrayList<String>();
		 for (HashMap.Entry<String, List<String>> entry : myHashMap.entrySet()) {
			 keys.add(entry.getKey());
		 }
		 return keys;
	 }
	 //Méthode pour obtenir la Hashmap des résultats
	 public HashMap<String, List<String>> getData(){
		 return myHashMap;
	 }
}
