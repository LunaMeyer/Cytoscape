package app;


import org.cytoscape.service.util.CyServiceRegistrar;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;
import org.cytoscape.io.read.InputStreamTaskFactory;


import org.osgi.framework.BundleContext;
import org.cytoscape.service.util.AbstractCyActivator;
import java.util.Properties;


public class CyActivator extends AbstractCyActivator {

    public CyActivator() {
        super();
    }

    
    public void start(BundleContext bc) {
            
            CyServiceRegistrar registrar = getService(bc, CyServiceRegistrar.class);    
            Manager manager = new Manager (registrar);
        
        
        //This is the bananicon eventListener service
        {   
            //Get the object needed for cytoscape to read that specific service
            CySwingApplication cytoscapeDesktopService = getService(bc,CySwingApplication.class);
            //Create the service
            IconAction iconAction = new IconAction(cytoscapeDesktopService, manager);
            //Register the service
            registerService(bc,iconAction,CyAction.class, new Properties());
        }
        
        
        //This is the menu item taskfactory
        {
            //create taskfactory
            Task1Facto task1Facto = new Task1Facto (manager);
            //add menu item
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.BANANAPP");
            props.setProperty("title","Prends 2 bananes");
            //register taskfactory
            registerService(bc,task1Facto,TaskFactory.class, props);
        }
        
        {
            Task2Facto task2Facto = new Task2Facto (manager);
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.BANANAPP");
            props.setProperty("title","step 2");
            registerService(bc,task2Facto,TaskFactory.class, props);
        }
        
        {
            VizTaskFactory vizTaskFactory = new VizTaskFactory (manager);
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.BANANAPP");
            props.setProperty("title","ZouBisouVizu");
            registerService(bc,vizTaskFactory,TaskFactory.class, props);
        }
        
    }
}








