package app;

import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.command.CommandExecutorTaskFactory;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.SynchronousTaskManager;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.TaskObserver;
import org.cytoscape.work.Task;
import org.cytoscape.work.AbstractTask;

import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.VisualStyleFactory;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.presentation.annotations.AnnotationManager;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyTableManager;
import org.cytoscape.model.CyColumn;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;


//The manager serves as some sort of relay between the different steps of the pipeline to store up all information created or modified by the steps into a unique object that will be passed on, instead of numerous different variables.
//It also serve as relay to call up different service managers or tasks provided by cytoscape.
class Manager {
    
    //the board
    TaskManager<?,?> taskManager;
    SynchronousTaskManager<?> synchTaskMan;
    CyServiceRegistrar registrar;
    CyApplicationManager cyApplicationManager;
    VisualMappingManager visualMappingManager;
    CyTableManager cyTableManager;
    AnnotationManager annotationManager;
    TaskObserver taskObserver;
    TaskMonitor tm;
    
    //the factories
    VisualStyleFactory visualStyleFactory;
    VisualMappingFunctionFactory visualMappingFunctionFactory;
    VisualMappingFunctionFactory visualMappingFunctionFactory2;
    NewNetworkSelectedNodesOnlyTaskFactory newNetworkSelectedNodesOnlyTaskFactory;
    CommandExecutorTaskFactory cmd;
    
    //the vars
    String ref;
    String clientName;
    String networkPath = "";
    String dataPath = "";
    String conditions = "";
    
    //var fcts parseConditions
    String prefix = "log2_Abundance_Ratio_";
    Collection<CyColumn> cycols;
    int lgth = prefix.length();
    int fullLgth;
    List<String> allConditions = new ArrayList<>();
    List<String> clusterConditions;
    String[] conditionList;
    Map<String,Integer> map = new HashMap<String,Integer>();
    List<String[]> pairs = new ArrayList<>();
    String current;
    String tmp;
    String name;
    String[] pair;
    String ctrl;
    String veh;
    
    
    public Manager(final CyServiceRegistrar registrar) {
        this.registrar = registrar;
        taskManager = registrar.getService(TaskManager.class);
        cyApplicationManager = registrar.getService(CyApplicationManager.class);
        annotationManager = registrar.getService(AnnotationManager.class);
        visualStyleFactory = registrar.getService(VisualStyleFactory.class);
        visualMappingManager = registrar.getService(VisualMappingManager.class);
        visualMappingFunctionFactory = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
        visualMappingFunctionFactory2 = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");
        cmd = registrar.getService(CommandExecutorTaskFactory.class);
        newNetworkSelectedNodesOnlyTaskFactory = registrar.getService(NewNetworkSelectedNodesOnlyTaskFactory.class);
        cyTableManager = registrar.getService(CyTableManager.class);
    }
    
    
    //commands
    public void executeSynchTask(Task task) {
        synchTaskMan.execute(new TaskIterator(task));
    }
    
    public void executeAll(Task task1, Task task2) {
        taskManager.execute(new TaskIterator(task1, task2));
    }
    
    public void executeTask(Task task) {
        taskManager.execute(new TaskIterator(task));
    }
    
    public void command (TaskObserver obs, String... str) {
        taskManager.execute(cmd.createTaskIterator???(obs, str));
    }
    
    public TaskIterator commandTask (TaskObserver obs, String... str) {
        return cmd.createTaskIterator???(obs, str);
    }
    
    
    //fetch managers
    public CyApplicationManager getAppMan(){
        return cyApplicationManager;
    }
    
    public CyTableManager getTabMan() {
        return cyTableManager;
    }
    
    public VisualMappingManager getVizMan() {
        return visualMappingManager;
    }
    
    public AnnotationManager getAnnMan() {
        return annotationManager;
    }
    
    
    //fetch tasks
    public NewNetworkSelectedNodesOnlyTaskFactory getSelectNetworkTask() {
        return newNetworkSelectedNodesOnlyTaskFactory;
    }
    
    public VisualStyle getVizu(String name) {
        return visualStyleFactory.createVisualStyle???(name);
    }
    
    public VisualMappingFunctionFactory getVizMapFacto() {
        return visualMappingFunctionFactory;
    }
    
    public VisualMappingFunctionFactory getVizMapFacto2() {
        return visualMappingFunctionFactory2;
    }
    
    
    //fetch/set vars
    public void setNetworkPath(String str) {
        networkPath = str;
    }
    
    public String getNetworkPath() {
        return networkPath;
    }
    
    public void setDataPath(String str) {
        dataPath = str;
    }
    
    public String getDataPath() {
        return dataPath;
    }
    
    public void setRef(String ref) {
        this.ref = ref;
    }
    
    public String getRef() {
        return ref;
    }
    
    public void setClientName(String str) {
        clientName = str;
    }
    
    public String getClientName() {
        return clientName;
    }
    
    public String getPrefix() {
        return prefix;
    }
    
    public void setConditions(String str) {
        conditions = str;
        conditionList = conditions.split(";");
        for (String condition : conditionList) {
            condition = condition.replaceAll("\\W","_");
            condition = condition.replaceAll("(_)\\1{1,}", "$1");
        }
    }
    
    public String[] getConditionList() {
        return conditionList;
    }
    
    public List<String> getAllConditions() {
        return allConditions;
    }
    
    
    //parsing of column names fonctions
    public List<String> parseClusterConditions() {
        
        clusterConditions = new ArrayList<>();
        cycols = cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().getColumns();
        for (CyColumn column : cycols) {
            name = column.getName();
            fullLgth = name.length();
            if (name.startsWith???(prefix)) {
                tmp = name.substring???(lgth,fullLgth-1);
                pair = tmp.split("_");
                pairs.add(pair);
            }
        }
        
        for (String[] pair : pairs) {
            current = pair[0];
            int currentCpt = map.getOrDefault(current,0);
            map.put(current,currentCpt+1);
        }
        for (String[] pair : pairs) {
            current = pair[1];
            if (map.containsKey(current)==false) {
                map.put(current,0);
            }
        }

        for (Map.Entry<String, Integer> iter : map.entrySet()) {
            if (iter.getValue()==0) {
                ctrl = iter.getKey();
            }
            if (iter.getValue()==1) {
                veh = iter.getKey();
            }
        }
        for (Map.Entry<String, Integer> iter : map.entrySet()) {
            if (iter.getValue()>1) {
                String tmp = iter.getKey();
                clusterConditions.add(iter.getKey()+"_"+veh);
            }
        }
        
        clusterConditions.add(veh+"_"+ctrl);
        
        return clusterConditions;
    }
    
    
    public List<String> parseAllConditions() {
        
        cycols = cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().getColumns();
        for (CyColumn column : cycols) {
            name = column.getName();
            fullLgth = name.length();
            if (name.startsWith???(prefix)) {
                tmp = name.substring???(lgth,fullLgth-1);
                allConditions.add(tmp);
            }  
        }
        return allConditions;
    }
    
    
    
}



