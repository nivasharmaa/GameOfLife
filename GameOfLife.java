package conwaygame;
import java.util.ArrayList;
/**
 * Conway's Game of Life Class holds various methods that will
 * progress the state of the game's board through it's many iterations/generations.
 *
 * Rules 
 * Alive cells with 0-1 neighbors die of loneliness.
 * Alive cells with >=4 neighbors die of overpopulation.
 * Alive cells with 2-3 neighbors survive.
 * Dead cells with exactly 3 neighbors become alive by reproduction.

 * @author Seth Kelley 
 * @author Maxwell Goldberg
 */
public class GameOfLife {

    // Instance variables
    private static final boolean ALIVE = true;
    private static final boolean  DEAD = false;

    private boolean[][] grid;    // The board has the current generation of cells
    private int totalAliveCells; // Total number of alive cells in the grid (board)

    /**
    * Default Constructor which creates a small 5x5 grid with five alive cells.
    * This variation does not exceed bounds and dies off after four iterations.
    */
    public GameOfLife() {
        grid = new boolean[5][5];
        totalAliveCells = 5;
        grid[1][1] = ALIVE;
        grid[1][3] = ALIVE;
        grid[2][2] = ALIVE;
        grid[3][2] = ALIVE;
        grid[3][3] = ALIVE;
    }

