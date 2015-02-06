
public class DoubleChain {
	
	private DNode head;
	
	public DoubleChain(double val) {
		head = new DNode(val); 
	}

	public DNode getFront() {
		DNode p = head;
		while(p.prev != null){
			p = p.prev;
		}
		return p;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		DNode p = head;
		while(p.next != null){
			p = p.next;
		}
		return p;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		getFront().prev = new DNode(d);
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		getBack().next = new DNode(d);
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
		return null;
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */		
		return null;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}
