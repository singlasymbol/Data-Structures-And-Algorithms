import java.util.*;
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

class BST{

	static Node root;

	BST()
	{
		root = null;
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

	public static void main(String[] args){
		BST bst = new BST();
		root = bst.insert(root,4);
		root = bst.insert(root,3);
		root = bst.insert(root,6);
		root = bst.insert(root,7);
		root = bst.insert(root,8);

		bst.inorder(root);
		System.out.println();
		bst.levelOrderTraversal(root);
	}
}