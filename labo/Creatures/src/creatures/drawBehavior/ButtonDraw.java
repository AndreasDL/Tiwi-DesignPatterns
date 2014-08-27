/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.drawBehavior;

import java.awt.Graphics2D;
import javax.swing.JButton;

/**
 *
 * @author Whatever
 */
public class ButtonDraw extends JButton implements IDrawBehavior{
    
    @Override
    public void draw(Graphics2D g, int cellWidth, int cellHeight) {
        super.setSize(cellWidth,cellHeight);
        super.paintComponent(g);
    }
    //objecten en klassen adapter
}
