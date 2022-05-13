package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;


public class ImportTask extends AbstractTask {
    
    final Manager manager;
    String dataPath;
    String networkPath;
    String conditions;
    ImportPanel importPanel;
    
    
    public ImportTask (final Manager manager) {
    
        this.manager = manager;
        networkPath = manager.getNetworkPath();
        dataPath = manager.getDataPath();
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Imports");
        
        if (networkPath.length()>0 && dataPath.length()>0) {
            insertTasksAfterCurrentTask​(new MergeTask(manager));
            insertTasksAfterCurrentTask​(manager.commandTask(null, "network load file file='"+networkPath+"'"));
            insertTasksAfterCurrentTask​(manager.commandTask(null, "table import file file='"+dataPath+"' startLoadRow=0 firstRowAsColumnNames=true"));
            manager.setDataPath("");
            manager.setNetworkPath("");
        } else {
            importPanel = new ImportPanel(manager);
        }
        
    }
     
    
}



