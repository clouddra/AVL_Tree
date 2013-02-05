class BSTNode {

	int height, rank;
	String key;
	BSTNode parent, left, right;

	BSTNode(String v) {
		key = v;
		parent = left = right = null;
		height = 0;
		rank = -1 ;
	}

};
