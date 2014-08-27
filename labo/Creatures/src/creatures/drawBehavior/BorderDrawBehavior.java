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
public class BorderDrawBehavior implements IDrawBehavior{
    IDrawBehavior drawBehavior;
    
    public BorderDrawBehavior(IDrawBehavior drawBehavior){
        this.drawBehavior = drawBehavior;//privacy leak
    }
    
    @Override
    public void draw(Graphics2D g, int cellWidth, int cellHeight) {
        g.drawRect(0,0,cellWidth,cellHeight);
        drawBehavior.draw(g,cellWidth, cellHeight);
    }
    
}
