package view.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class RoundePanel extends JPanel {


	private static final long serialVersionUID = 1L;
	
	private int coverRadius = 20;
	
	public RoundePanel(int radius) {
		
		super();
		setCoverRadius(radius);
		setOpaque(false);
	}
	

	 @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Dimension arcs = new Dimension(coverRadius, coverRadius);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, arcs.width, arcs.height);

	        g2.setColor(getForeground());
	        g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, arcs.width, arcs.height);
	    }
	
	
	public int getCoverRadius() {
		return coverRadius;
	}

	public void setCoverRadius(int coverRadius) {
		this.coverRadius = coverRadius;
	}
	
	
	
}
