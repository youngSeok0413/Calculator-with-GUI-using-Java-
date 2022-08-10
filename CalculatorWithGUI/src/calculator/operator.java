package calculator;

public class operator {
	public double plus(double a, double b) {
		return a + b;
	}

	public double minus(double a, double b) {
		return a - b;
	}

	public double multiple(double a, double b) {
		return a * b;
	}

	public double divide(double a, double b) {
		if(b == 0) {
			return 0;
		}else {
			return a/b;
		}
	}
}
