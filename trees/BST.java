import java.util.*;
import java.util.Map.Entry;

class Node{
	int val;
	Node left;
	Node right;

	Node(int key){
		val = key;
		left = null;
		right = null;
	}

}

class VerticalTraversalNode{
	int horizontal_distance;
	Node node;

	VerticalTraversalNode(int hd, Node newNode){
		horizontal_distance = hd;
		node = newNode;
	}
}

class BST{

	static Node root;
	static Node root1;

	BST()
	{
		root = null;
		root1 = null;
	}

	public void inorder(Node node){
		if(node == null){
			return;
		}

		inorder(node.left);
		System.out.print(node.val + " ");
		inorder(node.right);
	}

	public Node insert(Node node,int val){
		Node n = new Node(val);

		if(node == null){
			node = n;
			return node;
		}else if(node.val > val){
			node.left = insert(node.left,val);
		}else{
			node.right = insert(node.right,val);
		}
		return node;
	}

	public void levelOrderTraversal(Node node){
		Queue<Node> q = new LinkedList<Node>();

		q.add(node);

		while(!q.isEmpty()){
			Node temp = q.poll();
			System.out.print(temp.val + " ");

			if(temp.left != null)
				q.add(temp.left);

			if(temp.right != null)
				q.add(temp.right);
		}
	}

	public void levelOrderTraversalLineByLine(Node node){
		Node iterator = node;

		// the idea is to keep the count of the elements in the queue at the start 
		// and keep printing as soon as we keep popping(till the count is zero)

		Queue<Node> q = new LinkedList<Node>();
		int nodeCountInQueue = 0;
		q.add(iterator);

		while(true){

			nodeCountInQueue = q.size();
			if(nodeCountInQueue == 0){
				break;
			}

			while(nodeCountInQueue != 0){
				Node temp = q.poll();
				System.out.print(" " + temp.val);

				if(temp.left != null){
					q.add(temp.left);
				}

				if(temp.right != null){
					q.add(temp.right);
				}
				nodeCountInQueue--;
			}
			System.out.println();
		}
	}

	public void reverseLevelOrderTraversal(Node node){

		// the idea is to use a queue and a stack.
		// keeep on popping from queue and keep adding in stack simultaneously, 
		//following simple LOR approach for adding in queue.

		Queue<Node> q = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();

		q.add(node);

		while(!q.isEmpty()){
			Node temp = q.poll();

			stack.push(temp);

			if(temp.left != null){
				q.add(temp.right);
			}

			if(temp.right != null){
				q.add(temp.left);
			}
		}

		while(!stack.isEmpty()){
			Node temp = stack.pop();
			System.out.print(temp.val + " "); 
		}

	}

	public void fillTreeMap(Node node,TreeMap<Integer,Vector<Integer>> tm,int horizontal_level){
		if(node == null){
			return;
		}

		Vector<Integer>get_vector = tm.get(horizontal_level);
		if(get_vector == null){
			get_vector = new Vector<Integer>();
		}
		get_vector.add(node.val);
		tm.put(horizontal_level,get_vector);

		fillTreeMap(node.left, tm, horizontal_level - 1);
		fillTreeMap(node.right, tm, horizontal_level + 1);
	}

	public void verticalOrderTraversal(Node node){

	// Although this approach works fine if it comes to just printing the nodes in the same level
	// but there can be cases where it prints a node that is at a lower height before the one at a 
	// higher height. PRE-ORDER -> goes to left, so if right one has level 2,left  will call again before
	// right calls,, therefore the ones lower than right one, can come forehand.

		TreeMap<Integer,Vector<Integer>> tm = new TreeMap<Integer,Vector<Integer>>();
		int horizontal_level = 0;

		fillTreeMap(node, tm, horizontal_level);

		for (Entry<Integer, Vector<Integer>> entry : tm.entrySet()){
			System.out.println(entry.getValue());

		}
	}

