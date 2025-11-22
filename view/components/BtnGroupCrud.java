package view.components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


//util que se encarga de crear group de btns que trabajara con los forms crud de cada modulo

//evita repetir tener que crear estos btn en los demas archivos y que funcionen en base los roles 

public class BtnGroupCrud extends JPanel implements ActionListener {

	
	private JButton btnEditarProducto, btnBuscar, btnEliminar;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BtnGroupCrud() {
		
		setLayout(null);
		setBackground(null);
		crearPanel();
		
	}
	
	public void crearPanel() {
		
		btnEditarProducto = new JButton("");
		btnEditarProducto.setIcon(AssetManager.icon("editar.png", 30, 30));
		btnEditarProducto.setBounds(0, 0, 30, 30);
		btnEditarProducto.addActionListener(this);
		BtnStyle.flat(btnEditarProducto);
		add(btnEditarProducto);
				
		btnBuscar = new JButton("");
		btnBuscar.setIcon(AssetManager.icon("lupa.png", 30, 30));
		btnBuscar.setBounds(81, 0, 30, 30);
		btnBuscar.addActionListener(this);
		BtnStyle.flat(btnBuscar);
		add(btnBuscar);
				
		btnEliminar = new JButton("");
		btnEliminar.setIcon(AssetManager.icon("eliminar.png", 30, 30));
		btnEliminar.setBounds(159, 0, 30, 30);
		BtnStyle.flat(btnEliminar);
		btnEliminar.addActionListener(this);
		add(btnEliminar);
			
	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
