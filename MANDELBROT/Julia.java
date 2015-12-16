import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;

public class Julia implements Fractal {
	private double neg_real = -1.5, neg_im = -1.5, pos_real = 1.5,
			pos_im = 1.5;
	// Region of complex plane
	
	private int width = 1000, height = 1000; // Mapped to these pixels
	private final int MAX_ITERATIONS = 4000;
	
	private Complex c = new Complex(0, 0);
	// This complex constant defines the set we display
	
	private BufferedImage image; // The image we compute

	private final int[] colours = { 0x000000, 0x0000019, 0x000033, 0x00004c,
			0x000066, 0x00007f, 0x000099, 0x0000b2, 0x0000cc, 0x0000e5,
			0x1919ff, 0x3232ff, 0x4c4cff, 0x6666ff, 0x7f7fff, 0x9999ff,
			0xb2b2ff, 0xccccff, 0xe5e5ff, 0xffffff, 0xe5e5ff, 0xccccff,
			0xb2b2ff, 0x9999ff, 0x7f7fff, 0x6666ff, 0x4c4cff, 0x3232ff,
			0x1919ff, 0x0000e5, 0x0000cc, 0x0000b2, 0x000099, 0x00007f,
			0x000066, 0x00004c, 0x000033, 0x0000019 };

	public Julia(double c_real, double c_imaginary) throws IOException {
		c.setReal(c_real);
		c.setImaginary(c_imaginary);
	}

	@Override
	public void createImage() {

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int i, j;
		Complex z = new Complex(0, 0);
		double distancex = (pos_real - neg_real) / width;
		double distancey = (pos_im - neg_im) / height;
		z.setImaginary(neg_im);
		//visits all the pixels individually using two for loops
		for (j = 0; j < height; j++) {
			z.setImaginary(z.getImaginary() + distancey);
			z.setReal(neg_real);
			for (i = 0; i < width; i++) {
				z.setReal(z.getReal() + distancex);
				image.setRGB(width - 1 - i, j, colours[testPoint(z)]);
				//assigns pixel its designated colour
			}

		}
		try {
			ImageIO.write(image, "png", new File("julia0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// This is the key method for computing Julia sets. For each point z
	// in the complex plane, we repeatedly compute z = z*z + c using complex
	// arithmetic. We stop iterating when the magnitude of z exceeds 2 or
	// after the limit of iterations.
	public int testPoint(Complex z) {
		Complex zclone = new Complex(z.getReal(), z.getImaginary());
		for (int i = 0; i <= MAX_ITERATIONS; i++) {
			// Compute z = z*z + c;
			nextIteration(zclone, c);
			// Check magnitude of z and return iteration number
			if (zclone.getModulus() > 4)
				return i % colours.length;
		}
		return colours.length - 1;
	}

	public static void main(String[] args) throws IOException {
		Fractal juliatest = new Julia(0, 0);
		juliatest.createImage();
		// also done for (0.4,0.65),(0.285,0.01)
	}

	@Override
	public void nextIteration(Complex z, Complex constant) {
		double oldreal = z.getReal();
		double oldimaginary = z.getImaginary();
		z.setReal(oldreal * oldreal - oldimaginary * oldimaginary
				+ constant.getReal());
		z.setImaginary((2 * oldreal * oldimaginary) + constant.getImaginary());
	}

}
