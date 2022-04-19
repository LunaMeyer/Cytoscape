package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;


public class PanelTaskFactory extends AbstractTaskFactory {
    
    //public PanelTaskFactory(){}   ---We dont even need a constructor?
    
    public TaskIterator createTaskIterator(){
    
        return new TaskIterator(new PanelTask());
        
    }
    
}






