public class TreeNode<T> {
	protected TreeNode<T> left;
	protected TreeNode<T> right;
	protected T data;
	
	public TreeNode() {
		
	}
	
	public TreeNode(T dataNode) {
		data  = dataNode;
		left = null;
		right = null;
	}
	public TreeNode(TreeNode<T> node) {
		data = node.data;
		left = node.left;
		right = node.right;
	}
	
	public T getData() {
		return data;
	}

}
