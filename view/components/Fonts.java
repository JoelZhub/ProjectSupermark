package view.components;

import java.awt.Font;
import java.io.File;

public class Fonts {

	public static Font custom;
	public static Font bold;
	
	static {
		
		try {
		     custom = Font.createFont(
		        Font.TRUETYPE_FONT,
		        new File("resources/tipografias/Ubuntu/Ubuntu-Regular.ttf")
		    ).deriveFont(15f);
		   
		     bold = Font.createFont(
				        Font.TRUETYPE_FONT,
				        new File("resources/tipografias/Ubuntu/Ubuntu-Bold.ttf")
				    ).deriveFont(20f);
				   
			 
		
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
