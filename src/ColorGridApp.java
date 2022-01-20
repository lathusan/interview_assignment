import java.util.Random;

public class ColorGridApp {

	private String[] colors;
	private int colCount;
	private int rowCount;
	// Mark already visited node from BFS
	int visitedNodes[][];
	// Mark current largest continues block
	int largestContinuousBlock[][]; 
	// Node count of the largest continues block
	int continuousNodeCount; 

	// Get and set the from demo class
	public ColorGridApp(int colCount, int rowCount, String[] colors) {
		this.colCount = colCount;
		this.rowCount = rowCount;
		this.colors = colors;
		this.visitedNodes = new int[this.colCount][this.rowCount];
		this.largestContinuousBlock = new int[this.colCount][this.rowCount];
	}

	// This method randomly set RGB for entered row and column count
	public String[][] initGrid() {
		String arr[][] = new String[colCount][rowCount];
		// Import and using the random class
		Random random = new Random();
		for (int i = 0; i < colCount * rowCount; i++) {
			int x = i % colCount;
			int y = (int) Math.floor(i / colCount);
			arr[x][y] = colors[random.nextInt(colors.length)];
		}
		return arr;
	}

	// Find largest continuous grid
	public void findLargestContinuesGrid(String grid[][]) {
		int currentMax = Integer.MIN_VALUE;

		for (int i = 0; i < colCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				resetVisitedNodes();
				continuousNodeCount = 0;

				// Traverse to right
				if (j + 1 < rowCount)
					BreadthFirstSearch(grid[i][j], grid[i][j + 1], i, j, grid);

				// Reset if new max found
				if (continuousNodeCount >= currentMax) {
					currentMax = continuousNodeCount;
					resetLargestContinuousBlock(grid[i][j], grid);
				}
				resetVisitedNodes();
				continuousNodeCount = 0;

				// Traverse to down
				if (i + 1 < colCount)
					BreadthFirstSearch(grid[i][j], grid[i + 1][j], i, j, grid);

				// Reset if new max found
				if (continuousNodeCount >= currentMax) {
					currentMax = continuousNodeCount;
					resetLargestContinuousBlock(grid[i][j], grid);
				}
			}
		}
		printLargestContinuousBlock(grid, currentMax);
	}

	// Check validity of the node
	private boolean isValidNode(int x, int y, String grid, String[][] grid3) {
		if (x < colCount && y < rowCount && x >= 0 && y >= 0) {
			if (visitedNodes[x][y] == 0 && grid3[x][y] == grid)
				return Boolean.TRUE;
			else
				return Boolean.FALSE;
		} else
			return Boolean.FALSE;
	}

	// BFS to find all connected nodes for given color
	private void BreadthFirstSearch(String grid, String grid2, int i, int j, String[][] grid3) {
		// Terminate BFS
		if (grid != grid2)
			return;

		visitedNodes[i][j] = 1;
		continuousNodeCount++;

		// Possible movement for coordinate
		int xAxisMovements[] = { 0, 0, 1, -1 };
		int yAxisMovements[] = { 1, -1, 0, 0 };

		// Traverse for all four possible movements
		for (int u = 0; u < 4; u++) {
			if ((isValidNode(i + yAxisMovements[u], j + xAxisMovements[u], grid, grid3)) == true) {
				BreadthFirstSearch(grid, grid2, i + yAxisMovements[u], j + xAxisMovements[u], grid3);
			}
		}
	}

	// Method to reset visited nodes grid prior to each new BFS
	private void resetVisitedNodes() {
		for (int i = 0; i < colCount; i++)
			for (int j = 0; j < rowCount; j++)
				visitedNodes[i][j] = 0;
	}
	
	//	Print RGB Random Box
	public void printColorGrid(String grid[][]) {
		System.out.println();
		for (int i = 0; i < colCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	// Method to reset largest continuous block if new block found
	private void resetLargestContinuousBlock(String grid, String[][] grid2) {
		for (int i = 0; i < colCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				if (visitedNodes[i][j] == 1 && grid2[i][j] == grid) {
					largestContinuousBlock[i][j] = visitedNodes[i][j];
				} else {
					largestContinuousBlock[i][j] = 0;
				}
			}
		}
	}

	// Print large continue color block
	private void printLargestContinuousBlock(String[][] grid, int nodeCount) {

		// prints the largest continuous block
		for (int i = 0; i < colCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				if (largestContinuousBlock[i][j] != 0)
					System.out.print(grid[i][j] + " ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
		System.out.println("\nThe largest continues color grid count is : " + nodeCount);
	}

}