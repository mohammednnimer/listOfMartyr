package application;


public class linked_List<T extends Comparable<T>> {

	private  Node<T> head=new Node(null);
    
	
	public void insert(T data) {
		
		Node<T> newnode = new Node<>(data);
		 Node<T> curr = head.getNext();
		Node<T> prev = null;
		if (head.getNext() == null) {
			head.setNext( newnode);

			
		} 
		
		else {
			
			for (; curr != null && curr.getData().compareTo(data) <= 0; prev = curr, curr = curr.getNext());

			if (prev == null) {
				newnode.setNext(curr);
				head.setNext( newnode);
		

			} else {
				
				newnode.setNext(curr); 
				prev.setNext(newnode); 

			}

		}
	/*	for (; curr != null && curr.getData().compareTo(data) <= 0; prev = curr, curr = curr.getNext());

		if (prev == null) {
			newnode.setNext(curr);
			head.setNext( newnode);
	

		} else if (curr == null) {

			prev.setNext(newnode);

		} else {
			
			newnode.setNext(curr); 
			prev.setNext(newnode); 

		}*/


	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public Node delet(T data) {
		Node<T> curr = head.getNext();
		Node<T> prev = null;
		if (head.getNext() == null) {
			return null;
		}
		for (; curr != null && curr.getData().compareTo(data) != 0; prev = curr, curr = curr.getNext())
			;
		if (curr == null)
			return null;

		if (prev == null)
			head.setNext( curr.getNext());

		else if (curr.getNext() == null)
			prev.setNext(null);

		else
			prev.setNext(curr.getNext()); 

		return curr;

	}

	public Node<T> find(T data) {

		return find(data, head.getNext());

	}

	private Node<T> find(T data, Node curr) {
		if (curr == null)
			return null;

		if (curr.getData().compareTo(data) == 0)
			return  curr;

		return find(data, curr.getNext());

	}

	//
	//
	//
	//
	// public Node find(T data) {
	//
	// Node<T> curr = head;
	//
	// if(head==null)
	// {
	// return null;
	// }
	// for(;curr != null &&curr.data.compareTo(data)!=0;curr=curr.next);
	// if(curr==null)
	// return null;
	//
	//
	//
	//
	//
	// return curr;
	//
	//
	//
	// }
	//
	public void Reverse_iterative() {
		if (head == null || head.getNext() == null)
			return;

		Node curr = head.getNext();
		
		Node prev=null;
		Node next=null;
	
	while (curr !=null) {
		
		
		
		
		next=curr.getNext();
		curr.setNext(prev); 
		prev=curr;
		curr= next;
		
		
		
	}	
		head=prev;
		
		
		
		
		
		

	}

	public void reverse_Recursive() {
		if (head == null || head.getNext() == null)
			return;

		Node curr = head;
		reverse(head, head.getNext());
		curr.setNext(null);

	}

	private void reverse(Node data, Node curr) {
		if (curr.getNext() == null) {
			curr.setNext(data); 
			head = curr;
			return;
		}

		reverse(data.getNext(), curr.getNext());
		curr.setNext(data); 

	}

	public Node max() {
		if (head == null)
			return null;
		return max(head.getNext(), head.getNext());

	}

	private Node max(Node n, Node maxim) {
		if (n == null)
			return maxim;

		if (n.getData().compareTo(maxim.getData()) > 0)
			maxim = n;

		return max(n.getNext(), maxim);

	}

	public Node min() {
		if (head == null)
			return null;
		return min(head.getNext(), head.getNext());

	}

	private Node min(Node n, Node min) {
		if (n == null)
			return min;

		if (n.getData().compareTo(min.getData()) < 0)
			min = n;

		return min(n.getNext(), min);

	}
	
	public int length()
	{
		int i=0;
		
		Node<T> curr=head.getNext();
		while (curr!=null) {
			i++;
			curr=curr.getNext();
			
		}
		return i;
		
	}
	
	public String travirce() {
		
		Node<T> curr = head.getNext();
		String sum ="dommy -->";
				while (curr != null) {
			
					sum+=curr.getData().toString() + " -->";
						
					
					curr = curr.getNext();

				}
				sum+="null";
return sum;
			}
	
	

	public void travrce() {
		Node<T> curr = head.getNext();
System.out.print("dommy -->");
		while (curr != null) {
	
			System.out.print(curr.getData().toString() + " -->");
		
				
				
			
			curr = curr.getNext();

		}
		System.out.print("null");

		System.out.println();
	}

}