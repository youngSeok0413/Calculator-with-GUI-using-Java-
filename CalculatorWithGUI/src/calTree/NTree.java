package calTree;

public class NTree {
	protected final Node root;
	Node location;
	
	public NTree() {
		root = new Node('f', (char)-1, (double)-1, false);
		location = root;
	}
	
	public void addOperand(double data) {
		Node toAdd = new Node('o', (char)-1, data, true);
		toAdd.setPrevNode(location);
		location.add(toAdd);
	}
	public void addOperator(char operator) {
		Node toAdd = new Node('m', operator, (double)-1, true);
		toAdd.setPrevNode(location);
		location.add(toAdd);
	}
	public void addOpenBucket() {
		Node toAdd = new Node('f', (char)-1, (double)-1, false);
		toAdd.setPrevNode(location);
		location.add(toAdd);
		location = location.get(location.size()-1);
	}
	public void addCloseBucket() {
		location = location.prevNode;
	}
	
	public void toRoot() {
		this.location = root;
	}
	
	public void printAll() {
		print(root);
	}
	private void print(Node nd) {
		if(nd.isEmpty()) {
			System.out.println(nd);
		}else {
			for(int i=0; i < nd.size(); i++) {
				print(nd.get(i));
			}
			System.out.println(nd);
		}
	}
	
}
