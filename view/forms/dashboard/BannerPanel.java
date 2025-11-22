package view.forms.dashboard;

import java.awt.Color;
import javax.swing.JLabel;

import view.components.Fonts;
import view.components.RoundePanel;

public class BannerPanel  extends RoundePanel{


	private static final long serialVersionUID = 1L;

	
	public BannerPanel() {
		super(50);
		setBackground(new Color(255, 255, 255));
		//setBounds(298, 55, 1030, 129);
		setLayout(null);
		
		crearPanel();
	}
	
	
	public void crearPanel() {
		

		JLabel lbSaludo1 = new JLabel("Bienvenido,");
		lbSaludo1.setBounds(34, 47, 221, 22);
		lbSaludo1.setFont(Fonts.bold);
		add(lbSaludo1);
		
		JLabel lbSaludo2 = new JLabel("Admin");
		lbSaludo2.setBounds(34, 73, 175, 21);
		lbSaludo2.setFont(Fonts.bold);
		add(lbSaludo2);
	}
	
}