    /**
    * Constructor used that will take in values to create a grid with a given number
    * of alive cells
    * @param file is the input file with the initial game pattern formatted as follows:
    * An integer representing the number of grid rows, say r
    * An integer representing the number of grid columns, say c
    * Number of r lines, each containing c true or false values (true denotes an ALIVE cell)
    */
    public GameOfLife (String file) {

        // WRITE YOUR CODE HERE
        StdIn.setFile(file);

        // Read the number of rows and columns
        int rows = StdIn.readInt();
        int cols = StdIn.readInt();

        // Initialize the grid
        grid = new boolean[rows][cols];

        // Read the cell states and populate the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = StdIn.readBoolean();

                if (grid[i][j]) {
                    totalAliveCells++;
                }
            }
        }

    }

    /**
     * Returns grid
     * @return boolean[][] for current grid
     */
    public boolean[][] getGrid () {
        return grid;
    }
    
    /**
     * Returns totalAliveCells
     * @return int for total number of alive cells in grid
     */
    public int getTotalAliveCells () {
        return totalAliveCells;
    }

    /**
     * Returns the status of the cell at (row,col): ALIVE or DEAD
     * @param row row position of the cell
     * @param col column position of the cell
     * @return true or false value "ALIVE" or "DEAD" (state of the cell)
     */
    public boolean getCellState (int row, int col) {

        // WRITE YOUR CODE HERE
        if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length) {
            return this.grid[row][col];
        } else {
            return false;
        }
    }
    
       

    /**
     * Returns true if there are any alive cells in the grid
     * @return true if there is at least one cell alive, otherwise returns false
     */
    public boolean isAlive () {

        // WRITE YOUR CODE HERE
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    return true;
                }
            }
        }
    
        return false;
    }

    /**
     * Determines the number of alive cells around a given cell.
     * Each cell has 8 neighbor cells which are the cells that are 
     * horizontally, vertically, or diagonally adjacent.
     * 
     * @param col column position of the cell
     * @param row row position of the cell
     * @return neighboringCells, the number of alive cells (at most 8).
     */
    public int numOfAliveNeighbors (int row, int col) {
        int aliveNeighbors = 0;

    // Define the range for neighboring positions
    int numRows = grid.length;
    int numCols = grid[0].length;

    // Iterate over neighboring positions
    for (int i = row - 1; i <= row + 1; i++) {
        for (int j = col - 1; j <= col + 1; j++) {
            // Skip the cell itself
            if (i == row && j == col) {
                continue;
            }
            // Adjust for wrap-around effect at grid edges
            int newRow = (i + numRows) % numRows;
            int newCol = (j + numCols) % numCols;
            // Check if the neighboring cell is alive
            if (grid[newRow][newCol] == ALIVE) {
                aliveNeighbors++;
            }
        }
    }

    return aliveNeighbors;  
        // WRITE YOUR CODE HERE
         // update this line, provided so that code compiles
    }

    /**
     * Creates a new grid with the next generation of the current grid using 
     * the rules for Conway's Game of Life.
     * 
     * @return boolean[][] of new grid (this is a new 2D array)
     */
    public boolean[][] computeNewGrid () {
        // Create a new grid for the next generation
    boolean[][] newGrid = new boolean[grid.length][grid[0].length];

    // Iterate over each cell in the current grid
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            // Get the number of alive neighbors for the current cell
            int aliveNeighbors = numOfAliveNeighbors(row, col);

            // Apply Conway's Game of Life rules to determine the state of the cell in the new grid
            if (grid[row][col] == ALIVE) {
                // Rule 1 and Rule 4
                if (aliveNeighbors <= 1 || aliveNeighbors >= 4) {
                    // Alive cells with no neighbors or four or more neighbors die
                    newGrid[row][col] = DEAD;
                } else {
                    // Rule 3: Alive cells with two or three neighbors survive
                    newGrid[row][col] = ALIVE;
                }
            } else {
                // Rule 2: Dead cells with exactly three neighbors become alive
                if (aliveNeighbors == 3) {
                    newGrid[row][col] = ALIVE;
                } else {
                    // Dead cells with other than three neighbors remain dead
                    newGrid[row][col] = DEAD;
                }
            }
        }
    }

    // Return the new grid representing the next generation
    return newGrid;
       

        // WRITE YOUR CODE HERE
       // update this line, provided so that code compiles
    }

    /**
     * Updates the current grid (the grid instance variable) with the grid denoting
     * the next generation of cells computed by computeNewGrid().
     * 
     * Updates totalAliveCells instance variable
     */
    public void nextGeneration () {
        // Compute the new grid for the next generation
    boolean[][] newGrid = computeNewGrid();

    // Update the current grid with the new grid
    for (int row = 0; row < grid.length; row++) {
        for (int col = 0; col < grid[0].length; col++) {
            grid[row][col] = newGrid[row][col];
        }
    }
        // WRITE YOUR CODE HERE
    }

    /**
     * Updates the current grid with the grid computed after multiple (n) generations. 
     * @param n number of iterations that the grid will go through to compute a new grid
     */
    public void nextGeneration (int n) {
        for (int i = 0; i < n; i++) {
            nextGeneration();
        }
        
    

        // WRITE YOUR CODE HERE
    }

    /**
     * Determines the number of separate cell communities in the grid
     * @return the number of communities in the grid, communities can be formed from edges
     */
    public int numOfCommunities() {

        // WRITE YOUR CODE HERE
        int n=grid.length, m=grid[0].length;

        WeightedQuickUnionUF woof = new WeightedQuickUnionUF(n,m);
        
        for(int row=0; row<n; row++)
        {
            for(int col=0; col<m; col++){
                if(grid[row][col] == ALIVE)
                {
                    for (int i=row-1; i<=row+1; i++){
                        for (int j=col-1;j<=col+1;j++ )
                        {
                            int r=i, c=j;
                            if(j<0) c=m-1;
                            if(i<0) r=n-1;
                            if(j>=m) c=0;
                            if(i>=n) r=0;

                            if (grid[r][c]==ALIVE){
                                woof.union(r,c,row,col);
                            }
                        }
                    }
                }
            }
        }

        // Set<Integer> parents= new HashSet<Integer>();
        ArrayList<Integer> parents = new ArrayList<>();
        

        for(int row=0; row<n; row++){
            for(int col=0; col<m; col++){

                if(grid[row][col]==ALIVE)
                {parents.add(woof.find(row,col));}
            }
        }
        
        System.out.println(parents);
        // [21,23,23,21,21,21] parents
        // [21,23] uniqnos
        // Finding unique nos in arraylist
        
        ArrayList<Integer> uniqueNos = new ArrayList<>();
        for(Integer num: parents)
        {
            if(!uniqueNos.contains(num))
            {
                uniqueNos.add(num);
            }
        }
        System.out.println(uniqueNos);
        return uniqueNos.size();
        // return parents.size();
        // return 0;

         // update this line, provided so that code compiles
    }
}