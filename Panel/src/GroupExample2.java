import java.awt.Container;
import java.awt.event.*;

/*import java.io.*;*/

import javax.swing.*;  
import javax.swing.JButton;


import static javax.swing.GroupLayout.Alignment.*;  
public class GroupExample2 {  
    public static void main(String[] args) {  
        JFrame frame = new JFrame("GroupLayoutExample");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Container myPanel = frame.getContentPane();  
  
        GroupLayout groupLayout = new GroupLayout(myPanel);  
        groupLayout.setAutoCreateGaps(true);  
        groupLayout.setAutoCreateContainerGaps(true);  
        myPanel.setLayout(groupLayout);  
  
        JButton b1 = new JButton("Browse...");  
        JButton b2 = new JButton("Browse...");  
        JLabel l1 = new JLabel("Choose a network");
        JLabel l2 = new JLabel("Choose data");
        JLabel l1b = new JLabel("");
        JLabel l2b = new JLabel("");
        setEvent(b1, l1b);
        setEvent(b2, l2b);
        
  
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(LEADING).addComponent(l1).addComponent(l2))  
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(b1).addComponent(b2))
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(l1b).addComponent(l2b)));  
  
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(l1).addGap(100).addComponent(b1).addComponent(l1b))  
                .addGroup(groupLayout.createParallelGroup(BASELINE).addComponent(l2).addGap(100).addComponent(b2).addComponent(l2b)));  
        
        frame.setSize(500,300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);  
    }
    
    public static void setEvent(JButton button, JLabel label) {
    	button.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
			 
			        // For Directory
			        //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			        // For File
			        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			 
			        fileChooser.setAcceptAllFileFilterUsed(false);
			 
			        int rVal = fileChooser.showOpenDialog(null);
			        if (rVal == JFileChooser.APPROVE_OPTION) {
			        	String file = fileChooser.getSelectedFile().toString();
			        	int last_letter = file.length();
			        	int last_slash = file.lastIndexOf("\\");
			        	label.setText(file.substring(last_slash+1, last_letter));
			        }	
		      }
		});
    }
    
} 