import java.util.ArrayList;

public class MorseCodeTree implements LinkedConverterTreeInterface<String> {
	protected TreeNode<String> root = new TreeNode<String>();

	public MorseCodeTree() {
		// call buildtree
		buildTree();
	}

	@Override
	public TreeNode<String> getRoot() {
		return root;
	}

	@Override
	public void setRoot(TreeNode<String> newNode) {
		root = new TreeNode<String>(newNode);
	}

	@Override
	public LinkedConverterTreeInterface<String> insert(String code, String result) {
		addNode(root, code, result);
		return this;
	}

	@Override
	public void addNode(TreeNode<String> root, String code, String letter) {
		if (code.length() == 1) {
			if (code.equals(".")) {
				root.left = new TreeNode<String>(letter);
			}
			if (code.equals("-")) {
				root.right = new TreeNode<String>(letter);
			}
		} else if (code.length() != 1) {
			if (code.charAt(0) == '-') {
				addNode(root.right, code.substring(1), letter);
			}
			if (code.charAt(0) == '.') {
				addNode(root.left, code.substring(1), letter);
			}
		}
	}

	@Override
	public String fetch(String code) {
		return fetchNode(root, code);
	}

	@Override
	public String fetchNode(TreeNode<String> root, String code) {
		if (code.length() == 1) {
			if (code.equals(".")) {
				return root.left.getData();
			}
			if (code.equals("-")) {
				return root.right.getData();
			}
		} else if (code.length() != 1) {
			if (code.charAt(0) == '-') {
				return fetchNode(root.right, code.substring(1));
			}
			if (code.charAt(0) == '.') {
				return fetchNode(root.left, code.substring(1));
			}
		}
		return null;
	}

	@Override
	public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void buildTree() {
		setRoot(new TreeNode<String>(""));

		insert(".", "e");
		insert("-", "t");
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");

	}

	@Override
	public ArrayList<String> toArrayList() {
		ArrayList<String> o = new ArrayList<String>();
		LNRoutputTraversal(root, o);
		return o;
	}

	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
		if (root == null) {
			return;
		}
		LNRoutputTraversal(root.left, list);
		list.add(root.getData());
		LNRoutputTraversal(root.right, list);
	}

}
