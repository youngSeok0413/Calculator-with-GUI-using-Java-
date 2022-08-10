package calculator;

public class DataSet {
	private TYPE type;
	private char operator;
	private double operand;
	private boolean confirmed;
	
	public DataSet(TYPE type, char operator, double operand, boolean confirmed) {
		setType(type);
		setOperator(operator);
		setOperand(operand);
		setConfirmed(confirmed);
	}
	
	public void setType(TYPE type) {
		this.type = type;
	}
	public void setOperator(char operator) {
		this.operator = operator;
	}
	public void setOperand(double operand) {
		this.operand = operand;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	public TYPE getType() {
		return this.type;
	}
	public char getOperator() {
		return this.operator;
	}
	public double getOperand() {
		return this.operand;
	}
	public boolean whetherConfirmed() {
		return this.confirmed;
	}	
}
