/**
 * Maze solver interface that describes the basic operations a solver must
 * support.
 *
 * @author M. Allen, J. Hursey, S. Foley, J. Sauppe
 * Date: 2024-11-30
 */
public interface Solver {

    /**
     * Initializes the instance variables that are necessary for the solver to
     * explore and track its progress through the maze.
     *
     * @param start The starting tile of the maze to solve.
     * @param useLIFO true if the solver should use a LIFO ordering policy,
     *                false if the solver should use a FIFO ordering policy.
     */
    void initialize(MazeTile start, boolean useLIFO);

    /**
     * Method to perform a single search iteration. To be called by either the
     * "Step" or "Run" buttons in the GUI. When repeated, performs search to
     * solve the maze.
     *
     * @return true if this step found the goal, false otherwise.
     */
    boolean exploreNext();

    /**
     * Method to indicate whether or not the solver has more work left to do.
     * A solver is finished if it has found the goal or no more tiles remain
     * to be explored.
     * @return true if the solver is done exploring, false otherwise.
     */
    boolean isFinished();

    /**
     * @return The number of steps that have been taken in the solving process.
     */
    int getNumberOfSteps();

}

