package app;


import org.cytoscape.work.AbstractTaskFactory;
import org.cytoscape.work.TaskIterator;

public class Task2Facto extends AbstractTaskFactory {
    
    Manager manager;
    
    public Task2Facto (final Manager manager) {
        this.manager = manager;
    }
    
    public TaskIterator createTaskIterator(){
        return new TaskIterator(new Task2(manager));
    }
}
