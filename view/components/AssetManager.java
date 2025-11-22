package view.components;
import javax.swing.ImageIcon;
import java.awt.Image;
public class AssetManager {
	private static final String BASE = "/resources/img/";	
	public static ImageIcon icon(String name, int w, int h) {
		ImageIcon raw = new ImageIcon(AssetManager.class.getResource(BASE + name));
		Image img = raw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
		
	}
}
