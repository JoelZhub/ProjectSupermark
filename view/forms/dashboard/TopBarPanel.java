package view.forms.dashboard;

import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import view.components.AssetManager;
import view.components.BtnStyle;
import view.components.Fonts;

public class TopBarPanel extends JPanel {

	/**
	 * 
	 * 
	 * 
	 */
	
	private JLabel lbDate;
	private JButton btnLogout, btnDark, btnLight;
	private static final long serialVersionUID = 1L;

	
	public TopBarPanel() {
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		crearPanel();
	}
	
	public void crearPanel() {
		
		LocalDateTime time  = LocalDateTime.now();
		String date = time.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		
		lbDate = new JLabel(date);
		lbDate.setBounds(10, 7, 190, 14);
		lbDate.setFont(Fonts.custom);
		add(lbDate);
		
		btnLogout = new JButton("dddddhhhhhhhhhhhhdh");
		BtnStyle.flat(btnLogout);
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setHorizontalTextPosition(SwingConstants.LEFT);
		btnLogout.setIcon(AssetManager.icon("angulo.png", 24, 24));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.setBounds(910, 2, 183, 25);
		add(btnLogout);
		
		btnDark = new JButton("");
		BtnStyle.flat(btnDark);
		btnDark.setIcon(AssetManager.icon("oscuro.png", 24, 24));
		btnDark.setBounds(797, 2, 27, 27);
		add(btnDark);
		
		btnLight = new JButton("");
		BtnStyle.flat(btnLight);
		btnLight.setIcon(AssetManager.icon("modo-claro.png", 24, 24));
		btnLight.setBounds(853, 2, 27, 27);
		add(btnLight);
	
		//panelHeader.setBounds(287, 10, 1103, 35);
		
		
	}
	
	
	
}
