package tree;

class RBTreeNode<T> {
	
	private T value;
	private RBTreeNode<T> parent;
	private RBTreeNode<T> leftChild;
	private RBTreeNode<T> rightChild;
	private boolean color;
	private boolean extra_color;
	
	public RBTreeNode() {
		
	}
	public RBTreeNode(T value) {
		
		this.value = value;
	}
	public RBTreeNode(T value, boolean color) {
		
		this.value = value;
		this.color = color;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public RBTreeNode<T> getParent() {
		return parent;
	}
	public void setParent(RBTreeNode<T> parent) {
		this.parent = parent;
	}
	public RBTreeNode<T> getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(RBTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}
	public RBTreeNode<T> getRightChild() {
		return rightChild;
	}
	public void setRightChild(RBTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}
	public boolean isRed() {
		return this.color;
	}
	public void setColor(boolean color) {
		this.color = color;
	}
	public boolean getExtra_color() {
		return extra_color;
	}
	public void setExtra_color(boolean extra_color) {
		this.extra_color = extra_color;
	}
	public int compareTo(T oValue) {
		
		long theValue = Long.valueOf(this.value.toString());
		long theOValue = Long.valueOf(oValue.toString());
		return theValue > theOValue ? 1 : (theValue == theOValue ? 0 : -1);
	}
}
public class RBTree<T> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private RBTreeNode<T> root;
	private RBTreeNode<T> nil = new RBTreeNode<T>(null, BLACK);
	
	public RBTree() {

	}
	
	public RBTree(T value) {
		
		root = new RBTreeNode<T>(value);
		root.setColor(BLACK);
		root.setLeftChild(nil);
		root.setRightChild(nil);
		root.setParent(nil);
	}
	
	public RBTreeNode<T> getRoot() {
		return root;
	}
	
	public RBTreeNode<T> search(RBTreeNode<T> node, T value) {
		
		while (node != nil && node.compareTo(value) != 0) {
			
			if (node.compareTo(value) < 0) {
				node = node.getRightChild();
			}
			else if (node.compareTo(value) > 0){
				node = node.getLeftChild();
			}
		}
		return node;
	}
	
	public RBTreeNode<T> getTreeMiniNum(RBTreeNode<T> node) {
		
		while (node.getLeftChild() != nil) {
			node = node.getLeftChild();
		}
		return node;
	}
	
	public RBTreeNode<T> getTreeMaxNum(RBTreeNode<T> node) {
		
		while (node.getRightChild() != nil) {
			node = node.getRightChild();
		}
		return node;
	}
	
	public void leftRotate(RBTreeNode<T> xNode) {
		
		RBTreeNode<T> yNode = xNode.getRightChild();
		xNode.setRightChild(yNode.getLeftChild());
		
		if (yNode.getLeftChild() != nil) {
			yNode.getLeftChild().setParent(xNode);
		}
		yNode.setParent(xNode.getParent());
		if (xNode.getParent() == nil) {
			root = yNode;
		}
		else if (xNode == xNode.getParent().getLeftChild()) {
			xNode.getParent().setLeftChild(yNode);
		}
		else {
			xNode.getParent().setRightChild(yNode);
		}
		yNode.setLeftChild(xNode);
		xNode.setParent(yNode);
	}
	
	public void rightRotate(RBTreeNode<T> xNode) {
		
		RBTreeNode<T> yNode = xNode.getLeftChild();
		xNode.setLeftChild(yNode.getRightChild());
		
		if (yNode.getRightChild() != nil) {
			yNode.getRightChild().setParent(xNode);
		}
		yNode.setParent(xNode.getParent());
		if (xNode.getParent() == nil) {
			root = yNode;
		}
		else if (xNode == xNode.getParent().getLeftChild()) {
			xNode.getParent().setLeftChild(yNode);
		}
		else {
			xNode.getParent().setRightChild(yNode);
		}
		yNode.setRightChild(xNode);
		xNode.setParent(yNode);
	}
	
	public void insert(RBTreeNode<T> xNode) {
		
		RBTreeNode<T> testNode = root;
		RBTreeNode<T> preNode = nil;
		
		while (testNode != nil) {
			
			preNode = testNode;
			if (xNode.compareTo(testNode.getValue()) > 0) {
				testNode = testNode.getRightChild();
			}
			else if (xNode.compareTo(testNode.getValue()) < 0) {
				testNode = testNode.getLeftChild();
			}
		}
		xNode.setParent(preNode);
		if (preNode == nil) {
			root = xNode;
		}
		else if (xNode.compareTo(preNode.getValue()) > 0) {
			preNode.setRightChild(xNode);
		}
		else if (xNode.compareTo(preNode.getValue()) < 0) {
			preNode.setLeftChild(xNode);
		}
		xNode.setColor(RED);
		xNode.setLeftChild(nil);
		xNode.setRightChild(nil);
		
		fixInsert(xNode);
	}
	
