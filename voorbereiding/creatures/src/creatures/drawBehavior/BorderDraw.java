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
public class BorderDraw implements iDrawBehavior {
    private iDrawBehavior drawBe;
    
    public BorderDraw(iDrawBehavior drawBe){
        this.drawBe = drawBe;
    }
    @Override
    public void draw(Graphics2D g, int cellWidth, int cellHeight) {
        drawBe.draw(g, cellWidth, cellHeight);
        
        g.drawRect(0,0,cellWidth,cellHeight);
    }
}