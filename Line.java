/**
 * Creates a simple graphical line.
 *
 * @author David Riley
 * @author M. Allen
 * @author Jason Sauppe
 * Last Modified: 2022-11-10
 */
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;

@SuppressWarnings("serial")
public class Line extends JComponent {

    /** Line information */
    private int thickness;
    private int x1, y1, x2, y2;

    public Line(int x1, int y1, int x2, int y2) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thickness = 1;
        setBounds(Math.min(x1, x2) - 1, Math.min(y1, y2) - 1,
                  Math.max(x1, x2) + 2, Math.max(y1, y2) + 2);
        setBackground(Color.BLACK);
    }

    public Line(int x1, int y1, int x2, int y2, int t) {
        super();
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thickness = t;
        setBounds(Math.min(x1, x2) - t - 1, Math.min(y1, y2) - t - 1,
                  Math.max(x1, x2) + t + 1, Math.max(y1, y2) + t + 1);
        setBackground(Color.BLACK);
    }

    public void setThickness(int t) {
        this.thickness = t;
        setBounds(Math.min(x1, x2) - t - 1, Math.min(y1, y2) - t - 1,
                  Math.max(x1, x2) + t + 1, Math.max(y1, y2) + t + 1);
    }

    /**
     * postconditon this method draws a line segment from point (getX(),getY())
     * to point (x2, y2) in the background coordinate system and with color from
     * getBackground()
     */
    public void paint(Graphics g) {
        ((Graphics2D) g).setStroke(new BasicStroke(thickness,
                                                   BasicStroke.CAP_BUTT,
                                                   BasicStroke.JOIN_BEVEL));
        g.setColor(this.getBackground());
        g.drawLine(x1 - getX(), y1 - getY(), x2 - getX(), y2 - getY());
        ((Graphics2D) g).setStroke(new BasicStroke(1.0f));
    }

}

