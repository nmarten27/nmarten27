/**
 * A Maze Tile class.
 *
 * @author J. Hursey, S. Foley, J. Sauppe
 * Date: 2017-04-22; 2024-11-30
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.LinkedList;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class MazeTile extends JComponent {

    // Constants for drawing types and colors
    private static final char WALL_CHAR  = 'X';
    private static final char SPACE_CHAR = '_';
    private static final char START_CHAR = 'S';
    private static final char GOAL_CHAR  = 'G';

    private static final Color WALL_COLOR  = Color.BLACK;
    private static final Color SPACE_COLOR = Color.WHITE;
    private static final Color START_COLOR = Color.GREEN;
    private static final Color GOAL_COLOR  = Color.RED;
    private static final Color SEEN_COLOR     = Color.CYAN;
    private static final Color EXPLORED_COLOR = Color.CYAN.darker();

    /** Tracking information for the MazeTile */
    private char type;
    private boolean seen;
    private boolean explored;
    private List<MazeTile> neighbors;

    /**
     * Maze Tile
     * @param t Type of tile
     */
    public MazeTile(char t) {
        super();
        type = t;
        neighbors = new LinkedList<>();
        reset();
    }

    /**
     * Set the background of the tile based on its type
     */
    public void reset() {
    	if (type == SPACE_CHAR) {
        	setBackground(SPACE_COLOR);
        } else if (type == START_CHAR) {
        	setBackground(START_COLOR);
        } else if (type == GOAL_CHAR) {
        	setBackground(GOAL_COLOR);
        } else {
        	setBackground(WALL_COLOR);
        }
        seen = false;
    	explored = false;
    }

    /**
     * Add the given neighbor to this MazeTile's list of neighbors.
     * @param t The neighbor to add
     */
    public void addNeighbor(MazeTile t) {
    	neighbors.add(t);
    }

    /**
     * @return A list of neighbors for this MazeTile
     */
    public List<MazeTile> neighbors() {
    	return neighbors;
    }

    /**
     * @return true if and only if this tile has been marked as seen.
     */
    public boolean isSeen() {
    	return seen;
    }

    /**
     * @return true if and only if this tile has been marked as explored.
     */
    public boolean isExplored() {
    	return explored;
    }

    /**
     * @return true if and only if this tile is the start tile.
     */
    public boolean isStart() {
    	return type == START_CHAR;
    }

    /**
     * @return true if and only if this tile is the goal tile.
     */
    public boolean isGoal() {
    	return type == GOAL_CHAR;
    }

    /**
     * @return true if and only if this tile is a wall tile.
     */
    public boolean isWall() {
    	return type == WALL_CHAR;
    }

    /**
     * @return true if and only if this tile is a space tile.
     */
    public boolean isSpace() {
    	return type == SPACE_CHAR;
    }

    /**
     * Updates the background color of the tile to indicate it has been seen.
     * (Only recolors space tiles)
     */
    public void markSeen() {
    	seen = true;
    	if (isSpace()) {
    		setBackground(SEEN_COLOR);
    	} else if (isStart() || isGoal()) {
            setBackground(getBackground().darker());
        }
    }

    /**
     * Updates the background color of the tile to indicate it has been
     * explored. (Only recolors space tiles)
     */
    public void markExplored() {
    	explored = true;
    	if (isSpace()) {
    		setBackground(EXPLORED_COLOR);
    	} else if (isStart() || isGoal()) {
            setBackground(getBackground().darker());
        }
    }

    /*
     * Utility method used to draw this object on-screen
     * (non-Javadoc)
     * @see javax.swing.JComponent#paint(java.awt.Graphics)
     */
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        paintChildren(g);
    }

}

