package calculator;

public enum TYPE {
	OPERAND(0),
	OPERATOR(1),
	FORMULA(2);
	
	private final int value;
	
	TYPE(int value) {
		this.value = value;
	}
	
	public int getValue() {return value;}
}
