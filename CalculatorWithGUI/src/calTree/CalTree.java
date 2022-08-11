package calTree;

import java.util.Stack;
import java.util.Vector;

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
	
	public void infixToPostfix() {
		impl_infixToPostfix(root);
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
