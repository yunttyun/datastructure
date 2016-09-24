package cn.xz.graph.graphme;

/**
 * 图的邻接矩阵写法,无向图
 * 
 * @author xz-ha
 *
 */
public class Matrix {

	private String[] nodeNames; // 顶点信息
	private int[][] adjacencyMatrix; // 邻接矩阵

	/**
	 * 创建图(用已提供的矩阵)
	 * 
	 * @param nodeNames
	 *            点数
	 * @param edges
	 *            数
	 */
	public Matrix(String[] nodeNames, String[][] edges) {

		this.nodeNames = new String[nodeNames.length];
		for (int i = 0; i < nodeNames.length; i++) {
			this.nodeNames[i] = nodeNames[i];
		}

		this.adjacencyMatrix = new int[nodeNames.length][nodeNames.length];
		for (int i = 0; i < edges.length; i++) {
			int start = getPositionInNodes(nodeNames, edges[i][0]);
			int end = getPositionInNodes(nodeNames, edges[i][1]);
			if (start == -1 || end == -1) {
				this.adjacencyMatrix = new int[nodeNames.length][nodeNames.length];
				System.out.println("边与顶点不匹配!邻接矩阵已清空");
				break;
			}
			this.adjacencyMatrix[start][end] = 1;
			//this.adjacencyMatrix[end][start] = 1; //有向图不包含此项

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

	public void print() {
		System.out.printf("Martix Graph:\n");
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++)
				System.out.printf("%d ", adjacencyMatrix[i][j]);
			System.out.printf("\n");
		}
	}

	public static void main(String[] args) {
		String[] vexs = { "A", "B", "C", "D", "E", "asfaf" };
		String[][] edges = new String[][] { { "A", "C" }, { "A", "D" } };
		Matrix matrix = new Matrix(vexs, edges);
		matrix.print();
	}

}
