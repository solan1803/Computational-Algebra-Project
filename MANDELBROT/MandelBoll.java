public class MandelBoll {

	public static final int NUM_OF_EPSILONS = 8;
	public static final int EPSILON = 1;

	public MandelBoll(double c_real, double c_imaginary, boolean EPSILON_IN_REAL) {

		for (int i = 0; i < NUM_OF_EPSILONS; i++) {
			Complex c = new Complex(0, 0);
			int iterations = 0;
			if (!EPSILON_IN_REAL) {
				c.setImaginary(c_imaginary + EPSILON * Math.pow(2, -(i * 5)));
				c.setReal(c_real);
			} else if (EPSILON_IN_REAL) {
				c.setReal(c_real + EPSILON * Math.pow(2, -(i * 5)));
				c.setImaginary(c_imaginary);
			}
			Complex z = new Complex(0, 0);
			while (z.getModulus() < 2) {
				nextIteration(c, z);
				iterations++;
			}
			System.out.println(iterations);
		}
	}

	public MandelBoll() {
		// extra case (-1.25 - E^2, E)
		for (int i = 0; i < NUM_OF_EPSILONS; i++) {
			int iterations = 0;
			Complex c = new Complex(-1.25
					- (Math.pow(EPSILON * Math.pow(10, -i), 2)), EPSILON
					* Math.pow(10, -i));
			Complex z = new Complex(0, 0);
			while (z.getModulus() < 2) {
				nextIteration(c, z);
				iterations++;
			}
			System.out.println(iterations);
		}
	}

	public void nextIteration(Complex c, Complex z) {
		double x_new = z.getReal() * z.getReal() - z.getImaginary()
				* z.getImaginary() + c.getReal();
		z.setImaginary(2 * z.getReal() * z.getImaginary() + c.getImaginary());
		z.setReal(x_new);
	}

	public static void main(String[] args) {
		// MandelBoll test = new MandelBoll(0.25, 0, true);
		// MandelBoll test = new MandelBoll(-0.75, 0, false);
		MandelBoll test = new MandelBoll(); // special case see code
	}

}
