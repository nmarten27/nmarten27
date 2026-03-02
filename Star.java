import java.awt.Color;
import java.util.Scanner;

public class Star extends Oval {
	
        private double x;
        private double y;
        private int draperNumber;
        private double magnitude;
        private int harvardNumber;
        private String names;
        private int size;

        public Star(String line) {
        	super(0, 0, 0 ,0); 
        	Scanner parser = new Scanner(line);
        	this.x = parser.nextDouble();
        	this.y = parser.nextDouble();
        	parser.nextDouble(); // Skip Z Coordinate
        	this.draperNumber = parser.nextInt();
        	this.magnitude = parser.nextDouble();
        	this.harvardNumber = parser.nextInt();
        	if (parser.hasNextLine()) {
        		this.names = parser.nextLine();
        	} else {
        		this.names = " ";
        	}
        	setBackground(Color.WHITE);
        	parser.close();
        }

        public void updateSize() {
            int size = (int) (1 + Math.round(10.0 / (magnitude + 2.0)));
            setSize(size, size);
        }
        
        public void updateLocation(int windowWidth, int windowHeight) {
        	int windowX = (int) (windowWidth * ((x + 1)/2));
        	int windowY = (int) (windowHeight * ((1 - y)/2));
        	setLocation(windowX, windowY); 	
        }

        public double getOriginalX() {
            return x;
        }

        public double getOriginalY() {
            return y;
        }
        
        public int getDraperNumber() {
        	return draperNumber;
        }

        public double getOriginalMagnitude() {
            return magnitude;
        }

        public int getHarvardNumber() {
            return harvardNumber;
        }

        public String getNames() {
            return names;
        }
        
        public int getStarSize() {
        	return size;
        }
    }