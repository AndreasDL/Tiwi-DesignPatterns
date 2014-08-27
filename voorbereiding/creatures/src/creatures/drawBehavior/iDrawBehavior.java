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
public interface iDrawBehavior {
    public void draw(Graphics2D g, int cellWidth, int cellHeight);
}
