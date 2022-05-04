package app;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.work.TaskObserver;
import org.cytoscape.model.*;
import org.cytoscape.task.edit.*;
import org.cytoscape.model.subnetwork.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.model.*;

import org.cytoscape.service.util.CyServiceRegistrar;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.TaskMonitor;
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
import java.io.File;

class Manager {
    
    TaskManager<?,?> taskManager;
    CyServiceRegistrar registrar;
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
    
    
    public Manager(final CyServiceRegistrar registrar) {
        this.registrar = registrar;
        taskManager = registrar.getService(TaskManager.class);
        visualStyleFactory = registrar.getService(VisualStyleFactory.class);
        visualMappingManager = registrar.getService(VisualMappingManager.class);
        visualMappingFunctionFactory = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=continuous)");
        visualMappingFunctionFactory2 = registrar.getService(VisualMappingFunctionFactory.class, "(mapping.type=passthrough)");
        
    }
    
    public void createNetwork () {
        cyNetworkManagerServiceRef = registrar.getService(CyNetworkManager.class);
        cyNetworkNamingServiceRef = registrar.getService(CyNetworkNaming.class);
        cyNetworkFactoryServiceRef = registrar.getService(CyNetworkFactory.class);
        network = cyNetworkFactoryServiceRef.createNetwork();
		network.getRow(network).set(CyNetwork.NAME, cyNetworkNamingServiceRef.getSuggestedNetworkTitle("Banana"));
		
		CyNode node1 = network.addNode();
		CyNode node2 = network.addNode();
		CyNode node3 = network.addNode();
		
		network.getDefaultNodeTable().getRow(node1.getSUID()).set("name", "banane1");
		network.getDefaultNodeTable().getRow(node2.getSUID()).set("name", "banane2");
		network.getDefaultNodeTable().getRow(node3.getSUID()).set("name", "banane3");
		
		network.addEdge(node1, node2, false);
		network.addEdge(node2, node3, false);
		network.addEdge(node1, node3, false);
				
		cyNetworkManagerServiceRef.addNetwork(network);
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
	    cyApplicationManager = registrar.getService(CyApplicationManager.class);
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
	    CyRootNetworkManager cyRootNetworkManager = registrar.getService(CyRootNetworkManager.class);
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

	public Collection<String> getParams() {
	    return col;
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
}



