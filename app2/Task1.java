package app;

import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.task.read.LoadNetworkURLTaskFactory;
import org.cytoscape.task.read.LoadTableFileTaskFactory;
import org.cytoscape.work.TaskIterator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.Tunable;
import org.cytoscape.model.*;
import java.io.File;



public class Task1 extends AbstractTask {
    
    final Manager manager;
    int nb;
    CyNetwork net;
    CyApplicationManager mana;
    LoadNetworkFileTaskFactory loadNetworkFileTaskFactory;
    LoadTableFileTaskFactory loadTableFileTaskFactory;
    TaskIterator taskIterator;
    File fn = new File("/home/vincent/Cytoscape_v3.9.1/STRING network - Homo_sapiens_nat.cyjs");
    File fd = new File("/home/vincent/Cytoscape_v3.9.1/Elysia_Bioscience_Data_Res.xlsx");
    
    @Tunable(description="network file", params="input=true")
    public File nfile;
    
    @Tunable(description="data file", params="input=true")
    public File dfile;
    
    
    
    public Task1 (final Manager manager) {
        this.manager = manager;
    }
    
    //run app, run --- Method detected and used by the voodoo as actionEvent.
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("Bananagram");
        mana = manager.getAppMan();
        
        
        taskIterator = manager.loadDataFromFile(dfile);
        insertTasksAfterCurrentTask​(taskIterator);
        taskIterator = manager.loadNetworkFromFile(nfile);
        insertTasksAfterCurrentTask​(taskIterator);
        
        
    }
    
    
}



