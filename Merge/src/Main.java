import static javax.swing.GroupLayout.Alignment.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
//import java.io.*;
//import java.util.*;
import java.util.Map.Entry;

import javax.swing.*;

public class Main {
	 	static JPanel myPanel;
	 	static JFrame frame;
	 	static JLabel txt1;
	 	static JLabel txt2;
	 	static JTextField network;
	 	static JTextField data;
	 	static JButton browseNetwork;
	 	static JButton browseData;
	 	static JButton buttonOk;
	 	static JButton buttonCancel;
	    
	    
	public static void main(String[] args) {
		 //create panel and components
        Manager manager = new Manager();
        myPanel = new JPanel();
        txt1 = new JLabel("Choose your network file (format):");
        txt2 = new JLabel("Choose your data file:");
        browseNetwork = new JButton("Browse");
        browseData = new JButton("Browse");
        network = new JTextField(30);
        data = new JTextField(30);
        buttonOk= new JButton ("Ok");
        buttonCancel = new JButton("Cancel");
        
        //add components to panel
        myPanel.add(txt1);
        myPanel.add(network);
        myPanel.add(browseNetwork);
        myPanel.add(txt2);
        myPanel.add(data);
        myPanel.add(browseData);
        myPanel.add(buttonOk);
        myPanel.add(buttonCancel);
        
        //Create Layout
        GroupLayout groupLayout = new GroupLayout(myPanel); 
        groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
        myPanel.setLayout(groupLayout); 
        
        //Set components in the layout
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(txt1).addComponent(txt2))  
                .addGroup(groupLayout.createParallelGroup(CENTER).addComponent(network).addComponent(data).addComponent(buttonCancel))
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(browseNetwork).addComponent(browseData).addComponent(buttonOk)));  
  
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(txt1).addComponent(network).addComponent(browseNetwork))  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(txt2).addComponent(data).addComponent(browseData))
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(buttonCancel).addComponent(buttonOk)));  
        
        
        //create frame
        frame = createFrame("Browse");
        
        //add panel to frame
        frame.add(myPanel);
        setVisible();
        
        //Create Action listeners for the buttons
        browseNetwork.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    network.setText(browse.getSelectedFile().getPath());
                }
                
            }
        });
        
        browseData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                JFileChooser browse = new JFileChooser();
                if (browse.showOpenDialog(myPanel) == JFileChooser.APPROVE_OPTION) {
                    data.setText(browse.getSelectedFile().getPath());
                }
                
            }
        });
        
        //define buttonOk action
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                manager.setNetworkPath(network.getText());
                manager.setDataPath(data.getText());
                mergeMap(manager.getNetworkPath(),manager.getDataPath(), manager);
                frame.dispose();
                
            }
        });
        
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
    
    
    //method to be called to show the panel
    public static void setVisible(){
        frame.setVisible(true);
        frame.pack();
    }
    public static JFrame createFrame(String title) {
    	frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        return frame;
    }
    
    private static void mergeMap(String pathtonetwork,String pathtoresults, Manager manager){
    	String tempkey = new String();
    	List<String> templist = new ArrayList<String>();
    	
    	DataExtract data = new DataExtract(pathtoresults);
    	manager.setKeys(data.getKeys());
    	
    	ExtractNodes parserNodes = new ExtractNodes(pathtonetwork,manager.getKeys());
    	
    	
    	List<String> id = parserNodes.getId();
    	manager.setId(id);
    
    	ExtractEdges parserEdges = new ExtractEdges(pathtonetwork,id);
    	manager.setData(data.getData());
    	manager.setNodes(parserNodes.getNodes());
    	manager.setEdges(parserEdges.getEdges());
    	
    	for(Entry<String, String> entry : manager.getNodes().entrySet()) {
			
			tempkey = entry.getKey();
			templist = manager.getData().get(tempkey);
			templist.add(entry.getValue());
			manager.getMerge().put(tempkey, templist);
		}
    	System.out.println("Finito");
    }
}
