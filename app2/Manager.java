package app;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;
import org.cytoscape.task.edit.*;
import org.cytoscape.model.subnetwork.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.model.*;
import org.cytoscape.view.presentation.annotations.*;
import org.cytoscape.command.CommandExecutorTaskFactory;
import org.cytoscape.task.read.LoadVizmapFileTaskFactory;
import org.cytoscape.task.visualize.ApplyVisualStyleTaskFactory;

import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.TaskObserver;
import org.cytoscape.work.Task;
import org.cytoscape.work.AbstractTask;

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
    Collection<CyColumn> cycols;
    String clientName;
    LoadVizmapFileTaskFactory loadVizmapFileTaskFactory;
    ApplyVisualStyleTaskFactory applyVisualStyleTaskFactory;
    File vizFile;
    
    //var fct parseParam
    String prefix = "log2_Abundance_Ratio_";
    int lgth = prefix.length();
    int fullLgth;
    List<String> conditions = new ArrayList<>();
    List<String> endConditions = new ArrayList<>();
    Map<String,Integer> map = new HashMap<String,Integer>(4);
    List<String[]> pairs = new ArrayList<>();
    String current;
    String tmp;
    String name;
    String[] pair;
    String ctrl = "CTRL";
    String veh;
    
    public Manager(final CyServiceRegistrar registrar) {
        this.registrar = registrar;
        taskManager = registrar.getService(TaskManager.class);
        cyApplicationManager = registrar.getService(CyApplicationManager.class);
        visualStyleFactory = registrar.getService(VisualStyleFactory.class);
        visualMappingManager = registrar.getService(VisualMappingManager.class);
        visualMappingFunctionFactory = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
        visualMappingFunctionFactory2 = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");
        cmd = registrar.getService(CommandExecutorTaskFactory.class);
        loadVizmapFileTaskFactory =  registrar.getService(LoadVizmapFileTaskFactory.class);
        applyVisualStyleTaskFactory = registrar.getService(ApplyVisualStyleTaskFactory.class);
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
	
	public void loadN(File file) {
	    
	    loadNetworkFileTaskFactory = registrar.getService(LoadNetworkFileTaskFactory.class);
	    //loadTableFileTaskFactory = registrar.getService(LoadTableFileTaskFactory.class);
	    TaskIterator test = loadNetworkFileTaskFactory.createTaskIterator​(file);
	    taskManager.execute(test);
	    //CyNetwork net = cyApplicationManager.getCurrentNetwork();
	    //CyTable tab = net.getDefaultNetworkTable();
	    //int nb = net.getNodeCount();
	    //TestPanel test = new TestPanel (12);
	    //loadTableFileTaskFactory.insertTasksAfterCurrentTask(loadTableFileTaskFactory.createTaskIterator​(file));
	    
	    
	}
	
	
	public CyApplicationManager getAppMan(){
	    return cyApplicationManager;
	}
	
	
	public TaskIterator loadNetworkFromFile (File file) {
	    loadNetworkFileTaskFactory = registrar.getService(LoadNetworkFileTaskFactory.class);
        return loadNetworkFileTaskFactory.createTaskIterator​(file);
	}
	
	public void loadNetworkFromURL (URL url) {
	    loadNetworkURLTaskFactory = registrar.getService(LoadNetworkURLTaskFactory.class);
	    taskManager.execute(loadNetworkURLTaskFactory.loadCyNetworks​(url));
	}
	
	public TaskIterator loadDataFromFile (File file) {
	    loadTableFileTaskFactory = registrar.getService(LoadTableFileTaskFactory.class);
	    return loadTableFileTaskFactory.createTaskIterator​(file);
	}
	
	public MergeTablesTaskFactory getMergeTask(){
	    mergeTablesTaskFactory = registrar.getService(MergeTablesTaskFactory.class);
	    return mergeTablesTaskFactory;
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
	
	public void setParams(List<String> col) {
	    this.col = col;
	}

	public List<String> getParams() {
	    return endConditions;
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
	
	public void setClientName(String str) {
	    clientName = str;
	}
	
	public Set<VisualStyle> loadVizFile() {
	    return loadVizmapFileTaskFactory.loadStyles(vizFile);
	}
	
	public void setVizFile(File f) {
	    vizFile = f;
	}
	
	public TaskIterator applyVizTask(Set<CyNetworkView> views) {
	    return applyVisualStyleTaskFactory.createTaskIterator(views);
	}
	
	public List<String> parseParam () {
	
	    cycols = cyApplicationManager.getCurrentNetwork().getDefaultNodeTable().getColumns();
	    for (CyColumn column : cycols) {
            name = column.getName();
            fullLgth = name.length();
            if (name.startsWith​(prefix)) {
                tmp = name.substring​(lgth,fullLgth-1);
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
                endConditions.add(iter.getKey()+"_"+veh);
            }
        }
        endConditions.add(veh+"_"+ctrl);
        
        return endConditions;
	}
	
}



