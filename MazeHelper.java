/**
 * Static helper methods that are implemented for the Maze Solver.
 *
 * @author M. Allen, J. Hursey, S. Foley, J. Sauppe, N. Marten
 * Date: 12/10/2024
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MazeHelper {
	
    /**
     * Reads and converts the contents of the input file into a 2D
     * character-array that represents a maze.
     *
     * @param file Text file containing maze data.
     * @return char array of the maze
     */	
    public static char[][] loadMaze(String filename) {
    	try {
    		BufferedReader reader = new BufferedReader(new FileReader(filename));
    		char[][] maze = new char[0][];
    		int height = 0;
    		String line;
    		while ((line = reader.readLine()) != null) {
    			if (height == maze.length) {
    				char[][] newMaze = new char [height + 1][];
    				copyArray(maze, newMaze);
    				maze = newMaze;			
    			}
    			char[] charArray = new char[line.length()];
    			copyLine(line, charArray);
    			maze[height] = charArray;
    			++height;
    		}
    		reader.close();
    		return maze;
    	} catch (IOException e) {
    		e.printStackTrace();
    		return null;
    	} 
    }
    
    // Helper method to copy an array.
    private static void copyArray(char[][] maze, char[][] newMaze) {
    	for (int i = 0; i < maze.length; ++i) {
    		newMaze[i] = maze[i];
    	}
    }
    
    // Helper method to copy a given line.
    private static void copyLine(String line, char[] charArray) {
    	for (int i = 0; i < charArray.length; ++i) {
    		charArray[i] = line.charAt(i);
    	}
    }
	
    /**
     * Returns the starting tile from the given maze.
     *
     * @param maze The maze for which to find the starting tile.
     * @return The starting tile, or null if not found.
     */
    public static MazeTile findStart(MazeTile[][] maze) {
    	for (int i = 0; i < maze.length; ++i) {
    		for (int j = 0; j < maze[0].length; ++j) {
    			if (maze[i][j].isStart()) {
    				return maze[i][j];
    			}
    		}
    	}
    	return null;
    }

    /**
     * Identifies the neighbors for each tile in the maze.
     *
     * A MazeTile's addNeighbor(..) instance method can be used to associate
     * another MazeTile as a neighbor.
     *
     * Walls have no neighbors nor are they neighbors of any other tile.
     *
     * Each MazeTile should have its neighbors stored in the order North, West,
     * South, East (that is, counterclockwise from the north).
     *
     * @param maze The maze for which to construct the neighbor links.
     */
    public static void initializeNeighbors(MazeTile[][] maze) {
        for (int i = 0; i < maze.length; ++i) {
            for (int j = 0; j < maze[0].length; ++j) {
                MazeTile cur = maze[i][j];
                setNeighbors(maze, cur, i, j);
            }
        }
    }
    
    // Helper method to set neighbors that are adjacent to the current tile.
    private static void setNeighbors(MazeTile[][] maze, MazeTile cur, int row, int col) {
    	if (valid(maze, row - 1, col)) {
    		cur.addNeighbor(maze[row - 1][col]);
    	}
    	if (valid(maze, row, col - 1)) {
    		cur.addNeighbor(maze[row][col - 1]);
    	} 
    	if (valid(maze, row + 1, col)) {
    		cur.addNeighbor(maze[row + 1][col]);
    	}
    	if (valid(maze, row, col + 1)) {
    		cur.addNeighbor(maze[row][col + 1]);
    	}
    }
    
    // Helper method to check if tile is valid or not.
    private static boolean valid(MazeTile[][] maze, int row, int col) {
		return row >= 0 && col >= 0 && row < maze.length && col < maze[0].length && !maze[row][col].isWall();
	}
}