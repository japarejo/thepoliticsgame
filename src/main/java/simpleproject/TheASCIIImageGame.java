package simpleproject;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.github.lalyos.jfiglet.FigletFont;
import com.google.common.collect.Lists;

import io.korhner.asciimg.image.AsciiImgCache;
import io.korhner.asciimg.image.character_fit_strategy.ColorSquareErrorFitStrategy;
import io.korhner.asciimg.image.character_fit_strategy.StructuralSimilarityFitStrategy;
import io.korhner.asciimg.image.converter.AsciiToImageConverter;
import io.korhner.asciimg.image.converter.AsciiToStringConverter;

public class TheASCIIImageGame {
	public static void main(String[] args) {
		String itemType=loadAppItemType();
		Map<String, List<String>> alternatives = generateAlternatives(itemType);
		List<String> images = Lists.newArrayList(alternatives.keySet());
		List<Integer> sizes = Lists.newArrayList(30, 20, 10, 6, 3, 1);
		Random random = new Random();
		String imageName = images.get(random.nextInt(images.size()));
		BufferedImage image = loadImage(itemType+"/"+imageName);
		if (image != null) {
			Scanner scanner = new Scanner(System.in);
			String name = null;
			for (Integer size : sizes) {
				showImage(image, size);
				System.out
						.println("Please guess the name of the "
								+itemType+ " in the image:");
				name = scanner.nextLine();
				if (alternatives.get(imageName).contains(name)) {
					System.out.println(FigletFont
							.convertOneLine("You Win!  :-)"));
					System.out.println(FigletFont.convertOneLine("It is  "
							+ alternatives.get(imageName).get(0)));
					break;
				} else if (size != sizes.get(sizes.size() - 1)) {
					System.out.println(FigletFont
							.convertOneLine("Ouch! Try again!  :-S"));
				} else {
					System.out.println(FigletFont
							.convertOneLine("You loose! :'-("));
					System.out.println(FigletFont.convertOneLine("It is  "
							+ alternatives.get(imageName).get(0)));
					break;
				}
			}
		}

	}
	/**

	 * 
	 * @return map between image path and valid politic names.
	 */
	private static Map<String, List<String>> generateAlternatives(String itemType) {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		String dir="src.main.resources.";
		File f=new File(dir);
		if(!f.exists())
			dir="";
		Properties p=new Properties();
		try {
			p.load(TheASCIIImageGame.class.getClassLoader().getResourceAsStream(dir+itemType+".properties"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		List<String> values;
		for(Entry<Object,Object> property:p.entrySet())
		{
			values=Lists.newArrayList(property.getValue().toString().split(","));
			result.put(property.getKey().toString(),values);
		}				
		
		return result;
	}
	
	public static String loadAppItemType(){
		String dir="src.main.resources.";
		File f=new File(dir);
		if(!f.exists())
			dir="";
		Properties p=new Properties();
		try {
			p.load(TheASCIIImageGame.class.getClassLoader().getResourceAsStream(dir+"app.properties"));
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return p.getProperty("itemType");
	}

	public static BufferedImage loadImage(String file) { //
		String dir="src.main.resources.";
		File f=new File(dir);
		if(!f.exists())
			dir="";
		BufferedImage portraitImage = null;		
		try {
			//f=new File(dir+file);
			//System.out.println("Loading image  from:"+dir+file);
			portraitImage = ImageIO.read(TheASCIIImageGame.class.getClassLoader().getResourceAsStream(dir+file));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return portraitImage;
	}

	public static String showImage(BufferedImage img, int size) {

		// initialize cache
		AsciiImgCache cache = AsciiImgCache.create(new Font("Courier",
				Font.BOLD, size));

		// initialize converters
		AsciiToImageConverter imageConverter = new AsciiToImageConverter(cache,
				new ColorSquareErrorFitStrategy());
		AsciiToStringConverter stringConverter = new AsciiToStringConverter(
				cache, new StructuralSimilarityFitStrategy());

		// image output
		// ImageIO.write(imageConverter.convertImage(portraitImage), "png",
		// new File("ascii_art.png"));
		// string converter, output to console
		StringBuffer result = stringConverter.convertImage(img);
		System.out.println(result);
		return result.toString();
	}
}
