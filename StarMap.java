/**
 * CS 220: Assignment 2
 * This program displays a star map based off of given star values and displays corresponding constellations that are based off of given values too.
 *
 * @author Nick Marten
 * Last Modified: 10/08/2024
 */
import java.awt.Color;
import java.io.File;
import javax.swing.JFrame;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;

public class StarMap {

    /** Interior size of window (drawing region) */
    private int drawingRegionWidth = 800;

    /** File containing the star data (default) */
    private String starFilename =
            "data" + File.separator + "stars.txt";

    /** File containing the constellation data (default) */
    private String constellationFilename =
            "data" + File.separator + "constellations.txt";

    /** Base name of output files (default) */
    private String outputFilename = "output-sky";

    /** Colors to use for drawing constellations */
    public static final Color[] COLORS = {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.YELLOW,
            Color.PINK,
            Color.MAGENTA,
            Color.ORANGE,
            Color.CYAN
    };

    /** Window to draw in */
    private JFrame window;

    /**
     * Main method - where everything starts.
     *
     * @param args Command-line arguments (ONLY USED DURING GRADING)
     */

    public static void main(String[] args) {
        /* DO NOT MODIFY THIS METHOD! */
       // Create and use the StarMap
        StarMap sm = new StarMap(args);
        sm.displaySky();
        sm.saveSky();
    }

    /**
     * A StarMap constructor
     *
     * @param args Command-line arguments (ONLY USED DURING GRADING)
     */
    public StarMap(String[] args) {
        /* DO NOT MODIFY THIS METHOD! */
        // Process command-line arguments (if any)
        if (args.length > 0) {
            this.drawingRegionWidth = Integer.parseInt(args[0]);
        }
        if (args.length > 1) {
            this.starFilename = args[1];
        }
        if (args.length > 2) {
            this.constellationFilename = args[2];
        }
        if (args.length > 3) {
            this.outputFilename = args[3];
        }
        // Create the window
        this.window = new JFrame();
        this.window.setLayout(null);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setLocation(20, 20);
        this.window.pack();
        int width  = this.drawingRegionWidth
                   + window.getInsets().left   + window.getInsets().right;
        int height = this.drawingRegionWidth
                   + window.getInsets().bottom + window.getInsets().top;
        this.window.setSize(width, height);
        this.window.setTitle("Star Map");
        this.window.getContentPane().setBackground(Color.BLACK);
        this.window.setVisible(true);
    }

    /**
     * Display all of the stars and constellations in the sky.
     */
    
    private Star[] stars;
    public void displaySky() {
        // TODO: Implement this
    	try {
    	int starCount = 0;
        Scanner fileScanner = new Scanner(new File(starFilename));
        
        while (fileScanner.hasNextLine()) {
            fileScanner.nextLine();
            ++starCount;
        }
        fileScanner.close();

        stars = new Star[starCount];
        
        fileScanner = new Scanner(new File(starFilename));
        int i = 0;
        int w = window.getContentPane().getWidth();
        int h = window.getContentPane().getHeight();
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            stars[i] = new Star(line);
            stars[i].updateSize();
            stars[i].updateLocation(w, h);
            window.add(stars[i]);            
            ++i;
        }
        fileScanner.close();
        
        drawConstellations(stars);
                
    	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			// NOTE: Leave this at the end of this method
			window.repaint();
    	}
    }
    
    /*
     * Finds a star in the array and returns its name.
     */
    
    private Star findStarByName(Star[] stars, String name) {
    	for (Star star : stars) {
    		String[] starNames = star.getNames().split(";");
    		// TODO: Split the names on ;
    		// Check each individual name against the 'name' param w/ .equals
    		for (String starName : starNames) {
    			if (starName.trim().equals(name)) {
    				return star;
    			}
    		}
    	}
    	return null;
    }

    /*
     * Takes the constellation file, trims and splits it to draw lines between different stars and form constellations.
     */
    
    private void drawConstellations(Star[] stars) {
        try {
            Scanner fileScanner = new Scanner(new File(constellationFilename));
            int colorIndex = 0;
            boolean readingStars = true;
            String line = "";

            while (fileScanner.hasNextLine()) {
            	
            	if(readingStars) {
            		line = fileScanner.nextLine().trim();
            	}
                

                if (line.length() > 0 && line.charAt(0) == '#') {
                    Color constellationColor = COLORS[colorIndex];
                    colorIndex = (colorIndex + 1) % COLORS.length;

                    readingStars = true; 
                    while (readingStars && fileScanner.hasNextLine()) {
                        line = fileScanner.nextLine().trim();

                        if (line.length() == 0 || line.charAt(0) == '#') {
                            readingStars = false;
                        } else {
                            String[] starNames = line.split(",");
                            for (int i = 0; i < starNames.length; ++i) {
                                starNames[i] = starNames[i].trim();
                            }

                            //for (int i = 0; i < starNames.length - 1; ++i) {
                                Star starA = findStarByName(stars, starNames[0].trim());
                                Star starB = findStarByName(stars, starNames[1].trim());

                                if (starA != null && starB != null) {
                                    Line constellationLine = new Line(
                                        starA.getX() + starA.getWidth() / 2,
                                        starA.getY() + starA.getHeight() / 2,
                                        starB.getX() + starB.getWidth() / 2,
                                        starB.getY() + starB.getHeight() / 2
                                    );
                                    constellationLine.setBackground(constellationColor);
                                    window.add(constellationLine);
                                }
                            //}
                        }
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Write out the stars' information to both text and binary files.
     */
    
    public void saveSky() {
    	
        if (stars == null) {
            return;
        }

        try {
            FileWriter txt = new FileWriter(outputFilename + ".txt");
            BufferedWriter buf = new BufferedWriter(txt);
            PrintWriter puf = new PrintWriter(buf);
            DataOutputStream bin = new DataOutputStream(new FileOutputStream(outputFilename + ".bin"));

            for (int i = 0; i < stars.length; i++) {
                Star star = stars[i];
                
                if (star != null) {
                    double originalX = star.getOriginalX();
                    double originalY = star.getOriginalY();
                    int windowX = star.getX();
                    int windowY = star.getY();
                    double originalMagnitude = star.getOriginalMagnitude();
                    int displaySize = star.getStarSize();
                    int henryDraperNumber = star.getDraperNumber();

                    puf.println(originalX + " " + originalY + " " + windowX + " " + windowY + " " + 
                              originalMagnitude + " " + displaySize + " " + henryDraperNumber);

                    bin.writeDouble(originalX);
                    bin.writeDouble(originalY);
                    bin.writeInt(windowX);
                    bin.writeInt(windowY);
                    bin.writeDouble(originalMagnitude);
                    bin.writeInt(displaySize);
                    bin.writeInt(henryDraperNumber);
                }
            }
            txt.close();
            bin.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}