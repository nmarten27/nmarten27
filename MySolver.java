/**
 * Implementation of the Solver interface to support the solving of a maze.
 *
 * @author M. Allen, J. Hursey, S. Foley, J. Sauppe, N. Marten
 * Date: 12/10/2024
 */
import java.util.LinkedList;

public class MySolver implements Solver{
	
	private LimitedAccessList<MazeTile> unexplored;
	private int steps;
	
	/**
     * Initializes the instance variables that are necessary for the solver to
     * explore and track its progress through the maze.
     *
     * @param start The starting tile of the maze to solve.
     * @param useLIFO true if the solver should use a LIFO ordering policy,
     *                false if the solver should use a FIFO ordering policy.
     */
	public void initialize(MazeTile start, boolean useLIFO) {
		steps = 0;
		if (useLIFO) {
			unexplored = new LIFOLimitedAccessList<>();
		} else {
			unexplored = new FIFOLimitedAccessList<>();
		}	
		// Starting tile added to list initially and marked as seen.
		unexplored.insert(start);
		start.markSeen();
	}
	
	/**
     * Method to perform a single search iteration. To be called by either the
     * "Step" or "Run" buttons in the GUI. When repeated, performs search to
     * solve the maze.
     *
     * @return true if this step found the goal, false otherwise.
     */
	public boolean exploreNext() {
		// 1. List is empty, then stop.
		if (unexplored.isEmpty()) {
			return false;
		} 
		
		// 2. List not empty, remove the next tile, t, from the list.
		MazeTile t = unexplored.removeNext();	
		++steps;
		
		// 3. Mark t as explored.
		t.markSeen();
		
		// Case where t is the goal.
		if (t.isGoal()) {
			return true;
		}
		// Explore t by looking at all of its neighbors.
		for (MazeTile tile : t.neighbors()) {
			if (!tile.isSeen()) {
				tile.markSeen();
				unexplored.insert(tile);
			}
		}
		// 4.
		return false;
	}
	
	/**
     * Method to indicate whether or not the solver has more work left to do.
     * A solver is finished if it has found the goal or no more tiles remain
     * to be explored.
     * @return true if the solver is done exploring, false otherwise.
     */
	public boolean isFinished() {
		return unexplored.isEmpty();
	}
	
	/**
     * @return The number of steps that have been taken in the solving process.
     */
	public int getNumberOfSteps() {
		return steps;
	}
	
	// First-in, first-out ordering policy.
	private class FIFOLimitedAccessList <E> implements LimitedAccessList<E> {
		
		private LinkedList<E> list = new LinkedList<>();
		
		/**
	     * @return true if and only if there are no data elements in the list.
	     */
		public boolean isEmpty() {
			return list.isEmpty();
		}
		
		/**
	     * Adds an element to the list in queue order.
	     *
	     * @param element The element to be added to the list.
	     */
		public void insert(E element) {
			list.addLast(element);
		}
		
		/**
	     * Returns but does not remove the next element of the list.
	     *
	     * @return The next element in the list.
	     */
		public E inspectNext() {
			return list.getFirst();
		}
		
		/**
	     * Removes and returns the next element of the list, in the queue.
	     *
	     * @return The next element in the list.
	     */
		public E removeNext() {
			return list.removeFirst();
		}
	}
	
	// Last in, first-out ordering policy.
	private class LIFOLimitedAccessList <E> implements LimitedAccessList<E> {
		
		private LinkedList<E> list = new LinkedList<>();
		
		/**
	     * @return true if and only if there are no data elements in the list.
	     */
		public boolean isEmpty() {
			return list.isEmpty();
		}
		
		/**
	     * Adds an element to the list in stack order.
	     *
	     * @param element The element to be added to the list.
	     */
		public void insert(E element) {
			list.addLast(element);
		}
		
		/**
	     * Returns but does not remove the next element of the list.
	     *
	     * @return The next element in the list.
	     */
		public E inspectNext() {
			return list.getLast();
		}
		
		/**
	     * Removes and returns the next element of the list, in the stack.
	     *
	     * @return The next element in the list.
	     */
		public E removeNext() {
			return list.removeLast();
		}
	}	
}