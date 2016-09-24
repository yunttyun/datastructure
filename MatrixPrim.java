package cn.xz.graph.graphme;

/**
 * 最小生成树,Prim算法;图使用邻接矩阵存储
 * 
 * @author xz-ha,made by xz
 * @version 1.0,2016-9-24
 */
public class MatrixPrim {
	private static final int INF = Integer.MAX_VALUE; // 最大值
	public String[] nodeNames; // 顶点信息
	public int[][] adjacencyMatrix; // 带权值的邻接矩阵
	public int nodeLength;

	/**
	 * 使用邻接矩阵构造
	 * 
	 * @param nodeNames
	 * @param adjacencyMatrix
	 */
	public MatrixPrim(String[] nodeNames, int[][] adjacencyMatrix) {
		this.nodeLength = nodeNames.length;
		// 实例化
		this.nodeNames = new String[this.nodeLength];
		for (int i = 0; i < this.nodeLength; i++) {
			this.nodeNames[i] = nodeNames[i];
		}

		this.adjacencyMatrix = new int[adjacencyMatrix.length][adjacencyMatrix[0].length];
		for (int i = 0; i < adjacencyMatrix.length; i++) {
			for (int j = 0; j < adjacencyMatrix[0].length; j++) {

				this.adjacencyMatrix[i][j] = adjacencyMatrix[i][j];
			}
		}

	}

	/**
	 * prim算法生成最小生成树,时间复杂度O(n^2)
	 * 
	 * @param start
	 */
	public void prim(int start) {

		if (this.nodeLength == 0) {
			return;
		}
		int[] nodesBefore = new int[this.nodeLength];// 记录遍历过的顶点，存储值始终有序，做查询最短路径用
		for (int i = 0; i < this.nodeLength; i++) {
			// 实例化每一个元素都为-1,即为空,后续遍历过的位置设置对应顶点序号
			nodesBefore[i] = -1;
		}
		int[] nodesAfter = new int[this.nodeLength];// 即将遍历的顶点,存储值始终有序，做查询最短路径用
		for (int i = 0; i < this.nodeLength; i++) {
			// 实例化每一个元素都为对应顶点序号，后续遍历过的对应位置设置为-1
			nodesAfter[i] = i;
		}
		nodesBefore[start] = start;
		nodesAfter[start] = -1;
		int[] ergodic = new int[this.nodeLength];// 最短路径顺序,存储值为索引
		ergodic[0] = start; // 起始顶点赋值
		for (int i = 1; i < this.nodeLength; i++) {
			int nextIndex = findMinAdjacencyEdge(nodesBefore, nodesAfter);
			if (nextIndex != -1) {
				nodesBefore[nextIndex] = nextIndex;
				nodesAfter[nextIndex] = -1;
				ergodic[i] = nextIndex;
			}
		}
		// 打印输出，最小生成树prim顶点顺序
		System.out.println("最小生成树prim顶点顺序");
		for (int i = 0; i < ergodic.length; i++) {
			System.out.print(i + " ");

		}
		System.out.printf("\n");
		for (int i = 0; i < ergodic.length; i++) {
			System.out.print(this.nodeNames[ergodic[i]] + " ");
		}
		System.out.printf("\n");

	}

	/**
	 * 根据已遍历顶点，和未遍历顶点，找到最小的邻接边，返回下一个顶点;时间复杂度O(n^2)
	 * 
	 * @param nodesBefore
	 *            已遍历顶点，数组存储索引，未遍历的位置为-1
	 * @param nodesAfter
	 *            未遍历顶点,数组存储索引，已遍历的位置为-1
	 * @return 返回-1，则为error; 返回数值，为下一个顶点索引
	 */
	private int findMinAdjacencyEdge(int[] nodesBefore, int[] nodesAfter) {

		boolean flag = false;// min中是否存储了第一个数值
		int min = 0;// min存储第一个合法到下一顶点的路径权值
		int index = -1;// 下一个顶点索引
		for (int i = 0; i < this.nodeLength; i++) {

			if (nodesBefore[i] != -1) {// 查找

				for (int j = 0; j < this.nodeLength; j++) {
					if (nodesAfter[j] != -1) {
						if (flag == false) {
							min = this.adjacencyMatrix[i][j];
							index = j;
							flag = true;// 最小值已赋值
						}

						else {
							if (this.adjacencyMatrix[i][j] < min) {
								min = this.adjacencyMatrix[i][j];// 寻找替换最短路径下一个顶点索引
								index = j;
							}
						}
					}
				}
			}
		}

		return index;
	}

	/**
	 * prim算法生成最小生成树
	 * 
	 * @param node
	 *            顶点信息
	 */
	public void prim(String node) {
		String[] primNodes = new String[this.nodeLength];// 排序后的顶点数组
		primNodes[0] = node;

	}

	public static void main(String[] args) {
		String[] vexs = { "A", "B", "C", "D", "E", "F", "GG" };
		int matrix[][] = {
				/* A *//* B *//* C *//* D *//* E *//* F *//* G */
				/* A */ { 0, 12, INF, INF, INF, 16, 14 }, /* B */ { 12, 0, 10, INF, INF, 7, INF },
				/* C */ { INF, 10, 0, 3, 5, 6, INF }, /* D */ { INF, INF, 3, 0, 4, INF, INF },
				/* E */ { INF, INF, 5, 4, 0, 2, 8 }, /* F */ { 16, 7, 6, INF, 2, 0, 9 },
				/* G */ { 14, INF, INF, INF, 8, 9, 0 } };
		MatrixPrim matrixPrim = new MatrixPrim(vexs, matrix);
		matrixPrim.prim(0);
	}
}
