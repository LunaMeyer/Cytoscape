package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.Tunable;

import java.io.File;


public class ImportTask extends AbstractTask {
    
    final Manager manager;
    CyApplicationManager appMan;
    
    @Tunable(description="network file", params="input=true")
    public File nfile;
    
    @Tunable(description="data file", params="input=true")
    public File dfile;
    
    
    public ImportTask (final Manager manager) {
        this.manager = manager;
    }
    
    
    public void run(TaskMonitor monitor){
        
        
        monitor.setTitle("Imports");
        
        
        manager.command(null, "table import file file='"+dfile+"' startLoadRow=0 firstRowAsColumnNames=true");
        
        
        insertTasksAfterCurrentTask​(manager.commandTask(null, "network load file file='"+nfile+"'"));
        
    }
    
    
}



