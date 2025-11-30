package view.dashboard;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.AplicationContext;
import view.components.FrameDragger;
import view.components.Messages;
import view.modules.admin.DashboardAdmin;
import view.modules.billing.BillingModule;
import view.modules.custormerService.customerService;
import view.modules.inventory.InventoryModule;
import view.modules.sales.SalesModule;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import session.SessionContext;
import model.Rol;

public class Dahsboard extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelMenu, panelFondo,panelTopBar, panelBanner,  panelContenido;

	private final AplicationContext context;
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
	
	public  Dahsboard(AplicationContext context) {

		this.context = context;
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources\\img\\iconApp.png"));
		setResizable(false);
		setUndecorated(true);
		setBounds(100, 100, 1400, 700);	
		new FrameDragger(this);
	
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
		panelMenu.setBounds(0, 0, 277, 700);
		panelMenu.setBackground(Color.white);
		panelFondo.add(panelMenu);
		
		panelContenido = new JPanel();
		panelContenido.setLayout(null);
		panelContenido.setBounds(300, 210, 1090, 460);
		panelContenido.setBackground(null);
		panelFondo.add(panelContenido);
			
		panelTopBar = new JPanel();
		panelTopBar.setLayout(null);
		panelTopBar.setBackground(Color.white);
		panelTopBar.setBounds(287, 10, 1103, 35);
		panelFondo.add(panelTopBar);
		
		panelBanner = new JPanel();
		panelBanner.setLayout(null);
		panelBanner.setBackground(Color.white);
		panelBanner.setBounds(298, 55, 1030, 129);
		panelFondo.add(panelBanner);
	
		inicializarComponentes();
	
	}
	
	
	public AplicationContext getContext() {
		return context;
	}
	
	public void inicializarComponentes() {
		setPanelMenu(new MenuPanel(context, this));
		setPanelTopBar(new TopBarPanel(this, context));
		setPanelBanner(new BannerPanel());	
	if(SessionContext.get() != null) {
			if(SessionContext.get().getRolUsuarioLogueado() == Rol.ENCARGADO_INVENTARIO) {
				setPanelContent(new InventoryModule(this, context));
			}else if(SessionContext.get().getRolUsuarioLogueado() == Rol.CAJERO) {
				setPanelContent(new BillingModule(context, this));
				
			}else if(SessionContext.get().getRolUsuarioLogueado() == Rol.ADMIN) {
				setPanelContent(new DashboardAdmin(context, this));
				
			}else if(SessionContext.get().getRolUsuarioLogueado() == Rol.VENDEDOR) {
				setPanelContent(new SalesModule(context, this));
			}
			else if(SessionContext.get().getRolUsuarioLogueado() == Rol.SERVICIO_CLIENTE) {
				setPanelContent(new customerService(context,this));
			}
			else {
				new Messages(this, "Rol no encontrado").messageError();
				setPanelContent(null);
			}
	}
			
	}
	
	public void setPanelContent(JPanel contenido) {
		contenido.setBounds(0,0,
				panelContenido.getWidth(),
				panelContenido.getHeight());
		panelContenido.removeAll();
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
