package view.components;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Messages {

	//gestion de mesanjes dentro de la app -> mensajes de alerta, advertencia, etc
	//si requiere otro tipo de mensaje favor agregar el mismo siguiendo la logica de los demas
	
	
	private static  String message = "";
	private JFrame frame;
	private JDialog dialog;
	
	public Messages(JFrame frame, String message) {
		this.message = message;
		this.frame = frame;
		dialog = new JDialog(frame, true);
		this.dialog.setSize(400, 160);
		this.dialog.setLocationRelativeTo(frame);
		this.dialog.setResizable(false);
	}
	
	public  void messageAlert() {
		builderDialog(new Color(125, 201, 149));
	}
	public  void messageWarning() {
		builderDialog(new Color(252, 233, 71));
	}
	public  void messageError() {
		builderDialog(new Color(222, 56, 55));
	}
	
	public void builderDialog(Color bg) {
		
        JPanel panel = new JPanel();
        panel.setBackground(bg);
        panel.setLayout(new BorderLayout(10, 10));

        JLabel lbl = new JLabel(message, SwingConstants.CENTER);
        lbl.setForeground(Color.WHITE);
        lbl.setFont(Fonts.bold);
        
        JButton btn = new JButton("OK");
        btn.setFont(Fonts.custom);
        BtnStyle.flat(btn);
        btn.setForeground(Color.white);
        btn.addActionListener(e -> dialog.dispose());

        panel.add(lbl, BorderLayout.CENTER);
        panel.add(btn, BorderLayout.SOUTH);
        
        dialog.setModal(true);
        dialog.setContentPane(panel);
        dialog.setVisible(true);
	}
	
}
