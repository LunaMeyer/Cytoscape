
import java.util.*;

class Manager {
    
    private String networkPath;
    private String dataPath;
    private Map<String, String> nodes; 
    private Map<String, List<String>> edges;
    private Map<String, List<String>> data;
	private Map<String, List<String>> merge= new HashMap<String,List<String>>();
	private List<String> keys = new ArrayList<String>();
	private List<String> id = new ArrayList<String>();
	
    
    public Manager() {
       
        
    }
    
    public void setNetworkPath (String networkPath) {
        this.networkPath = networkPath;
    }
    
    public void setDataPath (String dataPath) {
        this.dataPath = dataPath;
    }
    
    public String getNetworkPath () {
        return networkPath;
    }
    
    public String getDataPath () {
        return dataPath;
    }
    
    //Method to get/set the Map of the merge data-nodes
    public void setMerge (Map<String, List<String>> merge) {
        this.merge = merge;
    }
    public Map<String, List<String>> getMerge () {
        return merge;
    }
    
    //Method to get/set the Map of data
    public void setData (Map<String, List<String>> data) {
        this.data = data;
    }
    public Map<String, List<String>> getData () {
        return data;
    }
    
    //Method to get/set the Map of nodes
    public void setNodes (Map<String, String> nodes) {
        this.nodes = nodes;
    }
    public Map<String,String> getNodes () {
        return nodes;
    }
    
    //Method to get/set the Map of edges
    public void setEdges (Map<String, List<String>> edges) {
        this.edges = edges;
    }
    public Map<String, List<String>> getEdges () {
        return edges;
    }
    
    //Method to get/set the list of Uniprot code of the results
    public void setKeys (List<String> keys) {
        for(int i = 0; i<keys.size();i++) {
        	this.keys.add(keys.get(i));
        }
    }
    public List<String> getKeys () {
        return keys;
    }
    
    //Method to get/set the list of id of the nodes (used to get the edges)
    public void setId (List<String> id) {
    	for(int i = 0; i<id.size();i++) {
        	this.id.add(id.get(i));
        }
    } 
    public List<String> getId () {
        return id;
    }
}



