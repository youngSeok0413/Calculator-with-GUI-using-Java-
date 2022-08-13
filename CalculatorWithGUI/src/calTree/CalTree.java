package calTree;

import java.util.Stack;
import java.util.Vector;

public class CalTree extends NTree{
	
	private String operand;
	private String errorMsg;
	
	public CalTree(){
		super();
		this.operand = new String();
		this.errorMsg = new String();
	}
	
	public String getErrorMsg() {
		return this.errorMsg;
	}
	
	public void clear() {
		root = new Node('f', (char)-1, (double)-1, false);
		location = root;
		operand = "";
		errorMsg = "";
	}
	
	public void upload(String input) {
		if(input.isEmpty())
			return;
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
	
	//sorting
	public void infixToPostfix() {
		impl_infixToPostfix(root);
	}
	
	
	//actually calculate
	public Double calculate(String str) {
		upload(str);
		infixToPostfix();
		impl_calculate(root);
		return root.getOperand();
	}
	
	private void impl_calculate(Node node) {
		if(node == null)
			return;
			
		if(node.getType()=='f') {
			if(!node.whetherConfirmed()) {
				if(!node.isEmpty()) {
					Stack<Double> stack = new Stack<Double>();
					for(int i = 0; i < node.size(); i++) {
						Node buffer = node.get(i);
						if(buffer.getType() == 'o') {
							stack.push(buffer.getOperand());
						}
						else if(buffer.getType() == 'm') {
							Double b = null;
							Double a = null;
							if(!stack.isEmpty()) {
								b = stack.pop();
							}
							if(!stack.isEmpty()) {
								a = stack.pop();
								stack.push(impl_twoOperandOperate(buffer.getOperator(), a, b));
							}
						}
						else if(buffer.getType() == 'f') {
							if(!buffer.whetherConfirmed())
								impl_calculate(buffer);
							stack.push(buffer.getOperand());
						}
					}
					
					if(stack.isEmpty()) {
						System.out.println("Error");
					}else {
						node.setOperand(stack.pop());
						node.setConfirmedTrue();
					}
				}
			}
		}
	}
	private double impl_twoOperandOperate(char operator, Double a, Double b) {
		switch(operator) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		case '/':
			if(b == 0) {
				return 0;
			}else {
				return a/b;
			}
		default: return 0;		
		}
	}
	
	private void impl_infixToPostfix(Node node) {
		if(node.isEmpty())
			return;
		else {
			Stack<Node> stack = new Stack<Node>();
			Vector<Node> postfix = new Vector<Node>();
			
			for(int i = 0; i < node.size(); i++) {
				Node buffer = node.get(i);
				if(buffer.getType() == 'o') {
					postfix.add(buffer);
				}else if(buffer.getType() == 'm') {
					
					if(buffer.getOperator() == '+') {
						while(!stack.isEmpty())
							postfix.add(stack.pop());
						stack.push(buffer);
					}
					
					else if(buffer.getOperator() == '-') {
						while(!stack.isEmpty())
							postfix.add(stack.pop());
						stack.push(buffer);
					}
					
					else if(buffer.getOperator() == '*') {
						if(stack.isEmpty())
							stack.push(buffer);
						else {
							if(stack.peek().getOperator() == '*') {
								while(!stack.isEmpty())
									postfix.add(stack.pop());
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '/') {
								while(!stack.isEmpty())
									postfix.add(stack.pop());
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '+') {
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '-') {
								stack.push(buffer);
							}
						}
					}
					else if(buffer.getOperator() == '/') {
						if(stack.isEmpty())
							stack.push(buffer);
						else {
							if(stack.peek().getOperator() == '*') {
								while(!stack.isEmpty())
									postfix.add(stack.pop());
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '/') {
								while(!stack.isEmpty())
									postfix.add(stack.pop());
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '+') {
								stack.push(buffer);
							}else if(stack.peek().getOperator() == '-') {
								stack.push(buffer);
							}
						}
					}
				}else if(buffer.getType() == 'f') {
					impl_infixToPostfix(buffer);
					postfix.add(buffer);
				}
			}
			
			while(!stack.isEmpty())
				postfix.add(stack.pop());
			
			node.setNextNode(postfix);
		}
	}
	
	private void stackForOperand(char c) {
		this.operand += new String(""+c);
	}
	
	private double parseDouble() {
		double operand = Double.parseDouble(this.operand);
		return operand;
	}
	
	private boolean whehterUseDotOne() {
		int n = 0;
		for(int i=0; i < operand.length(); i++) {
			if(operand.charAt(i) == '.') n++;
			if(n > 1) {
				this.errorMsg = "ERROR!";
				return false;}
		}
		
		return true;
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
