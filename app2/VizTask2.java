package app;

import java.awt.Color;
import java.awt.Paint;
import java.util.Iterator;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.TaskMonitor;

import org.cytoscape.application.CyApplicationManager;
import org.cytoscape.model.CyNetwork;

import org.cytoscape.view.model.CyNetworkView;
import org.cytoscape.view.vizmap.VisualStyle;
import org.cytoscape.view.vizmap.VisualMappingFunctionFactory;
import org.cytoscape.view.vizmap.VisualMappingManager;
import org.cytoscape.view.vizmap.mappings.BoundaryRangeValues;
import org.cytoscape.view.vizmap.mappings.ContinuousMapping;
import org.cytoscape.view.vizmap.mappings.PassthroughMapping;
import org.cytoscape.view.presentation.property.BasicVisualLexicon;
import org.cytoscape.view.presentation.property.NodeShapeVisualProperty;
import org.cytoscape.view.presentation.property.values.NodeShape;
import java.io.File;

public class VizTask2 extends AbstractTask {
    
    final Manager manager;
    CyNetwork net;
    CyApplicationManager appMan;
    CyNetworkView view;
    VisualStyle vizu1;
    VisualMappingManager vizMan;
    VisualMappingFunctionFactory vizMapFacto;
    VisualMappingFunctionFactory vizMapFacto2;
    String column;
    String vizName;
    
    
    public VizTask2(final Manager manager) {
        this.manager = manager;
        column = manager.getRef();
        appMan = manager.getAppMan();
        File dir = appMan.getCurrentDirectory();
        vizMan = manager.getVizMan();
        vizMapFacto = manager.getVizMapFacto();
        vizMapFacto2 = manager.getVizMapFacto2();
        vizName = "AutoVizu";
    }
    
    
    public void run(TaskMonitor monitor){
        
        monitor.setTitle("VizuTaskApplication");
        
        //delete style if already named - From app samples.
        Iterator it = vizMan.getAllVisualStyles().iterator();
		while (it.hasNext()){
			VisualStyle curVS = (VisualStyle)it.next();
			if (curVS.getTitle().equalsIgnoreCase(vizName)) {
				vizMan.removeVisualStyle(curVS);
				break;
			}
		}
        
        view = appMan.getCurrentNetworkView();
        
        vizu1 = manager.getVizu(vizName);
        manager.setVizu(vizu1);
        vizMan.addVisualStyle(vizu1);
        vizMan.setVisualStyle​(vizu1, view);
        
        
        ContinuousMapping fillNode = (ContinuousMapping) vizMapFacto.createVisualMappingFunction("log2_Abundance_Ratio_"+column+"_", Double.class, BasicVisualLexicon.NODE_FILL_COLOR);
        vizu1.addVisualMappingFunction(fillNode);
         
         Color color1 = new Color(197,27,125);
         BoundaryRangeValues<Paint> brv1 = new BoundaryRangeValues<Paint>(color1, color1, color1);
        Double val1 = -2d;
        
        BoundaryRangeValues<Paint> brv2 = new BoundaryRangeValues<Paint>(Color.WHITE, Color.WHITE, Color.WHITE);
        Double val2 = 0d;
        
        Color color3 = new Color(77,146,33);
        BoundaryRangeValues<Paint> brv3 = new BoundaryRangeValues<Paint>(color3, color3, color3);
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

        
    }
    
    
}





