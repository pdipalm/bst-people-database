/*
 * Binary search tree class
 * @author Peter DiPalma
 * @version 1.0
 */
import java.util.ArrayList;
//comparable extended because objects in BST must be able to be compared
public class Bstree <T extends Comparable<T>> {
	private TreeNode<T> rootNode;
	
	/*
	 * constructs an empty BST
	 */
	public Bstree() {
		rootNode = null;
	}
	
	/*
	 * Returns an ArrayList of <T>
	 * 
	 * @return an ArrayList of <T>
	 */
	public ArrayList<T> getList() {
		ArrayList<T> traversalList = new ArrayList<>();
		inorderList(traversalList, rootNode);
		return traversalList;
	}
	
	/*
	 * returns a boolean indicating if an item is present in the BST, uses search()
	 * 
	 * @param the item to look for
	 * @return boolean indicating if item is present
	 */
	public boolean exists(T item) {
		if(search(item) != null)
			return true;
		
		return false;
	}
	
	/*
	 * inserts an item into the BST
	 * 
	 * @param <T> item to insert
	 */
	public void insert(T item) {
		rootNode = insertR(rootNode, item);
	}
	/*
	 * search method that calls recursive method
	 * 
	 * @param <T> item to search for
	 * @return reference to TreeNode where item is located
	 */
	public TreeNode<T> search(T item){
		return searchR(rootNode, item);
	}
	/*
	 * deletes an item from the BST, calls the recursive delete function which updates the tree
	 * 
	 * @param <T> item
	 */
	public void delete(T item) {
		rootNode = deleteR(rootNode, item);
	}
	/*
	 * private method called by getList
	 * 
	 * @param ArrayList that is being added to, TreeNode that recurses from the root
	 */
	private void inorderList(ArrayList<T> traversalList, TreeNode<T> root) {
		if (root == null)
			return;
		inorderList(traversalList, root.getLeft());
		traversalList.add(root.getData());
		inorderList(traversalList, root.getRight());
	}

	/*
	 * recursive search method
	 * 
	 * @param recursive root, <T> target item
	 * @return reference to TreeNode where item is located 
	 */
	private TreeNode<T> searchR(TreeNode<T> root, T item){
		if (root==null || root.getData().equals(item))
	        return root;
		if (root.getData().compareTo(item) >= 0)
		       return searchR(root.getLeft(), item);
		return searchR(root.getRight(), item);
	}

	/*
	 * recursive method for insert
	 * @param root/recursive node, <T> item to insert
	 * @return TreeNode that will be assigned as appropriate child in the recursion
	 */
	private TreeNode<T> insertR(TreeNode<T> root, T item) {
		//base case
		if(root == null) {
			root = new TreeNode<T>(item);
			return root;
		}
		//recursive case
		if(root.getData().compareTo(item) >= 0) {
			root.setLeft(insertR(root.getLeft(), item));
		}else{
			root.setRight(insertR(root.getRight(), item));
		}
		//step return
		return root;
	}

	/*
	 * private recursive delete function
	 *  
	 * @param TreeNode root, <T> item to delete
	 * @return an updated treenode, the final return call will return the root of the tree
	 */
	private TreeNode<T> deleteR(TreeNode<T> root, T item) {
		//base case
		if(root == null)
			return root;
		//recursive calls for predecessors to be deleted
		if(root.getData().compareTo(item) > 0) {
			root.setLeft(deleteR(root.getLeft(), item));
		}else if(root.getData().compareTo(item) < 0) {
			root.setRight(deleteR(root.getRight(), item));
		}else {
			//this code is only reachable when we have found our node to be deleted
			if (root.getLeft() == null) //if this node has no left
				return root.getRight();
	        else if (root.getRight() == null) //if this node has no right
	            return root.getLeft();
			
			root.setData(minValue(root.getRight())); //set data as successor
			root.setRight(deleteR(root.getRight(), root.getData())); //delete old node
		}
		
		//this return is unreachable, but eclipse complained when I didn't include it
		return root;	
	}

	/*
	 * returns the minimum <T> in a (sub)tree
	 * 
	 * @param root TreeNode of the subtree
	 * @return <T> minimum item
	 */
	protected T minValue(TreeNode<T> root) {
		T min = root.getData();
		while(root.getLeft() != null) {
			min = root.getLeft().getData();
			root = root.getLeft();
		}
		return min;
	}
}
