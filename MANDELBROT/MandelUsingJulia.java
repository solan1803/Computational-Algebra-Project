import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MandelUsingJulia implements Fractal {
	// These constants are hard-coded for simplicity
		double neg_real = -2, neg_im = -2, pos_real = 1.5, pos_im = 2; // Region
																				// of
																				// complex
																				// plane

		int width = 3000, height = 3000; // Mapped to these pixels

		Complex z = new Complex(0, 0);// This complex constant defines the set we
										// display

		BufferedImage image; // The image we compute

		static int[] colours = { 0x000000, 0x0000019, 0x000033, 0x00004c, 0x000066,
				0x00007f, 0x000099, 0x0000b2, 0x0000cc, 0x0000e5, 0xffffff,
				0xe5e5ff, 0xccccff, 0xb2b2ff, 0x9999ff, 0x7f7fff, 0x6666ff,
				0x4c4cff, 0x3232ff, 0x1919ff };

		@Override
		public void createImage() {

			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

			int i, j;
			Complex c = new Complex(0, 0);
			double distancex = (pos_real - neg_real) / width;
			double distancey = (pos_im - neg_im) / height;
			c.setImaginary(neg_im);
			for (j = 0; j < height; j++) {
				c.setImaginary(c.getImaginary() + distancey);
				c.setReal(neg_real);
				for (i = 0; i < width; i++) {
					c.setReal(c.getReal() + distancex);
					image.setRGB(i, j, colours[testPoint(c)]);
				}

			}
			try {
				ImageIO.write(image, "png", new File("MandelUsingJulia.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		// This is the key method for computing Julia sets. For each point z
		// in the complex plane, we repeatedly compute z = z*z + c using complex
		// arithmetic. We stop iterating when the magnitude of z exceeds 2 or
		// after the limit of iterations. We return the number of iterations-1.
		public int testPoint(Complex c) {
			//Complex cClone = new Complex(c.getReal(), c.getImaginary());
			z.setReal(0);
			z.setImaginary(0);
			for (int i = 0; i < 50 * colours.length; i++) {
				// Compute z = z*z + c;
				nextIteration(z, c);
				// Check magnitude of z and return iteration number
				if (z.getModulus() > 4)
					return i % colours.length;
			}
			return colours.length - 1;
		}

		public static void main(String[] args) throws IOException {
			MandelUsingJulia test = new MandelUsingJulia();
			test.createImage();
		}

		@Override
		public void nextIteration(Complex z, Complex constant) {
			double x_new = z.getReal() * z.getReal() - z.getImaginary()
					* z.getImaginary() + constant.getReal();
			z.setImaginary(2 * z.getReal() * z.getImaginary()
					+ constant.getImaginary());
			z.setReal(x_new);
		}
}
