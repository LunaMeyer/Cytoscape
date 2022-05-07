package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyColumn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ClusterTask extends AbstractTask {
    
    final Manager manager;
    List<String> clusterConditions;
    
    
    public ClusterTask (final Manager manager) {
        this.manager = manager;
    }
    
    
    public void run(TaskMonitor monitor){
        
        clusterConditions = manager.parseClusterConditions();
        
       manager.command(null, "cluster featurevector createNewNetwork=true edgeAttribute=FeatureDistance edgeCutoff='0.05' ignoreMissing=true metric='Euclidean distance' nodeAttributeList='log2(Abundance Ratio: "+clusterConditions.get(0)+"),log2(Abundance Ratio: "+clusterConditions.get(1)+"),log2(Abundance Ratio: "+clusterConditions.get(2)+"),log2(Abundance Ratio: "+clusterConditions.get(3)+")' selectedOnly=false zeroMissing=false");
        
        insertTasksAfterCurrentTask​(new VizTask(manager));
        
    }
    
    
}





