package calculator;

import java.util.Vector;

public class Node {
	private DataSet data;
	private Node prev;
	private Vector<Node> next;
	
	public Node(DataSet data, Node prev) {
		this.data = data;
		this.prev = prev;
		this.next = new Vector<Node>();
	}
}
