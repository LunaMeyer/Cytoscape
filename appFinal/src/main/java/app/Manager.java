package app;

import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.work.TaskManager;
import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.view.presentation.annotations.AnnotationManager;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.TaskObserver;
import org.cytoscape.work.Task;
import org.cytoscape.work.AbstractTask;

import org.cytoscape.model.*;
import org.cytoscape.task.edit.*;
import org.cytoscape.model.subnetwork.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.model.*;
import org.cytoscape.view.presentation.annotations.*;
import org.cytoscape.command.CommandExecutorTaskFactory;
import org.cytoscape.task.create.NewNetworkSelectedNodesOnlyTaskFactory;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.model.CyNetwork;
import org.cytoscape.model.CyNode;

import org.cytoscape.io.read.CyNetworkReader;
import org.cytoscape.io.read.CyNetworkReaderManager;
import org.cytoscape.io.read.CyTableReader;
import org.cytoscape.io.read.InputStreamTaskFactory;
import java.io.*;
import java.util.*;

import org.cytoscape.task.read.LoadNetworkFileTaskFactory;
import org.cytoscape.task.read.LoadNetworkURLTaskFactory;
import org.cytoscape.task.read.LoadTableFileTaskFactory;
import java.net.URL;
import java.util.Set;

class Manager {
    
    TaskManager<?,?> taskManager;
    CyServiceRegistrar registrar;
    CyRootNetworkManager cyRootNetworkManager;
    AnnotationManager annotationManager;
    CyTableManager cyTableManager;
    File networkFile;
    File dataFile;
    CyNetworkManager cyNetworkManagerServiceRef;
    CyNetworkNaming cyNetworkNamingServiceRef;
    CyNetworkFactory cyNetworkFactoryServiceRef;
    ImportDataTableTaskFactory importDataTableTaskFactory;
    MergeTablesTaskFactory mergeTablesTaskFactory;
    CyNetwork network;
    CyNetwork[] networks;
    LoadNetworkFileTaskFactory loadNetworkFileTaskFactory;
    LoadNetworkURLTaskFactory loadNetworkURLTaskFactory;
    LoadTableFileTaskFactory loadTableFileTaskFactory;
    CyNetworkReaderManager cyNetworkReaderManager;
    CyApplicationManager cyApplicationManager;
    InputStreamTaskFactory input;
    FileInputStream fis;
    TaskMonitor tm;
    VisualStyleFactory visualStyleFactory;
    VisualMappingManager visualMappingManager;
    VisualMappingFunctionFactory visualMappingFunctionFactory;
    VisualMappingFunctionFactory visualMappingFunctionFactory2;
    List<String> col;
    String ref;
    VisualStyle vizu1;
    CommandExecutorTaskFactory cmd;
    TaskObserver taskObserver;
    String clientName;
    NewNetworkSelectedNodesOnlyTaskFactory newNetworkSelectedNodesOnlyTaskFactory;
    
    //var fct parseParam
    String prefix = "log2(Abundance Ratio: ";
    Collection<CyColumn> cycols;
    int lgth = prefix.length();
    int fullLgth;
    List<String> allConditions = new ArrayList<>();
    List<String> clusterConditions;
    Map<String,Integer> map = new HashMap<String,Integer>();
    List<String[]> pairs = new ArrayList<>();
    String current;
    String tmp;
    String name;
    String[] pair;
    String ctrl = "control";
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
        mergeTablesTaskFactory = registrar.getService(MergeTablesTaskFactory.class);
        newNetworkSelectedNodesOnlyTaskFactory = registrar.getService(NewNetworkSelectedNodesOnlyTaskFactory.class);
        cyTableManager = registrar.getService(CyTableManager.class);
    }
    
    public CyTableManager getTabMan() {
        return cyTableManager;
    }
    
    public NewNetworkSelectedNodesOnlyTaskFactory getSelectNetworkTask() {
        return newNetworkSelectedNodesOnlyTaskFactory;
    }
    
    public void setNetworkFile (File networkFile) {
        this.networkFile = networkFile;
    }
    
    public void setDataFile (File dataFile) {
        this.dataFile= dataFile;
    }
    
    public void execute(TaskIterator iterator) {
		taskManager.execute(iterator);
	}
	
	public void executeTask(Task task) {
		taskManager.execute(new TaskIterator(task));
	}
	
	public CyApplicationManager getAppMan(){
	    return cyApplicationManager;
	}
	
	public CyRootNetwork getRoot(CyNetwork net) {
	    cyRootNetworkManager = registrar.getService(CyRootNetworkManager.class);
	    return cyRootNetworkManager.getRootNetwork(net);
	}
	
	public VisualStyle getVizu(String name) {
	    return visualStyleFactory.createVisualStyle​(name);
	}
	
	public VisualMappingManager getVizMan() {
	    return visualMappingManager;
	}
	
	public VisualMappingFunctionFactory getVizMapFacto() {
	    return visualMappingFunctionFactory;
	}
	
	public VisualMappingFunctionFactory getVizMapFacto2() {
	    return visualMappingFunctionFactory2;
	}
	
	public void setRef(String ref) {
	    this.ref = ref;
	}
	
	public String getRef() {
	    return ref;
	}
	
    public void setVizu(VisualStyle vizu1) {
	    this.vizu1 = vizu1;
	}
	
	public VisualStyle getVizu(){
	    return vizu1;
	}
	
	public void command (TaskObserver obs, String... str) {
	    taskManager.execute(cmd.createTaskIterator​(obs, str));
	}
	
	public TaskIterator commandTask (TaskObserver obs, String... str) {
	    return cmd.createTaskIterator​(obs, str);
	}
	
	public void setClientName(String str) {
	    clientName = str;
	}
	
	public String getClientName() {
	    return clientName;
	}
	
	public AnnotationManager getAnnMan() {
	    return annotationManager;
	}
	
	public List<String> parseClusterConditions() {
	    
	    clusterConditions = new ArrayList<>();
	    cycols = cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().getColumns();
	    for (CyColumn column : cycols) {
            name = column.getName();
            fullLgth = name.length();
            if (name.startsWith​(prefix)) {
                tmp = name.substring​(lgth,fullLgth-1);
                pair = tmp.split(" / ");
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
            int x = map.getOrDefault(current,0);
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
                clusterConditions.add(iter.getKey()+" / "+veh);
            }
        }
        
        clusterConditions.add(veh+" / "+ctrl);
        
        return clusterConditions;
	}
	
	public List<String> parseParam() {
	    
	    cycols = cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().getColumns();
	    for (CyColumn column : cycols) {
            name = column.getName();
            fullLgth = name.length();
            if (name.startsWith​(prefix)) {
                tmp = name.substring​(lgth,fullLgth-1);
                allConditions.add(tmp);
            }  
        }
	    return allConditions;
	}
	
	
	
	
}



