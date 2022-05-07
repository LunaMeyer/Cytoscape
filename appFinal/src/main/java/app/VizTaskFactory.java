package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

class VizTaskFactory extends AbstractTaskFactory {
    
    final Manager manager;
    
    public VizTaskFactory(final Manager manager) {
        this.manager = manager;
    }
    
    public TaskIterator createTaskIterator(){
    
        return new TaskIterator(new VizTask(manager));
        
    }
}






