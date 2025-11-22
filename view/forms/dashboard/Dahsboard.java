package view.forms.dashboard;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.components.BtnGroupCrud;
import view.components.FrameDragger;
import view.modules.admin.DashboardAdmin;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatLightLaf;

import navigation.NavigationManager;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Dahsboard extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMenu, panelBtnCrud, panelFondo,panelTopBar, panelBanner,  panelContenido;

	private NavigationManager navigation;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
					FlatLightLaf.setup();
					
					Dahsboard dash =new Dahsboard(null);
					dash.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setChangeState(NavigationManager navigation) {
		
		this.navigation = navigation;
	}
	public  Dahsboard(NavigationManager navigation) {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 1400, 700);
		new FrameDragger(this);
		
		this.navigation = navigation;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		panelFondo = new JPanel();
		panelFondo.setBackground(new Color(255, 255, 255));
		panelFondo.setBounds(0, 0, 1400, 700);
		getContentPane().add(panelFondo);
		panelFondo.setLayout(null);

		panelMenu = new JPanel();
		panelMenu.setLayout(null);
		panelMenu.setBounds(0, 0, 277, 700);
		panelMenu.setBackground(new Color(255, 255, 255));
		panelFondo.add(panelMenu);
		
		panelContenido = new JPanel();
		panelContenido.setLayout(null);
		panelContenido.setBounds(300, 170, 1090, 460);
		panelContenido.setBackground(null);
		panelFondo.add(panelContenido);
			

		panelTopBar = new JPanel();
		panelTopBar.setLayout(null);
		panelTopBar.setBackground(new Color(255, 255, 255));
		panelTopBar.setBounds(287, 10, 1103, 35);
		panelFondo.add(panelTopBar);
		
		panelBanner = new JPanel();
		panelBanner.setLayout(null);
		panelBanner.setBackground(new Color(255, 255, 255));
		panelBanner.setBounds(298, 55, 1030, 129);
		panelFondo.add(panelBanner);

//		
		panelBtnCrud = new JPanel();
		panelBtnCrud.setLayout(null);
		panelBtnCrud.setBackground(new Color(255,255,255));
		panelBtnCrud.setBounds(10, 150, 1043, 36);
		panelContenido.add(panelBtnCrud);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(280, 0, 1, 700);
		panelFondo.add(separator);
		
		inicializarComponentes();
	
	}

	
	public void inicializarComponentes() {
		setPanelMenu(new MenuPanel());
		setPanelTopBar(new TopBarPanel());
		setPanelBanner(new BannerPanel());
		setPanelContent(new DashboardAdmin());
		setPanelBtnCrud(new BtnGroupCrud());
	}
	
	public void setPanelBtnCrud(JPanel btns) {
		btns.setBounds(0,0, panelBtnCrud.getWidth(), panelBtnCrud.getHeight());
		panelBtnCrud.removeAll();
		panelBtnCrud.add(btns);
		panelBtnCrud.revalidate();
		panelBtnCrud.repaint();
		
	}
	public void setPanelContent(JPanel contenido) {
		contenido.setBounds(0,
				panelBtnCrud.getHeight()
				, panelContenido.getWidth(),
				panelContenido.getHeight() - panelBtnCrud.getHeight());
	
		panelContenido.remove(panelBtnCrud);
		panelContenido.removeAll();
		
		panelContenido.add(panelBtnCrud);
		panelContenido.add(contenido);
		
		panelContenido.revalidate();
		panelContenido.repaint();
	}
	
	public void setPanelMenu(JPanel menu) {
		menu.setBounds(0,0, panelMenu.getWidth(), panelMenu.getHeight());
		panelMenu.removeAll();
		panelMenu.add(menu);
		panelMenu.revalidate();
		panelMenu.repaint();
		
	}
	
	public void setPanelTopBar(JPanel header) {
		header.setBounds(0,0, panelTopBar.getWidth(), panelTopBar.getHeight());
		panelTopBar.removeAll();
		panelTopBar.add(header);
		panelTopBar.revalidate();
		panelTopBar.repaint();	
	}
	
	public void setPanelBanner(JPanel banner) {
		banner.setBounds(0,0, panelBanner.getWidth(), panelBanner.getHeight());
		panelBanner.removeAll();
		panelBanner.add(banner);
		panelBanner.revalidate();
		panelBanner.repaint();
		
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
