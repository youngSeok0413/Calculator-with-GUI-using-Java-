package calTree;

import java.util.Vector;
import debugger.PrintItself;

public class Node {
	private char type;  // 'f' : formula, 'o' : operand, 'm' : operator(method)
	private char operator;
	private double operand;
	private boolean confirmed;
	
	public Node prevNode;
	private Vector<Node> nextNode;
	
	public Node(char type, char operator, double oeprand, boolean confirmed) {
		setType(type);
		setOperator(operator);
		setOperand(oeprand);
		if(confirmed)
			setConfirmedTrue();
		else
			setConfirmedFalse();
		this.nextNode = new Vector<Node>();
	}
//set
	public void setType(char type) {
		this.type = type;
	}
	public void setOperator(char operator){
		this.operator = operator;
	}
	public void setOperand(double operand) {
		this.operand = operand;
	}
	public void setConfirmedTrue(){
		this.confirmed = true;
	}
	public void setConfirmedFalse(){
		this.confirmed = false;
	}
	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}
	public void setNextNode(Vector<Node> next) {
		this.nextNode = next;
	}
//get
	public char getType() {
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
	
	public Node getPrevNode() {
		return this.prevNode;
	}
	public Vector<Node> getNext(){
		return this.nextNode; // deep copy
	}
	
	public Node get(int i){
		if(i < size())
			return this.nextNode.get(i); // just component
		else 
			return new Node((char)0, (char)0, (double)0, true);
	}
	public int size() {
		return this.nextNode.size();
	}
	public boolean isEmpty() {
		return this.nextNode.isEmpty();
	}
	
	public void add(Node node) {
		this.nextNode.add(node);
	}
	
	public String toString() {
		return new String("type : " +type+ " operator : " 
	+ operator+" operand : "+operand+" confirmed : " + confirmed);
	}
}
