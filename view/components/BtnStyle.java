package view.components;

import java.awt.Color;

import javax.swing.JButton;

//aplicar desing style nimbus a  btn especificos
public class BtnStyle  {
	
	public static void flat(JButton b) { 
        b.setContentAreaFilled(false);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
    }
	
	//crear btn con diseños especificos 
	
	 public static void primary(JButton b, Color c) {
	        b.setOpaque(true);
	        b.setBackground(c);
	        b.setForeground(Color.WHITE);
	    }
	 
	 
	 public static void second (JButton b) {
		 
		 	b.setOpaque(true);
	        b.setBackground(new Color(255,255,255));
	        b.setForeground(Color.black);
	        b.setFocusPainted(false);       
	        b.setBorderPainted(false);      
	        b.setContentAreaFilled(false);  
	        b.setOpaque(true);             
	        b.setBorder(null);
	 }
	
    
}
