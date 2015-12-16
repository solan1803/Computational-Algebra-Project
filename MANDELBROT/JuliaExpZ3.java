import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class JuliaExpZ3 implements Fractal {
	// These constants are hard-coded for simplicity
	double neg_real = -1.5, neg_im = -1.5, pos_real = 1.5, pos_im = 1.5; // Region
																			// of
																			// complex
																			// plane

	int width = 1000, height = 1000; // Mapped to these pixels

	Complex c = new Complex(0, 0);// This complex constant defines the set we
									// display

	BufferedImage image; // The image we compute

	static int[] colours = { 0x000000, 0x0000019, 0x000033, 0x00004c, 0x000066,
			0x00007f, 0x000099, 0x0000b2, 0x0000cc, 0x0000e5, 0xffffff,
			0xe5e5ff, 0xccccff, 0xb2b2ff, 0x9999ff, 0x7f7fff, 0x6666ff,
			0x4c4cff, 0x3232ff, 0x1919ff };

	private int[] colours2 = { 0x00FFFF, 0xDEEBDC, 0x7F525D, 0x57FEFF,
			0xBCC7B9, 0xB93B8F, 0x50EBEC, 0x7A7D74, 0xF9B7FF, 0x46C7C7,
			0xF660AB, 0xE6A9EC, 0x307D7E, 0xF665AB, 0xC38EC7, 0xAF7817,
			0xE45E9D, 0x7E587E, 0xFBB117, 0xC25283, 0xADDCE3, 0xE8A317,
			0x7D2252, 0x8E35EF, 0xC58917, 0x5E2217, 0x893BFF };

	public JuliaExpZ3(double c_real, double c_imaginary) throws IOException {
		c.setReal(c_real);
		c.setImaginary(c_imaginary);
		// setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void createImage() {

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int i, j;
		Complex z = new Complex(0, 0);
		double distancex = (pos_real - neg_real) / width;
		double distancey = (pos_im - neg_im) / height;
		z.setImaginary(neg_im);
		for (j = 0; j < height; j++) {
			z.setImaginary(z.getImaginary() + distancey);
			z.setReal(neg_real);
			for (i = 0; i < width; i++) {
				z.setReal(z.getReal() + distancex);
				image.setRGB(i, j, colours2[testPoint(z)]);
			}

		}
		try {
			ImageIO.write(image, "png", new File("juliaExpZ3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// This is the key method for computing Julia sets. For each point z
	// in the complex plane, we repeatedly compute z = z*z + c using complex
	// arithmetic. We stop iterating when the magnitude of z exceeds 2 or
	// after the limit of iterations. We return the number of iterations-1.
	public int testPoint(Complex z) {
		Complex zclone = new Complex(z.getReal(), z.getImaginary());
		for (int i = 0; i < 40 * colours.length; i++) {
			// Compute z = z*z + c;
			nextIteration(zclone, c);
			// Check magnitude of z and return iteration number
			if (zclone.getModulus() > 4)
				return i % colours2.length;
		}
		return colours2.length - 1;
	}

	public static void main(String[] args) throws IOException {
		Fractal juliatest = new JuliaExpZ3(-0.621,0);
		juliatest.createImage();
	}

	@Override
	public void nextIteration(Complex z, Complex constant) {
		double r = z.getReal();
		double i = z.getImaginary();
		z.setReal(Math.exp(r * r * r - 3 * r * i * i)
				* Math.cos(3 * r * r * i - i * i * i) + constant.getReal());
		z.setImaginary(Math.exp(r * r * r - 3 * r * i * i)
				* Math.sin(3 * r * r * i - i * i * i) + constant.getImaginary());
	}

}
