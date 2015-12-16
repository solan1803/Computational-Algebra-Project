import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class JuliaUsingMandel implements Fractal {
	private final int[] coloursHex = { 0x000000, 0x0000019, 0x000033, 0x00004c,
			0x000066, 0x00007f, 0x000099, 0x0000b2, 0x0000cc, 0x0000e5,
			0xffffff, 0xe5e5ff, 0xccccff, 0xb2b2ff, 0x9999ff, 0x7f7fff,
			0x6666ff, 0x4c4cff, 0x3232ff, 0x1919ff };

	int width = 1000, height = 1000, maxIterations = 1000;

	BufferedImage image = new BufferedImage(width, height,
			BufferedImage.TYPE_INT_RGB);

	Complex c = new Complex(0, 0);

	public static void main(String[] args) throws Exception {
		JuliaUsingMandel test = new JuliaUsingMandel(0.285, 0.01);
		test.createImage();
	}

	public JuliaUsingMandel(double c_real, double c_imaginary)
			throws IOException {
		c.setReal(c_real);
		c.setImaginary(c_imaginary);
		// setPreferredSize(new Dimension(width, height));
	}

	@Override
	public void nextIteration(Complex z, Complex constant) {
		double x_new = z.getReal() * z.getReal() - z.getImaginary()
				* z.getImaginary() + constant.getReal();
		z.setImaginary(2 * z.getReal() * z.getImaginary()
				+ constant.getImaginary());
		z.setReal(x_new);

	}

	@Override
	public void createImage() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				Complex z = new Complex((col - width / 2) * 4.0 / width,
						(row - height / 2) * 4.0 / width);
				// z.setReal(0);
				// z.setImaginary(0);
				int iterations = 0;
				while (z.getModulus() < 2 && iterations < maxIterations) {
					nextIteration(z, c);
					iterations++;
				}
				/*
				 * if (iterations == maxIterations){ image.setRGB(col, row,
				 * coloursHex[0]); } else { image.setRGB(col, row,
				 * coloursHex[10]); }
				 */
				image.setRGB(width - col - 1, row, coloursHex[iterations
						% coloursHex.length]);
			}
		}
		try {
			ImageIO.write(image, "png", new File("JuliaUsingMandel2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
