package calculator;

public enum OperatorType {
	PLUS('+'),
	MINUS('-'),
	MULTIPLE('*'),
	DIVIDE('/'),
	OPENBUCKET('('),
	CLOSEBUCKET(')');
	
	private final char value;
	
	private OperatorType(char value) {
		this.value = value;
	}
	
	public char getValue() {
		return value;
	}
}
