class LinkedList{
	
	static class Node{
		int val;
		Node next;

		Node(int value){
			val = value;
			next = null;
		}
	}
	static Node head;

	public void insertAtEnd(int val){
		Node n = new Node(val);

		if(head == null){
			head = n;
		}else{
			Node ptr = head;
			while(ptr.next != null){
				ptr = ptr.next;
			}

			ptr.next = n;
		}
	}

	public void insertAtStart(int val){
		Node n = new Node(val);

		if(head == null){
			head = n;
		}else{
			n.next = head;
			head = n;
		}
	}

	public void print(Node node){
		Node ptr = node;

		while(ptr != null){
			System.out.print(ptr.val + " ");
			ptr = ptr.next;
		}
		System.out.println();
	}

	public int getSize(){
		Node ptr = head;
		int count = 0;

		while(ptr != null){
			ptr = ptr.next;
			count++;
		}

		return count;
	}
 
	public void deleteNthNode(int nodeNumber){

		int LinkedListSize = getSize();

		if(nodeNumber == 0){
			
			System.out.println("Please enter a valid nodeNumber i.e. from 1 to " + getSize());

		}else if(nodeNumber > LinkedListSize){

			System.out.println("Total nodes in Linked List are " + getSize());
		
		}else if(nodeNumber == 1){
			//this is the first Node

			head = head.next;
		}else{
			Node ptr = head;
			Node prev = null;
			int count = 1;

			while(ptr != null && count < nodeNumber){
				prev = ptr;
				ptr = ptr.next;
				count++;
			}
			System.out.println("deleting " +  ptr.val);
			prev.next = ptr.next;
		}
	}

	public Node reverseIterative(Node node){

		Node prev = null;
		Node curr = node;
		Node next = null;

		while(curr != null){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}

		node = prev;

		return node;
	}

	public void reverseRecurrsive(){

	}

	public void nthNodeFromEndWithoutHead(int n){
		// find nth node from End without head reference

		// The idea is to use two pointers
		// move the first pointer n nodes ahead of the second node
		// and traverse both nodes until the first node reaches null
		// which will place second node at n positions from end

		if(n > getSize()){
			System.out.println("Please enter a valid number");
			return;
		}

		Node ptr = head;
		int count= 1;
		while(ptr != null && count < n){
			ptr = ptr.next;
			count++;
		}

		Node ptr1 = head;
		while(ptr.next != null){
			ptr = ptr.next;
			ptr1 = ptr1.next;
		}

		System.out.println("node from end is " + ptr1.val);
	}

	public Node getMidNode(){
		Node first = head;
		Node second= head;

		while(second.next != null && second.next.next != null){
			first = first.next;
			second = second.next.next;
		}

		System.out.println("mid node is " + first.val);
        return first;
	}

	public boolean iterateAndMatchList(Node first,Node second){

		while(first.next != null && second.next != null){
			// System.out.println(first.val + " " + second.val);
			if(first.val != second.val){
				return false;
			}
			first = first.next;
			second = second.next;
		}
		return true;
	}

	public void removeDuplicatesInUnsortedList(Node node){

		Node ptr = node;
		Node ptr1 = node.next;

		while(ptr != null){
			ptr1 = ptr.next;
			Node prev = ptr;

			while(ptr1 != null){
				if(ptr.val == ptr1.val){
					prev.next = ptr1.next;
					ptr1 = prev.next;
				}else{
					prev = ptr1;
					ptr1 = ptr1.next;
				}
			}
			ptr = ptr.next;
		}
	}

	public void removeDuplicatesInSortedlist(Node node){

		Node ptr = node;
		Node ptr1 = node.next;

		while(ptr != null && ptr.next != null){
			if(ptr.next.val == ptr.val){
				ptr.next = ptr.next.next;
			}else{
				ptr = ptr.next;
			}
		}
	}

	public Node removeAllDuplicatedinASortedList(Node node){
		Node temp = new Node(-1);

		temp.next = node;
		Node curr = node;
		Node prev = temp; 
		while(curr != null){

			while(curr.next != null && curr.next.val == prev.next.val){
				curr = curr.next;
			}

			if(prev.next == curr){
				prev = prev.next;
			}else{
				prev.next = curr.next;
			}

			curr = curr.next;
		}

		return node = temp.next;

	}

	public boolean isPalindrome(){

		Node mid = getMidNode();
		mid.next = reverseIterative(mid.next);
		// print(mid);
		return iterateAndMatchList(head,mid.next);
	}

	public static void main(String[] args){
		LinkedList ll = new LinkedList();
		
		ll.insertAtEnd(2);
		ll.insertAtEnd(2);
		ll.insertAtEnd(3);
		ll.insertAtEnd(3);
		ll.insertAtEnd(4);
		ll.insertAtEnd(4);
		ll.insertAtEnd(1);
		ll.insertAtStart(1);
		ll.print(head);
		
		head = ll.removeAllDuplicatedinASortedList(head);
		//ll.removeDuplicatesInSortedlist(head);
		//ll.removeDuplicatesInUnsortedList(head);

		// System.out.println(ll.isPalindrome() ? "list is Palindrome" : "list is not Palindrome");
		//ll.getMidNode();
		//head  = ll.reverseIterative(head);
		//ll.nthNodeFromEndWithoutHead(2);
		// ll.reverseRecurrsive(head);
		//ll.deleteNthNode(10);
		
		ll.print(head);

	}

}