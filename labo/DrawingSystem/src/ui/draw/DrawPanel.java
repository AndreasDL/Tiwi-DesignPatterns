/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.draw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import javax.swing.JComponent;
import ui.drawables.Drawable;

/**
 *
 * @author Whatever
 */
public class DrawPanel extends JComponent {

    public DrawPanel() {
        
    }
    
    protected Map<Drawable, Point> positions = new HashMap<>();
    protected java.util.List<Drawable> drawables = new LinkedList<>();

    // pre: drawable not already added
    public void add(Drawable drawable, Point location) {
        drawables.add(drawable);
        positions.put(drawable, location);
        repaint();
    }

    // callback
    public void changed(Drawable source) {
        repaint();
    }

    public Drawable getAtLocation(Point p) {
        for (int i = drawables.size() - 1; i >= 0; i--) {
            Drawable dr = drawables.get(i);
            Point pos = positions.get(dr);
            if (dr.contains(new Point(p.x - pos.x, p.y - pos.y))) {
                return dr;
            }
        }
        return null;
    }

    public int getDrawableCount() {
        return drawables.size();
    }

    public Drawable getLast() {
        if (drawables.isEmpty()) {
            return null;
        } else {
            return drawables.get(drawables.size() - 1);
        }
    }

    // pre: drawable added
    public Point getPosition(Drawable drawable) {
        return positions.get(drawable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Drawable dr : drawables) {
            Point pos = positions.get(dr);
            g2.translate(pos.x, pos.y);
            dr.draw(g2);
            g2.translate(-pos.x, -pos.y);
        }

    }

    // pre: drawable added
    public void setPosition(Drawable drawable, Point pos) {
        positions.put(drawable, pos);
        repaint();
    }
    
}
