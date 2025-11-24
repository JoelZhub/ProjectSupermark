package view.forms.dashboard;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.components.BtnGroupCrud;
import view.components.FrameDragger;
import view.modules.admin.DashboardAdmin;
import view.modules.billing.BillingModule;
import view.modules.products.ProductsModule;
import view.modules.sales.SalesModule;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import navigation.NavigationManager;
import javax.swing.UIManager;

public class Dahsboard extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelMenu, panelFondo,panelTopBar, panelBanner,  panelContenido;

	private NavigationManager navigation;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void setChangeState(NavigationManager navigation) {
		
		this.navigation = navigation;
	}
	
	public NavigationManager getNavigation() {
		return this.navigation;
	}
	public JPanel getPanelContenido() {
		return this.panelContenido;
		
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
		panelFondo.setOpaque(true);
		panelFondo.setBounds(0, 0, 1400, 700);
		getContentPane().add(panelFondo);
		panelFondo.setLayout(null);

		panelMenu = new JPanel();
		panelMenu.setLayout(null);
		//panelMenu.setOpaque(true);
		panelMenu.setBounds(0, 0, 277, 700);
		panelMenu.setBackground(Color.white);
		panelFondo.add(panelMenu);
		
		panelContenido = new JPanel();
		panelContenido.setLayout(null);
		//panelContenido.setOpaque(true);
		panelContenido.setBounds(300, 210, 1090, 460);
		panelContenido.setBackground(null);
		panelFondo.add(panelContenido);
			

		panelTopBar = new JPanel();
		panelTopBar.setLayout(null);
		//panelTopBar.setOpaque(true);
		panelTopBar.setBackground(Color.white);
		panelTopBar.setBounds(287, 10, 1103, 35);
		panelFondo.add(panelTopBar);
		
		panelBanner = new JPanel();
		//panelBanner.setOpaque(true);
		panelBanner.setLayout(null);
		panelBanner.setBackground(Color.white);
		panelBanner.setBounds(298, 55, 1030, 129);
		panelFondo.add(panelBanner);

//		
//		panelBtnCrud = new JPanel();
//		panelBtnCrud.setLayout(null);
////		panelBtnCrud.setOpaque(true);
//		panelBtnCrud.setBackground(Color.white);
//		panelBtnCrud.setBounds(10, 150, 1043, 36);
//		panelContenido.add(panelBtnCrud);

		
		applyLightMode();
		inicializarComponentes();
	
	}

	
	public void inicializarComponentes() {
		setPanelMenu(new MenuPanel(navigation));
		setPanelTopBar(new TopBarPanel(this));
		setPanelBanner(new BannerPanel());
		setPanelContent(new BillingModule());
		
		/// configurar en base al rol
		//setPanelContent(new DashboardAdmin());
		//configurar btns en base al table schema
//		setPanelBtnCrud(new BtnGroupCrud());
		
	}
	
	
//	public void setPanelBtnCrud(JPanel btns) {
//		btns.setBounds(0,0, panelBtnCrud.getWidth(), panelBtnCrud.getHeight());
//		panelBtnCrud.removeAll();
//		panelBtnCrud.add(btns);
//		panelBtnCrud.revalidate();
//		panelBtnCrud.repaint();
//		
//	}
	public void setPanelContent(JPanel contenido) {
		contenido.setBounds(0,0,
				panelContenido.getWidth(),
				panelContenido.getHeight());
	
		//panelContenido.remove(panelBtnCrud);
		panelContenido.removeAll();
		
		//panelContenido.add(panelBtnCrud);
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


	public void applyDarkMode() {
		
	panelFondo.setBackground(UIManager.getColor("Panel.background"));
	panelMenu.setBackground(UIManager.getColor("Panel.background"));
	panelTopBar.setBackground(UIManager.getColor("Panel.background"));
	panelBanner.setBackground(UIManager.getColor("Panel.background"));
	panelContenido.setBackground(UIManager.getColor("Panel.background"));
//	panelBtnCrud.setBackground(UIManager.getColor("Panel.background"));
	
	repaint();
	}

	
	public void applyLightMode() {
		
		Color blanco = Color.WHITE;

	    panelFondo.setBackground(blanco);
	    panelMenu.setBackground(blanco);
	    panelTopBar.setBackground(blanco);
	    panelBanner.setBackground(blanco);
	    panelContenido.setBackground(blanco);
//	    panelBtnCrud.setBackground(blanco);


	    repaint();
	}
}
