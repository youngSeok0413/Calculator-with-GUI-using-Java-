package calculator;

import calTree.*;

public class Main {

	public static void main(String[] args) {
		CalTree cal = new CalTree();
		cal.upload("1+2-(3/45)");
		cal.printAll();
	}

}
