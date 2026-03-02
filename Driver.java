/**
 * CS 220 Assignment 06: Driver for the Maze Solver
 *
 * @author J. Hursey, S. Foley, J. Sauppe
 * Last Modified: 2017-04-21
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;

public class Driver implements ActionListener {

    /** Window for display */
    private JFrame window;

    /** Panel to show the maze in */
    private JPanel mazePanel;

    /** Status message label */
    private JLabel statusInfo;

    /** User interaction buttons */
    private JButton loadMazeBtn;
    private JButton stackModeBtn;
    private JButton queueModeBtn;
    private JButton stepBtn;
    private JButton runBtn;

    /** File Chooser */
    private JFileChooser fileChooser;
    /** A short name for the file - used to display to the user only */
    private String mazeFileNameOnly;
    /** The absolute path to the file - used to read the file */
    private String mazeFileNamePath;

    /** Timer for the "Run" Button */
    private Timer timer;

    /** The current maze */
    private MazeTile[][] currentMaze;

    /** Solver */
    private Solver solver;

    // Constants for graphics layout
    public static final int BUTTON_WIDTH  = 160;
    public static final int BUTTON_HEIGHT = 30;
    public static final int MAZE_WIDTH    = 500;
    public static final int MAZE_HEIGHT   = 500;
    public static final int MAZE_X        = 10;
    public static final int MAZE_Y        = 120;

    /**
     * Where the program starts
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        new Driver();
    }

    /**
     * Maze Driver constructor; sets up the GUI window.
     */
    public Driver() {
        currentMaze = null;
        solver = null;

        // Setup the Window
        window = new JFrame("A Maze Solver");
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().setBackground(new Color(175, 238, 238));
        window.pack();
        window.setLocation(10, 10);
        int windowWidth = MAZE_WIDTH + 20
                        + window.getInsets().left + window.getInsets().right;
        int windowHeight = MAZE_HEIGHT + MAZE_Y + 10
                         + window.getInsets().top + window.getInsets().bottom;
        window.setSize(windowWidth, windowHeight);

        // Setup File Chooser
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        FileNameExtensionFilter filter;
        filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        // Setup labels/buttons/etc
        int xPos = 10;
        int yPos = 10;
        loadMazeBtn = new JButton("Load Maze");
        loadMazeBtn.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadMazeBtn.addActionListener(this);
        window.add(loadMazeBtn, 0);

        xPos += BUTTON_WIDTH + 10;
        stackModeBtn = new JButton("Solve with Stack");
        stackModeBtn.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        stackModeBtn.addActionListener(this);
        window.add(stackModeBtn, 0);

        xPos += BUTTON_WIDTH + 10;
        queueModeBtn = new JButton("Solve with Queue");
        queueModeBtn.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        queueModeBtn.addActionListener(this);
        window.add(queueModeBtn, 0);

        xPos = 105;
        yPos += BUTTON_HEIGHT + 10;
        stepBtn = new JButton("Step");
        stepBtn.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        stepBtn.addActionListener(this);
        window.add(stepBtn, 0);

        xPos += BUTTON_WIDTH + 10;
        runBtn = new JButton("Run");
        runBtn.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        runBtn.addActionListener(this);
        window.add(runBtn, 0);

        // Status Box
        statusInfo = new JLabel("No maze loaded.");
        statusInfo.setBackground(window.getContentPane().getBackground());
        statusInfo.setBounds(10, 90, MAZE_WIDTH, 20);
        window.add(statusInfo, 0);

        // Add an empty panel
        mazePanel = new JPanel(null);
        mazePanel.removeAll();
        mazePanel.setBounds(MAZE_X, MAZE_Y, MAZE_WIDTH, MAZE_HEIGHT);
        mazePanel.setBackground(Color.GRAY);
        window.add(mazePanel, 0);

        // Timer for animation with "Run" Button
        timer = new Timer(100, this);

        // Make visible and request focus for the window
        window.setVisible(true);
        window.repaint();
        window.requestFocus();
    }

    /*
     * Method to respond to events generated from buttons and file choosers
     *
     * (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Load a maze
        if (e.getSource() == loadMazeBtn) {
            chooseInputFile();
            if (mazeFileNamePath != null && mazeFileNamePath.length() > 0) {
                statusInfo.setText("Loading maze file " + mazeFileNameOnly + ".");
                drawMaze(MazeHelper.loadMaze(mazeFileNamePath));
                MazeHelper.initializeNeighbors(currentMaze);
                solver = null; // Reset solver
            }
        } else if (currentMaze == null) {
            // Check if no maze is loaded.
            statusInfo.setText("Error: Load a maze first.");
        } else {
            if (e.getSource() == stackModeBtn ||
                e.getSource() == queueModeBtn) {
                stopTimer();
                resetMaze();
                MazeTile start = MazeHelper.findStart(currentMaze);
                solver = new MySolver();
                if (e.getSource() == stackModeBtn) {
                    solver.initialize(start, true);
                    statusInfo.setText("Solver Mode: Stack-based");
                } else {
                    solver.initialize(start, false);
                    statusInfo.setText("Solver Mode: Queue-based");
                }
            } else if (solver == null) {
                statusInfo.setText("Error: Select a Solver mode");
            } else {
                if (e.getSource() == stepBtn || e.getSource() == timer) {
                    makeSolverProgress();
                } else if (e.getSource() == runBtn) {
                    if (timer.isRunning()) {
                        stopTimer();
                    } else {
                        startTimer();
                    }
                }
            }
        }

        // Make sure to request focus -after- processing the button
        window.repaint();
        window.requestFocus();
    }

    /**
     * Perform one step in the solver process. Update the GUI appropriately
     * if the search has ended.
     */
    private void makeSolverProgress() {
        boolean foundTarget = solver.exploreNext();
        String info = "(" + solver.getNumberOfSteps() + " steps)";
        if (foundTarget) {
            stopTimer();
            statusInfo.setText("Success! The goal has been found! " + info);
        } else if (solver.isFinished()) {
            stopTimer();
            statusInfo.setText("Failed! The goal is unreachable! " + info);
        }
        window.repaint();
    }

    /**
     * Draw a maze on the screen.
     *
     * @param maze The maze to use as reference when coloring the cells.
     */
    private void drawMaze(char[][] maze) {
        int rows = maze.length;
        int cols = maze[0].length;
        int w = MAZE_WIDTH / cols;
        int h = MAZE_HEIGHT / rows;

        // clears existing maze (if any)
        mazePanel.removeAll();
        mazePanel.setBounds(MAZE_X, MAZE_Y, MAZE_WIDTH, MAZE_HEIGHT);

        // lays out the new maze
        currentMaze = new MazeTile[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                currentMaze[r][c] = new MazeTile(maze[r][c]);
                currentMaze[r][c].setBounds(c * w, r * h, w, h);
                mazePanel.add(currentMaze[r][c]);
            }
        }
        window.repaint();
    }

    /**
     * Resets the tiles in the maze to their default colors.
     */
    private void resetMaze() {
        for (int r = 0; r < currentMaze.length; ++r) {
            for (int c = 0; c < currentMaze[r].length; ++c) {
                currentMaze[r][c].reset();
            }
        }
    }

    /**
     * Ask the user for a maze file to open
     *
     * pre: FileChooser object activated.
     * post: If the user chooses a file, its name is saved globally, and is
     * displayed in GUI.
     */
    private void chooseInputFile() {
        int returnVal = fileChooser.showOpenDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            mazeFileNameOnly = fileChooser.getSelectedFile().getName();
            statusInfo.setText("Selected maze file " + mazeFileNameOnly + "." );
            // Make sure to grab the absolute path
            mazeFileNamePath = fileChooser.getSelectedFile().getAbsolutePath();
        }
    }

    /**
     * Start the timer and update the runButton accordingly.
     */
    private void startTimer() {
        runBtn.setText("Stop");
        timer.start();
    }

    /**
     * Stop the timer and update the runButton accordingly.
     */
    private void stopTimer() {
        runBtn.setText("Run");
        timer.stop();
    }

}

