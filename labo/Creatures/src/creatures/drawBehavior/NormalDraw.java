/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.drawBehavior;

import java.awt.Graphics2D;

/**
 *
 * @author Whatever
 */
public class NormalDraw implements IDrawBehavior{

    @Override
    public void draw(Graphics2D g, int cellWidth, int cellHeight) {
        g.fillOval(0, 0, cellWidth - 1, cellHeight - 1);
    }
    
}
