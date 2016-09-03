import static org.junit.Assert.*;

import java.awt.image.BufferedImage;

import org.junit.Test;

import simpleproject.ThePoliticsGame;

public class ThePoliticsGameTest {

	@Test
	public void showImageTest() {
		BufferedImage img=ThePoliticsGame.loadImage("src/test/resources/Zapatero.jpg");
		String asciimage=ThePoliticsGame.showImage(img, 10);
		assertFalse(asciimage==null);
		assertFalse(asciimage.equals(""));
	}

}
