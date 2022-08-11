package calTree;

public class CalTree extends NTree{
	
	private String operand;
	
	public CalTree(){
		super();
		this.operand = new String();
	}
	
	public void upload(String input) {
		for(int i =0; i< input.length(); i++) {
			char c = input.charAt(i);
			if(whetherOperator(c)) {
				if(c == '(')
					addOpenBucket();
				else if(c == ')')
					addCloseBucket();
				else
					addOperator(c);
			}else {
				int next = i+1;
				if(next == input.length()) {
					stackForOperand(c);
					addOperand(parseDouble());
					operand = "";
				}
				else {
					if(whetherOperator(input.charAt(next))) {
						stackForOperand(c);
						addOperand(parseDouble());
						operand = "";
					}
					else
						stackForOperand(c);
				}
			}
		}
	}
	
	private void stackForOperand(char c) {
		this.operand += new String(""+c);
	}
	
	private double parseDouble() {
		double operand = Double.parseDouble(this.operand);
		return operand;
	}
	
	private boolean whetherOperator(char c) {
		switch(c) {
		case '+' : return true;
		case '-' : return true;
		case '*' : return true;
		case '/' : return true;
		case '(' : return true;
		case ')' : return true;
		default: return false;
		}
	}
}
