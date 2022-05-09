package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;

import java.io.File;


public class ImportTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    String dataPath;
    String networkPath;
    ImportPanel importPanel;
    
    
    public ImportTask (final Manager manager) {
    
        this.manager = manager;
        networkPath = manager.getNetworkPath();
        dataPath = manager.getDataPath();
        
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Imports");
        
        if (networkPath.length()>0 && dataPath.length()>0) {
            manager.command(null, "table import file file='"+dataPath+"' startLoadRow=0 firstRowAsColumnNames=true");
            insertTasksAfterCurrentTask​(new MergeTask(manager));
            insertTasksAfterCurrentTask​(manager.commandTask(null, "network load file file='"+networkPath+"'"));
            manager.setNetworkPath("");
            manager.setDataPath("");
        } else {
            importPanel = new ImportPanel(manager);
        }
        
    }
    
    
    
    
    
}



