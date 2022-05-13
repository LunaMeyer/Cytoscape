package app;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import java.util.List;


public class ClusterTask extends AbstractTask {
    
    final Manager manager;
    List<String> clusterConditions;
    String[] conditionList;
    String attributeList;
    String prefix;
    String veh;
    String ctrl;
    
    
    public ClusterTask (final Manager manager) {
        this.manager = manager;
        prefix = manager.getPrefix();
        conditionList = manager.getConditionList();
        
        attributeList = "";
        veh = "VEH";
        ctrl = "CTRL";
        for (String condition : conditionList) {
            attributeList += prefix+condition+"_"+veh+"_,";
        }
        attributeList += prefix+veh+"_"+ctrl+"_";
        attributeList = attributeList.replaceAll("(_)\\1{1,}", "$1");
    }
    
    
    public void run(TaskMonitor monitor){
        
        //clusterConditions = manager.parseClusterConditions();
        
        
        manager.command(null, "cluster featurevector createNewNetwork=true edgeAttribute=FeatureDistance edgeCutoff='0.05' ignoreMissing=true metric='Euclidean distance' nodeAttributeList='"+attributeList+"' selectedOnly=false zeroMissing=false");
        
        insertTasksAfterCurrentTask​(new VizTask(manager));
        
    }
    
    
}





