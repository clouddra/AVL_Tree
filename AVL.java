class AVL extends BST {
	public AVL() {
		root = null;
	}

	private int h(BSTNode T) {
		return T == null ? -1 : T.height;
	}

	public int getHeight() {
		return root.height;
	}

	protected BSTNode rotateLeft(BSTNode T) {
		// T must have a right child

		BSTNode w = T.right;
		w.parent = T.parent;
		T.parent = w;
		T.right = w.left;
		if (w.left != null)
			w.left.parent = T;
		w.left = T;

		T.height = Math.max(h(T.left), h(T.right)) + 1;
		w.height = Math.max(h(w.left), h(w.right)) + 1;

		

		return w;
	}

	protected BSTNode rotateRight(BSTNode T) {
		// T must have a left child

		BSTNode w = T.left;
		w.parent = T.parent;
		T.parent = w;
		T.left = w.right;
		if (w.right != null)
			w.right.parent = T;
		w.right = T;

		T.height = Math.max(h(T.left), h(T.right)) + 1;
		w.height = Math.max(h(w.left), h(w.right)) + 1;

		return w;
	}

	protected BSTNode insert(BSTNode T, String v) {
		if (T == null)
			return new BSTNode(v); // insertion point is found

		if (T.key.compareTo(v) < 0) { // search to the right
			T.right = insert(T.right, v);
			T.right.parent = T;
		} else { // search to the left
			T.left = insert(T.left, v);
			T.left.parent = T;
		}

		int balance = h(T.left) - h(T.right);
		if (balance == 2) { // left heavy
			int balance2 = h(T.left.left) - h(T.left.right);
			if (balance2 == 1) {
				T = rotateRight(T);
			} else { // -1
				T.left = rotateLeft(T.left);
				T = rotateRight(T);
			}
		} else if (balance == -2) { // right heavy
			int balance2 = h(T.right.left) - h(T.right.right);
			if (balance2 == -1)
				T = rotateLeft(T);
			else { // 1
				T.right = rotateRight(T.right);
				T = rotateLeft(T);
			}
		}

		T.height = Math.max(h(T.left), h(T.right)) + 1;
		return T; // return the updated AVL
	}

}