	public void fixInsert(RBTreeNode<T> xNode) {
		
		while (xNode.getParent().isRed()) {
			
			if (xNode.getParent() == xNode.getParent().getParent().getLeftChild()) {
				
				RBTreeNode<T> uncleNode = xNode.getParent().getParent().getRightChild();
				if (uncleNode.isRed()) {
					
					xNode.getParent().setColor(BLACK);
					uncleNode.setColor(BLACK);
					xNode.getParent().getParent().setColor(RED);
					xNode = xNode.getParent().getParent();
				}
				else {
					if (xNode == xNode.getParent().getRightChild()){
						
						xNode = xNode.getParent();
						leftRotate(xNode);
					}
					xNode.getParent().setColor(BLACK);
					xNode.getParent().getParent().setColor(RED);
					rightRotate(xNode.getParent().getParent());
				}
			}
			else {
				RBTreeNode<T> uncleNode = xNode.getParent().getParent().getLeftChild();
				if (uncleNode.isRed()) {
					
					xNode.getParent().setColor(BLACK);
					uncleNode.setColor(BLACK);
					xNode.getParent().getParent().setColor(RED);
					xNode = xNode.getParent().getParent();
				}
				else {
					if (xNode == xNode.getParent().getLeftChild()) {
						
						xNode = xNode.getParent();
						rightRotate(xNode);
					}
					xNode.getParent().setColor(BLACK);
					xNode.getParent().getParent().setColor(RED);
					leftRotate(xNode.getParent().getParent());
				}
			}
		}
		root.setColor(BLACK);
	}
	
	public void transplant(RBTreeNode<T> xNode, RBTreeNode<T> yNode) {
		
		if (xNode.getParent() == nil) {
			root = yNode;
		}
		else if (xNode == xNode.getParent().getLeftChild()) {
			xNode.getParent().setLeftChild(yNode);
		}
		else {
			xNode.getParent().setRightChild(yNode);
		}
		yNode.setParent(xNode.getParent());
	}
	
	public void delete(RBTreeNode<T> xNode) {
		
		RBTreeNode<T> yNode = xNode;
		boolean yIsBlack = !yNode.isRed();
		RBTreeNode<T> fixNode;
		
		if (xNode.getLeftChild() == nil) {
			
			fixNode = xNode.getRightChild();
			transplant(xNode, xNode.getRightChild());
		}
		else if (xNode.getRightChild() == nil) {
			
			fixNode = xNode.getLeftChild();
			transplant(xNode, xNode.getLeftChild());
		}
		else {
			yNode = getTreeMiniNum(xNode.getRightChild());
			yIsBlack = !yNode.isRed();
			fixNode = yNode.getRightChild();
			
			if (yNode.getParent() == xNode) {
				fixNode.setParent(yNode);
			}
			else {
				transplant(yNode, yNode.getRightChild());
				yNode.setRightChild(xNode.getRightChild());
				yNode.getRightChild().setParent(yNode);
			}
			transplant(xNode, yNode);
			yNode.setLeftChild(xNode.getLeftChild());
			yNode.getLeftChild().setParent(yNode);
			yNode.setColor(xNode.isRed());
		}
		
		if (yIsBlack) {
			fixDelete(fixNode);
		}
	}
	
	public void fixDelete(RBTreeNode<T> xNode) {
		RBTreeNode<T> brotherNode;
		while (xNode != root && !xNode.isRed()) {
			
			if (xNode == xNode.getParent().getLeftChild()) {
				
				brotherNode = xNode.getParent().getRightChild();
				if (brotherNode.isRed()) {
					
					brotherNode.setColor(BLACK);
					xNode.getParent().setColor(RED);
					leftRotate(xNode.getParent());
					brotherNode = xNode.getParent().getRightChild();
				}
				if (!brotherNode.getLeftChild().isRed() && !brotherNode.getRightChild().isRed()) {
					
					brotherNode.setColor(RED);
					xNode = xNode.getParent();
				}
				else {
					if (!brotherNode.getLeftChild().isRed()) {
						
						brotherNode.getLeftChild().setColor(BLACK);
						brotherNode.setColor(RED);
						rightRotate(brotherNode);
						brotherNode = xNode.getParent().getRightChild();
					}
					brotherNode.setColor(xNode.getParent().isRed());
					brotherNode.getRightChild().setColor(BLACK);
					xNode.getParent().setColor(BLACK);
					leftRotate(xNode.getParent());
					xNode = root;
				}
			}
			else {
				brotherNode = xNode.getParent().getLeftChild();
				if (brotherNode.isRed()) {
					
					brotherNode.setColor(BLACK);
					xNode.getParent().setColor(RED);
					rightRotate(xNode.getParent());
					brotherNode = xNode.getParent().getLeftChild();
				}
				if (!brotherNode.getLeftChild().isRed() && !brotherNode.getRightChild().isRed()) {
					
					brotherNode.setColor(RED);
					xNode = xNode.getParent();
				}
				else {
					if (!brotherNode.getLeftChild().isRed()) {
						
						brotherNode.setColor(RED);
						brotherNode.getRightChild().setColor(BLACK);
						rightRotate(brotherNode);
						brotherNode = xNode.getParent().getLeftChild();
					}
					brotherNode.setColor(xNode.getParent().isRed());
					xNode.getParent().setColor(BLACK);
					brotherNode.getLeftChild().setColor(BLACK);
					rightRotate(xNode.getParent());
					xNode = root;
				}
			}
		}
		xNode.setColor(BLACK);
	}
	
	public static void main(String[] args) {
		
		RBTree<Integer> tree = new RBTree<>(10);
		RBTreeNode<Integer> newNode = new RBTreeNode<>(9);
		tree.insert(newNode);
		newNode = new RBTreeNode<>(11);
		tree.insert(newNode);
		newNode = new RBTreeNode<>(12);
		tree.insert(newNode);
		newNode = new RBTreeNode<>(13);
		tree.insert(newNode);
		
		newNode = tree.search(tree.getRoot(), 12);
		tree.delete(newNode);
	
	}
}
