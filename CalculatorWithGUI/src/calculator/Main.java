package calculator;

import calTree.*;

public class Main {

	public static void main(String[] args) {
		CalTree cal = new CalTree();
		cal.upload("3*((2-1)*10)");
		cal.printAll();
		System.out.println();
		cal.infixToPostfix();
		cal.printAll();
	}

}