	public void verticalOrderTraversalUsingLevelOrderTraversal(Node node){
		TreeMap<Integer, ArrayList<Integer>> tm = new TreeMap<Integer,ArrayList<Integer>> ();
		Queue<VerticalTraversalNode> queue = new LinkedList<VerticalTraversalNode>();
		
		//assuming node is not null, i.e. tree is present;
		VerticalTraversalNode root_node = new VerticalTraversalNode(0,node);
		queue.add(root_node);
		ArrayList<Integer> arr = new ArrayList<Integer>();
		arr.add(node.val);
		tm.put(0,arr);

		while(!queue.isEmpty()){
			VerticalTraversalNode polledNode = queue.poll();

			if(polledNode.node.left != null){
				int hd = polledNode.horizontal_distance - 1;
				VerticalTraversalNode temp_node = new VerticalTraversalNode(hd,polledNode.node.left);
				queue.add(temp_node);

				ArrayList<Integer>get_array = tm.get(hd);
				if(get_array == null)
					get_array = new ArrayList<Integer>();
				
				get_array.add(polledNode.node.left.val);
				tm.put(hd,get_array);
			}

			if(polledNode.node.right != null){
				int hd = polledNode.horizontal_distance + 1;
				VerticalTraversalNode temp_node = new VerticalTraversalNode(hd,polledNode.node.right);
				queue.add(temp_node);

				ArrayList<Integer>get_array = tm.get(hd);
				if(get_array == null)
					get_array = new ArrayList<Integer>();
	
				get_array.add(polledNode.node.right.val);			
				tm.put(hd,get_array);				
			}
		}
		
		for (Entry<Integer, ArrayList<Integer>> entry : tm.entrySet()){
			System.out.println(entry);

		}
	}

	public void perfectBinaryTreeSpecificLevelOrderTraversal(Node node){
		Queue<Node> queue = new LinkedList<Node>();
		Queue<Node>finalQueue = new LinkedList<Node>();
		
		//assuming atleast it has two levels. 
		//queue.add(node);
		queue.add(node.left);
		queue.add(node.right);

		finalQueue.add(node);
		finalQueue.add(node.left);
		finalQueue.add(node.right);

		while(!queue.isEmpty()){
			Node firstNode = queue.poll();
			Node secondNode = queue.poll();

			if(firstNode.left != null)
				queue.add(firstNode.left);
			if(secondNode.right != null)
				queue.add(secondNode.right);
			if(firstNode.right != null)
				queue.add(firstNode.right);
			if(secondNode.left != null)
				queue.add(secondNode.left);


			if(firstNode.left != null)
				finalQueue.add(firstNode.left);
			if(secondNode.right != null)
				finalQueue.add(secondNode.right);
			if(firstNode.right != null)
				finalQueue.add(firstNode.right);
			if(secondNode.left != null)
				finalQueue.add(secondNode.left);
		
		}

		while(!finalQueue.isEmpty()){
			System.out.print(finalQueue.remove().val + " ");
		}
	}


	public Node lowestCommonAncestor(Node node, int key1, int key2){

		if(node == null){
			return null;
		}

		if(node.val > key1 && node.val > key2){
			return lowestCommonAncestor(node.left, key1, key2);
		}


		if(node.val < key1 && node.val < key2){
			return lowestCommonAncestor(node.right, key1, key2);
		}

		return node;
	}

	public int distanceBetweenTwoNode(Node node, int key1, int key2){
		// the idea is to find the lca first and then find the distance of the keys from lca and return the sum.
		Node lca = lowestCommonAncestor(node, key1, key2);
		int d1 = distanceOfKeyFromAncestor(lca, key1, 0);
		int d2 = distanceOfKeyFromAncestor(lca, key2, 0);

		// ANOTHER WAY of solving it can be using this -> Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) ;
		//this method will have more complexity as we will have to find the distance of lca extra than the method we are using.
		
		return d1 + d2;
	}

	public int distanceOfKeyFromAncestor(Node node, int key, int distance){
		if(node == null){
			return -1;
		} 

		if(node.val == key){
			return distance;
		}

		int d1 = distanceOfKeyFromAncestor(node.left, key, distance + 1);
		int d2 = distanceOfKeyFromAncestor(node.right, key, distance + 1);

		if(d1 != -1){
			return d1;
		}

		return d2;
	}

	public void printCommonNodes(Node node, int key1,int key2){
		ArrayList<Node> arr = new ArrayList<Node>();
		printCommonNodesUtil(node, key1, key2, arr);

		for(int i = 0;i < arr.size();i++)
			System.out.println(arr.get(i).val);
	}

	public void printCommonNodesUtil(Node node, int key1, int key2,ArrayList<Node> arr){

		if(node == null){
			return;
		}

		if(node.val > key1 && node.val > key2){
			
			arr.add(node);
			printCommonNodesUtil(node.left, key1, key2, arr);

		}else if(node.val < key1 && node.val < key2){
			
			arr.add(node);
			printCommonNodesUtil(node.right, key1, key2, arr);
		
		}else{
			//lca	
			arr.add(node);
		
		}

		return;
	}

	public void printCommonNodesWithoutUsingExtraSpace(Node node, int key1, int key2){
		Node lca = lowestCommonAncestor(node, key1, key2);
		printNodesInPath(node, lca.val);
	}

