package calFrame;

import java.util.Stack;

public class FormulaEditor {
	
	public boolean check(String formula) {
		if(operatorOverlapped(formula)) return false;
		if(!whetherBucketUsedProperly(formula)) return false;
		return true;
	}
	
	private boolean whetherBucketUsedProperly(String formula) {
		Stack<String> stack = new Stack<String>();
		
		for(int i =0;i <formula.length();i++) {
			char c = formula.charAt(i);
			
			if(c == '(') {
				stack.push(""+c);
			}
			else if(c == ')') {
				if(!stack.isEmpty()) stack.pop();
				else return false;
			}
		}
		
		if(stack.isEmpty()) return true;
		else return false;
	}
	
	private boolean operatorOverlapped(String formula) {
		for(int i =0;i <formula.length();i++) {
			if(i==0) {
				 if(whetherOperator(formula.charAt(i))) {
					 if(formula.charAt(i) == '+');
					 else if(formula.charAt(i) == '-');
					 else if(formula.charAt(i) == '(');
					 else return true;
				 }else {
					 if(formula.charAt(i) == '.') return true;
				 }
			}
			else if(i == formula.length()-1) {
				if(whetherOperator(formula.charAt(i))) {
					 if(formula.charAt(i) == ')');
					 else return true;
				 }else {
					 if(formula.charAt(i) == '.') return true;
				 }
			}else {
				int p = i-1;
				int n = i+1;
				char prev = formula.charAt(p);
				char next = formula.charAt(n);
				
				if(formula.charAt(i) == '+') {
					if(next == '(');
					else if(next == '+');
					else if(next == '-');
					else if(!whetherOperator(next));
					else return true;
				}
				else if(formula.charAt(i) == '-') {
					if(next == '(');
					else if(next == '+');
					else if(next == '-');
					else if(!whetherOperator(next));
					else return true;
				}
				else if(formula.charAt(i) == '*') {
					if(prev == ')');
					else if(!whetherOperator(prev));
					else return true;
					
					if(next == '(');
					else if(next == '+');
					else if(next == '-');
					else if(!whetherOperator(next));
					else return true;
				}
				else if(formula.charAt(i) == '/') {
					if(prev == ')');
					else if(!whetherOperator(prev));
					else return true;
					
					if(next == '(');
					else if(next == '+');
					else if(next == '-');
					else if(!whetherOperator(next));
					else return true;
				}
				else if(formula.charAt(i) == '(') {
					if(!whetherOperator(prev)) return true;
					
					if(next == '(');
					else if(next == '+');
					else if(next == '-');
					else if(!whetherOperator(next));
					else return true;
				}
				else if(formula.charAt(i) == ')') {
					if(prev == ')');
					else if(!whetherOperator(prev));
					else return true;
					
					if(!whetherOperator(next)) return true;
				}
			}
		}
		return false;
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
