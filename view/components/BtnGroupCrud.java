package view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


//util que se encarga de crear group de btns que trabajara con los forms crud de cada modulo

//evita repetir tener que crear estos btn en los demas archivos y que funcionen en base los roles 

public class BtnGroupCrud extends JPanel implements ActionListener {

	
	private JButton btnEditarProducto, btnBuscar, btnEliminar;
	private JPanel panelSearch ;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BtnGroupCrud() {
		
		setLayout(null);
		setBackground(null);
		crearPanel();
		crearPanelBuscar();
		
	}
	
	public void crearPanel() {
		
		btnEditarProducto = new JButton();
		btnEditarProducto.setText("Editar");
		btnEditarProducto.setIcon(AssetManager.icon("editar.png", 18, 18));
		btnEditarProducto.setBounds(0, 0, 120, 35);
		btnEditarProducto.addActionListener(this);
		btnEditarProducto.setFocusPainted(false);
		btnEditarProducto.setIconTextGap(6);
		btnEditarProducto.setBorder(BorderFactory.createLineBorder(new Color(88, 177, 237), 2, true));
		btnEditarProducto.setForeground(new Color(88, 177, 237));
		btnEditarProducto.setBackground(new Color(255,255,255));
		btnEditarProducto.putClientProperty("JButton.arc", 8);
		add(btnEditarProducto);
				
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(255,255,255));
		btnEliminar.setForeground(new Color(255, 90, 90));
		btnEliminar.setBorder(BorderFactory.createLineBorder(new Color(255,120, 120), 2, true));
		btnEliminar.putClientProperty("JButton.arc",20);
		btnEliminar.setFocusPainted(false);
		btnEliminar.setIcon(AssetManager.icon("borrar.png", 18, 18));
		btnEliminar.setBounds(150, 0, 120, 35);
		btnEliminar.setIconTextGap(6);
		btnEliminar.setFont(Fonts.custom);
		btnEliminar.addActionListener(this);
		add(btnEliminar);
			
	
		
	}
	
	public void crearPanelBuscar() {
		
		panelSearch = new JPanel();
		panelSearch.setBounds(760, 0, 246, 32);
		panelSearch.setBackground(new Color(255, 255, 255));
		panelSearch.setLayout(null);
		
		//JLabel lbTotalRegister = new JLabel(usuarios.size() + "");
		JLabel lbTotalRegister = new JLabel("0");
		lbTotalRegister.setBounds(0, 0, 45, 32);
		panelSearch.add(lbTotalRegister);
		
		JTextField textFieldSearch = new JTextField();
		textFieldSearch.setText("Search");
		textFieldSearch.setBounds(57, 4, 190, 26);
		textFieldSearch.setColumns(10);
		panelSearch.add(textFieldSearch);
		
		add(panelSearch);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