	public void printNodesInPath(Node node, int lca){
		if(node == null){
			return;
		}

		if(node.val > lca){
			System.out.println(node.val + " ");
			printNodesInPath(node.left, lca);
		}

		if(node.val < lca){
			System.out.println(node.val + " ");
			printNodesInPath(node.right, lca);			
		}

		if(node.val == lca){
			System.out.println(node.val + " ");
			return;
		}

		return;
	}

	public void deleteAllTree(Node node){
		// do a postorder traversal and make every node as null;
		if(node == null){
			return;
		}

		deleteAllTree(node.left);
		deleteAllTree(node.right);
		System.out.println("deleting "+ node.val);
		node = null;

	}

	public int heightOfTree(Node node){
		if(node == null){
			return 0;
		}

		return 1 + Math.max(heightOfTree(node.left),heightOfTree(node.right));
	}

	public boolean checkIdenticalTrees(Node node1, Node node2){
		if(node1 == null && node2 == null){
			return true;
		}

		if(node1 != null && node2 != null){
			return (node1.val == node2.val) && checkIdenticalTrees(node1.left,node2.left) && checkIdenticalTrees(node1.right, node2.right);
		}
		System.out.println(node1 + " "  + (node2.val));
		return false;
	}

	public int sizeOfTree(Node node){
		if(node == null){
			return 0;
		}
		return 1 + sizeOfTree(node.left) + sizeOfTree(node.right);
	}

	public boolean rootToLeafPathSum(Node node,int sum){
		if(node == null){
			return false;
		}

		if(node.left == null && node.right == null){
			return (node.val == sum);
		}

		return rootToLeafPathSum(node.left, sum - node.val) || rootToLeafPathSum(node.right, sum - node.val);
	}

	public boolean heightBalancedTree(Node node){

		if(node == null){
			return true;
		}

		boolean left = heightBalancedTree(node.left);
		boolean right = heightBalancedTree(node.right);

		int leftHeight = heightOfTree(node.left);
		int rightHeight = heightOfTree(node.right);

		return left && right && (Math.abs(leftHeight - rightHeight) <= 1);
	}

	public int diameterOfTree(Node node){
		if(node == null){
			return 0;
		}

		int leftHeight = heightOfTree(node.left);
		int rightHeight = heightOfTree(node.right);

		return Math.max(leftHeight + rightHeight,Math.max(diameterOfTree(node.left),diameterOfTree(node.right)));
	}

	public static void main(String[] args){
		BST bst = new BST();
		Scanner scan = new Scanner(System.in);
		// System.out.println("Please Enter the number of nodes in the tree");
		// int number_of_nodes = scan.nextInt();

		// for(int i = 0; i < number_of_nodes ;i++){
		// 	root = bst.insert(root,scan.nextInt());
		//  		root = bst.insert(root,20);
		root = bst.insert(root,10);
		root = bst.insert(root,30);
		root = bst.insert(root,5);
		root = bst.insert(root,15);
		root = bst.insert(root,25);
		root = bst.insert(root,40);
		root = bst.insert(root,1);
		root = bst.insert(root,7);
		root = bst.insert(root,13);
		root = bst.insert(root,17);
		root = bst.insert(root,21);
		root = bst.insert(root,27);
		root = bst.insert(root,35);
		root = bst.insert(root,45);

		bst.inorder(root);
		System.out.println();
		//bst.levelOrderTraversal(root);
		// bst.levelOrderTraversalLineByLine(root);
		//bst.reverseLevelOrderTraversal(root);
		//bst.verticalOrderTraversalUsingLevelOrderTraversal(root);
		//perfect binary tree - basically every node should have a outdegree of 2
		// bst.perfectBinaryTreeSpecificLevelOrderTraversal(root);
		//bst.lowestCommonAncestor(root, 27, 21);
		// System.out.println(bst.distanceBetweenTwoNode(root,15,17));
		//bst.printCommonNodes(root, 15,17);
		//System.out.println("---");
		//bst.printCommonNodesWithoutUsingExtraSpace(root, 15,17);
		//System.out.println(bst.heightOfTree(root));	

		// bst.deleteAllTree(root);
		// bst.inorder(root);

		//System.out.println(bst.checkIdenticalTrees(root,root1));
		//System.out.println(bst.sizeOfTree(root));

		//System.out.println(bst.rootToLeafPathSum(root,68));
		// System.out.println(bst.heightBalancedTree(root));
		System.out.println("Diameter of tree is " + (1+  bst.diameterOfTree(root)));
	}
}