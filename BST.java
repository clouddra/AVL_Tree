class BST {
	protected BSTNode root;
	protected int nodeCount;
	protected boolean updated = false; // updated = false if new nodes are
										// inserted

	protected BSTNode searchStart(BSTNode T, String START) {
		if (T == null)
			return T;
		else if (T.key.equals(START))
			return T; // found
		else if (T.key.compareTo(START) < 0)
			return T.right == null ? successor(T) : searchStart(T.right, START);
		// find next node larger than T if reaches a leaf smaller than T
		else
			return T.left == null ? T : searchStart(T.left, START);
		// found if no more smaller node

	}

	protected BSTNode searchEnd(BSTNode T, String END) {
		if (T == null)
			return T; // not found
		else if (T.key.equals(END))
			return predecessor(T); // find the largest that is smaller than T
		else if (T.key.compareTo(END) < 0)
			return T.right == null ? T : searchEnd(T.right, END);
		// found if no more larger node
		else
			return T.left == null ? predecessor(T) : searchEnd(T.left, END);
		// find next node smaller than T if reach a leaf larger than T
	}

	protected BSTNode predecessor(BSTNode T) {
		if (T.left != null) // this subtree has left subtree
			return findMax(T.left); // the predecessor is the maximum of left
									// subtree
		else {
			BSTNode par = T.parent;
			BSTNode cur = T;
			// if par(ent) is not root and cur(rent) is its left children
			while ((par != null) && (cur == par.left)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par; // this is the successor of T
		}
	}

	protected BSTNode successor(BSTNode T) {
		if (T.right != null) // this subtree has right subtree
			return findMin(T.right); // the successor is the minimum of right
										// subtree
		else {
			BSTNode par = T.parent;
			BSTNode cur = T;
			// if par(ent) is not root and cur(rent) is its right children
			while ((par != null) && (cur == par.right)) {
				cur = par; // continue moving up
				par = cur.parent;
			}
			return par; // this is the successor of T
		}
	}

	protected BSTNode findMin(BSTNode T) {
		if (T == null)
			return null; // not found
		else if (T.left == null)
			return T; // this is the min
		else
			return findMin(T.left); // go to the left
	}

	protected BSTNode findMax(BSTNode T) {
		if (T == null)
			return null; // not found
		else if (T.right == null)
			return T; // this is the max
		else
			return findMax(T.right); // go to the right
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

		return T; // return the updated BST
	}

	protected void updateRank(BSTNode T, int rank) {
		if (T == null)
			return;
		updateRank(T.left, rank);// recursively go to the left
		nodeCount++;
		T.rank = nodeCount;
		updateRank(T.right, rank); // recursively go to the right
	}

	public void updateRank() {
		nodeCount = 0;
		updateRank(root, nodeCount);
		updated = true;
	}

	public int findCount(String START, String END) {

		if (updated == false) // update if tree is not up to date
			updateRank();

		BSTNode startN = searchStart(root, START);
		BSTNode endN = searchEnd(root, END);

		if (startN == null || endN == null)
			return 0;

		return endN.rank - startN.rank + 1;
	}

	public BST() {
		root = null;
	}

	public void insert(String v) {
		updated = false; // tree is not up to date
		root = insert(root, v);
	}

}
