package simpleproject;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

public class ThePoliticsGame {
	public static void main(String[] args) {
		Map<String, List<String>> alternatives = generateAlternatives();
		List<String> images = Lists.newArrayList(alternatives.keySet());
		List<Integer> sizes = Lists.newArrayList(30, 20, 10, 6, 3, 1);
		Random random = new Random();
		String imageName = images.get(random.nextInt(images.size()));
		BufferedImage image = loadImage(imageName);
		if (image != null) {
			Scanner scanner = new Scanner(System.in);
			String name = null;
			for (Integer size : sizes) {
				showImage(image, size);
				System.out
						.println("Please guess the name of the Spanish politician in the image:");
				name = scanner.nextLine();
				if (alternatives.get(imageName).contains(name)) {
					System.out.println(FigletFont
							.convertOneLine("You Win!  :-)"));
					System.out.println(FigletFont.convertOneLine("He is  "
							+ alternatives.get(imageName).get(0)));
					break;
				} else if (size != sizes.get(sizes.size() - 1)) {
					System.out.println(FigletFont
							.convertOneLine("Ouch! Try again!  :-S"));
				} else {
					System.out.println(FigletFont
							.convertOneLine("You loose! :'-("));
					System.out.println(FigletFont.convertOneLine("He is  "
							+ alternatives.get(imageName).get(0)));
					break;
				}
			}
		}

	}

	private static Map<String, List<String>> generateAlternatives() {
		Map<String, List<String>> result = new HashMap<String, List<String>>();
		result.put("src/main/resources/AlbertRivera.jpg", Lists.newArrayList(
				"Albert Rivera", "albert rivera", "Rivera", "rivera"));
		result.put("src/main/resources/MarianoRajoy.jpg", Lists.newArrayList(
				"Mariano Rajoy", "mariano rajoy", "rajoy", "Rajoy"));
		result.put("src/main/resources/PabloIglesias.jpg", Lists.newArrayList(
				"Pablo Iglesias", "pablo iglesias", "Iglesias", "iglesias"));
		result.put("src/main/resources/PedroSanchez.jpg", Lists.newArrayList(
				"Pedro Sanchez", "pedro sanchez", "Pedro Sánchez",
				"pedro sánchez", "sanchez", "sánchez", "Sánchez", "Sanchez"));
		result.put("src/main/resources/AlbertoGarzon.jpg", Lists.newArrayList(
				"Alberto Garzón", "alberto garzón", "Alberto Garzon",
				"alberto garzon", "garzon", "garzón", "Garzón", "Garzon"));
		result.put("src/main/resources/FelipeGonzalez.jpg", Lists.newArrayList(
				"Felipe Gonzalez", "felipe gonzalez", "Felipe González",
				"felipe gonzález", "gozalez", "Gonzalez"));
		result.put("src/main/resources/JoseMariaAznar.jpg", Lists.newArrayList(
				"Jose Maria Aznar", "José María Aznar", "jose maria aznar",
				"josé maría aznar", "aznar", "Aznar"));
		result.put("src/main/resources/zapatero.jpg",
				Lists.newArrayList("Zapatero", "zapatero"));
		result.put("src/main/resources/Anguita.jpg", Lists.newArrayList(
				"Julio Anguita", "julio anguita", "anguita", "Anguita"));

		return result;
	}

	public static BufferedImage loadImage(String file) { //
		BufferedImage portraitImage = null;
		try {
			portraitImage = ImageIO.read(new File(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
