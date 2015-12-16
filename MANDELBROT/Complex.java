public class Complex {

	private double real;
	private double imaginary;
	private double modulus;

	public Complex(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
		setModulus();
	}

	public void setModulus() {
		modulus = Math.sqrt(real * real + imaginary * imaginary);
	}

	public double getReal() {
		return real;
	}

	public double getImaginary() {
		return imaginary;
	}

	public double getModulus() {
		return modulus;
	}

	public void setReal(double real) {
		this.real = real;
		setModulus();
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
		setModulus();
	}
	
	public double getAngle() {
		return Math.atan(real/imaginary);
	}


	public void nextIterationZcube(Complex constant) {
		double oldreal = real;
		double oldimaginary = imaginary;
		setReal(oldreal * oldreal * oldreal - 3 * oldreal * oldimaginary
				* oldimaginary + constant.getReal());
		setImaginary(3 * oldreal * oldreal * oldimaginary - oldimaginary
				* oldimaginary * oldimaginary + constant.getImaginary());
	}

}
