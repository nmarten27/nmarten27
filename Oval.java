/**
 * Creates a simple graphical oval.
 *
 * @author David Riley
 * @author M. Allen
 * @author J. Hursey
 * @author Jason Sauppe
 * Last modified: 2022-11-10
 */
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Oval extends JComponent {

    /**
     * post: getX() == x and getY() == y
     * and getWidth() == w and getHeight() == h
     * and getBackground() == Color.black
     */
    public Oval(int x, int y, int w, int h) {
        super();
        setBounds(x, y, w, h);
        setBackground(Color.BLACK);
    }

    /**
     * @param comp JComponent to test for intersection
     * @return true iff this intersects
     */
    public boolean intersects(JComponent comp) {
        if (this.getX() >= comp.getX() &&
            this.getX() <= comp.getX() + comp.getWidth() &&
            this.getY() >= comp.getY() &&
            this.getY() <= comp.getY() + comp.getHeight()) {
            return true;
        }
        if (comp.getX() >= this.getX() &&
            comp.getX() <= this.getX() + this.getWidth() &&
            comp.getY() >= this.getY() &&
            comp.getY() <= this.getY() + this.getHeight()) {
            return true;
        }
        return false;
    }

    /**
     * @param x Coordinate
     * @param y Coordinate
     * @return true iff the (x,y) point lies inside this shape's bounding box
     */
    public boolean pointIntersects(int x, int y) {
        return this.getX() <= x && x <= this.getX() + this.getWidth() &&
               this.getY() <= y && y <= this.getY() + this.getHeight();
    }

    /**
     * post: Draws a filled Oval
     * and the upper left corner of the bounding rectangle is ( getX(), getY() )
     * and the oval's dimensions are getWidth() and getHeight()
     * and the oval's color is getBackground()
     */
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillOval(0, 0, getWidth(), getHeight());
        paintChildren(g);
    }

}

