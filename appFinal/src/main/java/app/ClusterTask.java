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
        
        manager.command(null, "cluster featurevector createNewNetwork=true edgeAttribute=FeatureDistance edgeCutoff='0.05' ignoreMissing=true metric='Euclidean distance' nodeAttributeList='log2_Abundance_Ratio_"+clusterConditions.get(0)+"_,log2_Abundance_Ratio_"+clusterConditions.get(1)+"_,log2_Abundance_Ratio_"+clusterConditions.get(2)+"_,log2_Abundance_Ratio_"+clusterConditions.get(3)+"_' selectedOnly=false zeroMissing=false");
        
        insertTasksAfterCurrentTask​(new VizTask(manager));
        
    }
    
    
}





