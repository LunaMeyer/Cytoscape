package app;

import java.awt.Color;
import java.awt.Paint;
import java.util.Iterator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.*;

import org.cytoscape.view.model.*;
import org.cytoscape.view.vizmap.*;
import org.cytoscape.view.vizmap.mappings.*;
import org.cytoscape.view.presentation.property.*;
import org.cytoscape.view.presentation.property.values.NodeShape;
import java.io.File;

public class VizTask2 extends AbstractTask {
    
    final Manager manager;
    CyNetwork net;
    CyApplicationManager appMan;
    CyNetworkView view;
    VisualStyle vizu1;
    CyTable tab;
    VisualMappingManager vizMan;
    VisualMappingFunctionFactory vizMapFacto;
    VisualMappingFunctionFactory vizMapFacto2;
    String column;
    String vizName;
    
    public VizTask2(final Manager manager) {
        this.manager = manager;
    }
    
    public void run(TaskMonitor monitor){
        
        column = manager.getRef();
        monitor.setTitle("VizuTaskApplication");
        appMan = manager.getAppMan();
        File dir = appMan.getCurrentDirectory();
        vizMan = manager.getVizMan();
        vizMapFacto = manager.getVizMapFacto();
        vizMapFacto2 = manager.getVizMapFacto2();
        vizName = "AutoVizu";
        File vizFile = new File(dir.getAbsolutePath()+"/"+vizName);
        manager.setVizFile(vizFile);
        
        if (vizFile.exists()) {
            vizFile.delete();
        }
        
        //delete les trucs rate d'avant pour les testz
        Iterator it = vizMan.getAllVisualStyles().iterator();
		while (it.hasNext()){
			VisualStyle curVS = (VisualStyle)it.next();
			if (curVS.getTitle().equalsIgnoreCase(vizName)) {
				vizMan.removeVisualStyle(curVS);
				break;
			}
		}
        
        view = appMan.getCurrentNetworkView();
        tab = appMan.getCurrentTable();
        
        vizu1 = manager.getVizu(vizName);
        manager.setVizu(vizu1);
        vizMan.addVisualStyle(vizu1);
        vizMan.setVisualStyle​(vizu1, view);
        
        
        ContinuousMapping fillNode = (ContinuousMapping) vizMapFacto.createVisualMappingFunction("log2_Abundance_Ratio_"+column+"_", Double.class, BasicVisualLexicon.NODE_FILL_COLOR);
        vizu1.addVisualMappingFunction(fillNode);
         
         BoundaryRangeValues<Paint> brv1 = new BoundaryRangeValues<Paint>(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA);
        Double val1 = -2d;
        
        BoundaryRangeValues<Paint> brv2 = new BoundaryRangeValues<Paint>(Color.WHITE, Color.WHITE, Color.WHITE);
        Double val2 = 0d;
        
        BoundaryRangeValues<Paint> brv3 = new BoundaryRangeValues<Paint>(Color.GREEN, Color.GREEN, Color.GREEN);
        Double val3 = 2d;
        
        
        fillNode.addPoint(val1,brv1);
        fillNode.addPoint(val2,brv2);
        fillNode.addPoint(val3,brv3);
        
        
        PassthroughMapping label = (PassthroughMapping) vizMapFacto2.createVisualMappingFunction("stringdb_canonical_name", String.class, BasicVisualLexicon.NODE_LABEL);
        vizu1.addVisualMappingFunction(label);
        
        ContinuousMapping shape = (ContinuousMapping) vizMapFacto.createVisualMappingFunction("Abundance_Ratio_Adj_P_Value_"+column+"_", Double.class, BasicVisualLexicon.NODE_SHAPE);
        BoundaryRangeValues<NodeShape> brvs1 = new BoundaryRangeValues<NodeShape>(NodeShapeVisualProperty.ELLIPSE, NodeShapeVisualProperty.RECTANGLE, NodeShapeVisualProperty.RECTANGLE);
        shape.addPoint(0.05,brvs1);
        vizu1.addVisualMappingFunction(shape);
        
        ContinuousMapping size = (ContinuousMapping) vizMapFacto.createVisualMappingFunction("tissue_skin", Double.class, BasicVisualLexicon.NODE_SIZE);
        vizu1.addVisualMappingFunction(size);
        
        Double width = view.getVisualProperty(BasicVisualLexicon.NETWORK_WIDTH);
        Double height = view.getVisualProperty(BasicVisualLexicon.NETWORK_HEIGHT);
        
        //manager.command(null, "vizmap export options='Style XML (*.xml)' outputFile="+vizFile+" styles="+vizName);
        //manager.command(null, "vizmap load file file="+vizFile+".xml");
        
        
        //insertTasksAfterCurrentTask​(new TestViz(manager));
        
    }
    
    
}





