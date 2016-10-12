package cn.xz.tree;

import cn.xz.tree.MyBinaryTree.TreeNode;

/**
 * 排序二叉树
 * 
 * @author xz-ha
 *
 */
public class SortBinaryTree {

	public Node mRoot;// 排序二叉树根节点

	public class Node {
		public int data;
		public Node leftNode;
		public Node rightNode;

		Node(int data) {
			this.data = data;
			this.leftNode = null;
			this.rightNode = null;
		}
	}

	/**
	 * 查找节点
	 * 
	 * @param rootNode
	 *            根节点
	 * @param value
	 *            数值
	 * @return true or false
	 */
	public boolean findNode(Node rootNode, int value) {
		if (rootNode == null) {// 根节点不存在
			return false;
		}
		if (value < rootNode.data) {
			// 插入左孩子
			return findNode(rootNode.leftNode, value);
		} else if (value > rootNode.data) {
			return findNode(rootNode.rightNode, value);
		} else {
			return true;
		}

	}

	/**
	 * 插入一个数值
	 * 
	 * @param rootNode
	 *            根节点
	 * @param value
	 *            数值
	 * @return true or false
	 */
	public boolean insertValue(Node rootNode, int value) {
		if (rootNode == null) {
			this.mRoot = new Node(value);
			return true;
		}
		if (value < rootNode.data) {
			// 插入左孩子

			if (rootNode.leftNode != null) {
				return insertValue(rootNode.leftNode, value);
			} else {
				rootNode.leftNode = new Node(value);

				return true;
			}

		} else if (value > rootNode.data) {
			if (rootNode.rightNode != null) {
				return insertValue(rootNode.rightNode, value);
			} else {
				rootNode.rightNode = new Node(value);

				return true;
			}

		} else {
			return false;
		}
	}

	public boolean deleteTreeNode(int value) {
		return deleteTreeNode(this.mRoot, value);
	}

	/**
	 * 删除根节点下的某个值为value的节点
	 * 
	 * @param rootNode
	 *            根节点
	 * @return
	 */
	public boolean deleteTreeNode(Node rootNode, int value) {
		if (rootNode == null) {// 根节点不存在
			return false;
		}
		if (value < rootNode.data) {
			// 插入左孩子
			return deleteTreeNode(rootNode.leftNode, value);
		} else if (value > rootNode.data) {
			return deleteTreeNode(rootNode.rightNode, value);
		} else {
			deleteNode(rootNode);
			return true;
		}
	}

	/**
	 * 
	 * @param node
	 *            将要被删除的节点
	 */
	public boolean deleteNode(Node node) {

		if (node.leftNode == null && node.rightNode == null) {

		} else if (node.leftNode == null && node.rightNode != null) {

			node = node.rightNode;

		} else if (node.rightNode == null && node.leftNode != null) {

			node = node.leftNode;

		} else {
			Node tempFather = node;
			Node temp = node.leftNode;
			while (temp.rightNode != null) {
				tempFather = temp;// 最终会是最右侧节点的父节点，注：最右侧节点可能还有子节点
				temp = temp.rightNode;// 最右边的节点
			}
			node.data = temp.data;// 接上最右边的节点
			if (tempFather == node) {// 删除节点的左孩子没有右侧子节点
				tempFather.leftNode = temp.leftNode;
			} else {// 删除节点的左孩子有右侧子节点
				tempFather.rightNode = temp.leftNode;
			}
		}
		return true;
	}

	/**
	 * 中序优先遍历
	 * 
	 * @param node
	 */
	public void moddleOrder(Node node) {
		if (node != null) {
			moddleOrder(node.leftNode);
			System.out.print(node.data + ",");
			moddleOrder(node.rightNode);
		}
	}

	public static void main(String[] args) {
		int[] input = { 1, 2, 4, 123, 3, 5, 245, 34, 43, 3, 4, 5, 6, 7 };
		SortBinaryTree sortBTree = new SortBinaryTree();
		for (int i : input) {
			sortBTree.insertValue(sortBTree.mRoot, i);
		}
		sortBTree.moddleOrder(sortBTree.mRoot);
		sortBTree.deleteTreeNode(34);
		System.out.println("删除节点");

		sortBTree.moddleOrder(sortBTree.mRoot);
	}
}
