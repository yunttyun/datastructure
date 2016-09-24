package cn.xz.graph.graphme;

/**
 * 图的邻接表实现方法
 * 
 * @author xz-ha
 *
 */
public class AdjacencyList {

	/**
	 * 链表的节点类
	 * 
	 * @author xz-ha
	 *
	 */
	public class LinkedListNode {
		public int nodeIndex; // 图的顶点的代号
		public LinkedListNode nextNode;// 下一个顶点
	}

	/**
	 * 图的顶点类,指向图的顶点
	 * 
	 * @author xz-ha
	 *
	 */
	public class GraphNode {
		public String data; // 数据域
		// public int data1;
		public LinkedListNode firstLinkedListNode; // 链表的首节点
	}

	public GraphNode[] graphNodes; // 图的顶点

	public int length; // 图的顶点数

	/**
	 * 创建图(用已提供的矩阵)
	 * 
	 * @param nodeNames
	 *            顶点,存储的可以是名称
	 * @param edges
	 *            边，可以为有向边，也可以为无向边
	 */
	public AdjacencyList(String[] nodeNames, String[][] edges) {
		this.length = nodeNames.length;
		this.graphNodes = new GraphNode[this.length];
		for (int i = 0; i < this.length; i++) {
			this.graphNodes[i] = new GraphNode();
			this.graphNodes[i].data = new String(nodeNames[i]);// 给顶点赋予数值域

		}

		for (int i = 0; i < edges.length; i++) {
			int start = getPositionInNodes(nodeNames, edges[i][0]);
			int end = getPositionInNodes(nodeNames, edges[i][1]);
			if (start == -1 || end == -1) {
				// 边与顶点不匹配,注: 这里要做容错处理，需要清除当前申请的存储空间
				break;
			}
			if (this.graphNodes[start].firstLinkedListNode == null) {
				// 给顶点赋予第一个链表邻接点
				this.graphNodes[start].firstLinkedListNode = new LinkedListNode();
				this.graphNodes[start].firstLinkedListNode.nodeIndex = end;
			} else {
				LinkedListNode tempNode = this.graphNodes[start].firstLinkedListNode;

				while (true) {
					// 遍历链表，给最后一个节点赋予数值
					if (tempNode.nextNode == null) {
						tempNode.nextNode = new LinkedListNode();
						tempNode.nextNode.nodeIndex = end;
						break;
					}
					tempNode = tempNode.nextNode;
				}

			}
		}
	}

	public void print() {
		System.out.printf(" Graph:\n");
		for (int i = 0; i < this.length; i++) {

			System.out.print(this.graphNodes[i].data);
			if (this.graphNodes[i].firstLinkedListNode != null) {
				LinkedListNode tempNode = this.graphNodes[i].firstLinkedListNode;
				while (true) {
					System.out.print("  " + tempNode.nodeIndex);
					if (tempNode.nextNode != null) {
						tempNode = tempNode.nextNode;
					} else {
						// System.out.print(" " + tempNode.nextNode.nodeIndex);
						break;
					}

				}
			}
			System.out.printf("\n");
		}

	}

	/**
	 * 得到顶点在顶点数组中的位置
	 * 
	 * @param nodeNames
	 * @param nodeName
	 * @return
	 */
	private int getPositionInNodes(String[] nodeNames, String nodeName) {
		for (int j = 0; j < nodeNames.length; j++) {

			if (nodeName == nodeNames[j]) {
				return j;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String[] vexs = { "A", "B", "C", "D", "E", "FFFF" };
		String[][] edges = new String[][] { { "A", "C" }, { "A", "D" },{ "D", "A" } };

		AdjacencyList adjacencyList = new AdjacencyList(vexs, edges);
		adjacencyList.print();
		//System.out.println("over--");
		// matrix.print();
	}

}
