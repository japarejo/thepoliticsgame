import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import simpleproject.TheASCIIImageGame;

public class ThePoliticsGameTest {

	@Test
	public void showImageTest() {
		String dir="src.main.resources.";
		File f=new File(dir);
		if(!f.exists())
			dir="";
		BufferedImage img = null;
		
		try {
			//f=new File(dir+file);
			//System.out.println("Loading image  from:"+f.getAbsolutePath());
			img = ImageIO.read(TheASCIIImageGame.class.getClassLoader().getResourceAsStream(dir+"Zapatero.jpg"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String asciimage=TheASCIIImageGame.showImage(img, 10);
		assertFalse(asciimage==null);
		assertFalse(asciimage.equals(""));
	}

}
