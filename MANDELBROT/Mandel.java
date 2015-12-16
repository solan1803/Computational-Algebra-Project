import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Mandel implements Fractal {
	private final double CIRCLE_DIAMETER = 4.0;
	private final int width = 1920;
	private final int height = 1980;
	private final int maxIterations = 4016;
	private BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);
	private Complex z = new Complex(0, 0);

	/*private final int[] coloursHex = { 0xE68A2E, 0xCC7A29, 0xB26B24, 0x995C1F,
			0x804C1A, 0x663D14, 0x4C2E0F, 0x331F0A, 0x1A0F05, 0x000000,
			0x0000019, 0x000033, 0x00004c, 0x000066, 0x00007f, 0x000099,
			0x0000b2, 0x0000cc, 0x0000e5, 0x1919ff, 0x3232ff, 0x4c4cff,
			0x6666ff, 0x7f7fff, 0x9999ff, 0xb2b2ff, 0xccccff, 0xe5e5ff,
			0xffffff, 0xFFF5EB, 0xFFEBD6, 0xFFE0C2, 0xFFD6AD, 0xFFCC99,
			0xFFC285, 0xFFB870, 0xFFAD5C, 0xFF9933, 0xFFA347 };*/

	
	 private final int[] coloursHex = { 0x000000, 0x0000019, 0x000033,
	 0x00004c, 0x000066, 0x00007f, 0x000099, 0x0000b2, 0x0000cc, 0x0000e5,
	 0xffffff, 0xccccff, 0xb2b2ff, 0x9999ff, 0x7f7fff, 0x6666ff, 0x4c4cff,
	 0x3232ff, 0x1919ff };
	 
	// hex values of colours used to represent different iteration levels

	/*
	 * private int[] colours2 = { 0x57FEFF, 0xBCC7B9, 0xB93B8F, 0x50EBEC,
	 * 0x7A7D74, 0xF9B7FF, 0x46C7C7, 0xF660AB, 0xE6A9EC, 0x307D7E, 0xF665AB,
	 * 0xC38EC7, 0xAF7817, 0xE45E9D, 0x7E587E, 0xFBB117, 0xC25283, 0xADDCE3,
	 * 0xE8A317, 0x7D2252, 0x8E35EF, 0xC58917, 0x5E2217, 0x893BFF }; range of
	 * different colours
	 */

	public static void main(String[] args) throws Exception {
		Mandel mandeltest = new Mandel();
		mandeltest.createImage();
	}

	@Override
	public void nextIteration(Complex z, Complex constant) {
		double x_new = z.getReal() * z.getReal() - z.getImaginary()
				* z.getImaginary() + constant.getReal();
		z.setImaginary(2 * z.getReal() * z.getImaginary()
				+ constant.getImaginary());
		z.setReal(x_new);
		// the method performing the iterative process of z^2 + c
	}

	@Override
	public void createImage() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Complex c = new Complex((col - width / 2) * CIRCLE_DIAMETER
						/ width, (row - height / 2) * CIRCLE_DIAMETER / width);
				z.setReal(0);
				z.setImaginary(0);
				int iterations = 0;
				while (z.getModulus() < 2 && iterations < maxIterations) {
					nextIteration(z, c);
					iterations++;
				}
				// checks whether the modulus of the result is less than 2, and
				// carries on looping, assigning colours to different points
				// based on the amount of iterations before it escapes to
				// infinity
				if (iterations == maxIterations) {
					image.setRGB(col, row, 0x000000);
				} else {
					image.setRGB(col, row, coloursHex[(iterations+29)
							% coloursHex.length]);
				}
			}
		}
		try {
			// writes the image
			ImageIO.write(image, "png", new File("Mtest#7.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
