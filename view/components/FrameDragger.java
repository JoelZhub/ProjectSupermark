package view.components;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class FrameDragger {

	private Point initialClick;
	
	public FrameDragger(JFrame frame) {
		frame.addMouseListener( new MouseAdapter() {
			 public void mousePressed(MouseEvent e) {
				 initialClick = e.getPoint();
			 }
		});
		
		frame.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent e) {
				
				int currentX = frame.getLocation().x;
				int currentY = frame.getLocation().y;
				
				int moveX = currentX  + e.getX() - initialClick.x;
				int moveY = currentY + e.getY() - initialClick.y;
				
				frame.setLocation(moveX, moveY);
			}
			
		});
		
	}
	
	
	
}
