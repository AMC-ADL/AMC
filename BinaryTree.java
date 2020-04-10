package aula5;

import java.util.ArrayList;

class TreeNode{
	int key;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int key, TreeNode left, TreeNode right) {
		this.key = key;
		this.left = left;
		this.right = right;
	}

	public TreeNode(int key) {
		this.key = key;
		this.left = null;
		this.right = null;
	}

	@Override
	public String toString() {
		return "[" + key + "," + left + "," + right + "]";
	}
	
}

public class BinaryTree {
	TreeNode root;

	public BinaryTree(int key, BinaryTree left, BinaryTree right) {
		TreeNode n = new TreeNode(key, left.root, right.root);
		
		this.root = n;
	}
	
	public BinaryTree() {
		this.root = null;
	}
	
	public BinaryTree(int key) {
		this.root = new TreeNode(key);
	}
	
	public int numNodes() {
		return numNodes(root);
	
	}
	
	private int numNodes(TreeNode root) {
		if(root==null) return 0;
		else return 1+ numNodes(root.left) + numNodes(root.right);		
	}
	
	public int depth() {
		return depth(root);
		
	}
	public int depth(TreeNode root){
	 		if (root == null) return -1;
			else return 1+ Math.max(depth(root.left), depth(root.right));
	}
	 		
	public boolean memberQ(int x) {
		return memberQ(x, root);
	}
	
	private boolean memberQ(int x, TreeNode root) {
		if (root == null) return false;
		else  return (x == root.key || memberQ(x, root.left) || memberQ(x, root.right));
			
	}
	
	public ArrayList<Integer> depth_first(){
		return depth_first(root);
	}
	
	private ArrayList<Integer> depth_first(TreeNode root){
		ArrayList<Integer> vis = new ArrayList<Integer>();
		if (root == null) return vis;
		else {
			vis.addAll(depth_first(root.left));
			vis.add(root.key);
			vis.addAll(depth_first(root.right));
			return vis;
		}
	}
	
	public ArrayList<Integer> breadth_first(){
		return breadth_first(root);
	}
	
	private ArrayList<Integer> breadth_first(TreeNode root){
		ArrayList<Integer> vis = new ArrayList<Integer>();
		ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
		if (root != null) {
			queue.add(root);
			while (!queue.isEmpty()){
				TreeNode first = queue.get(0);
				queue.remove(0);
				vis.add(first.key);
				if (first.left != null) queue.add(first.left);
				if (first.right != null) queue.add(first.right);
			}
		}
		return vis;
	}
	
	public void insert(int x) {
		root = insert(x, root);
	}
	
	private TreeNode insert(int x, TreeNode root) {
		if (root == null) return new TreeNode(x);
		else {
			if (x < root.key) root = new TreeNode(root.key, insert(x, root.left), root.right);
			else root = new TreeNode(root.key, root.left, insert(x, root.right));
			return root;
		}
	}
	
	@Override
	public String toString() {
		return "BinaryTree [root=" + root + "]";	
	}

	
	public static void main(String[] args) {
		
		TreeNode n1 = new TreeNode(5);
		TreeNode n2 = new TreeNode(10);
		TreeNode n3 = new TreeNode(20,n2,n1);
		System.out.println(n3);
		BinaryTree b0 = new BinaryTree();
		BinaryTree b1 = new BinaryTree(12,b0,b0);
		BinaryTree b2 = new BinaryTree(22,b1,new BinaryTree());
		System.out.println(b2);
		System.out.println(b2.breadth_first());
		b2.insert(7);
		System.out.println(b2);
		
	}
	
}
