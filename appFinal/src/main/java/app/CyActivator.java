package app;


import org.cytoscape.service.util.CyServiceRegistrar;

import org.cytoscape.model.CyNetworkFactory;
import org.cytoscape.session.CyNetworkNaming;
import org.cytoscape.model.CyNetworkManager;
import org.cytoscape.work.TaskFactory;
import org.cytoscape.application.swing.CyAction;
import org.cytoscape.application.swing.CySwingApplication;

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
            
        
        {
            //create taskfactory
            StartFacto startFacto = new StartFacto (manager);
            //add menu item
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.AutomationApp");
            props.setProperty("title","Imports");
            //register taskfactory
            registerService(bc,startFacto,TaskFactory.class, props);
        }

        
        {
            VizTaskFactory vizTaskFactory = new VizTaskFactory (manager);
            Properties props = new Properties();
            props.setProperty("preferredMenu","Apps.AutomationApp");
            props.setProperty("title","Visualization Panel");
            registerService(bc,vizTaskFactory,TaskFactory.class, props);
        }
        
        
    }
}








