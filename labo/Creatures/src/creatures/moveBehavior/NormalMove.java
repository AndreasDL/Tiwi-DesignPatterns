/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.moveBehavior;

import creatures.Board;
import creatures.ICreature;
import java.awt.Point;

/**
 *
 * @author Whatever
 */
public class NormalMove implements IMoveBehavior{

    @Override
    public void move(Board b, ICreature creature) {
        Point pos = b.getPosition(creature);
        
        if (pos != null)
        {
            //Point pos = cell.getPosition();
            int x = pos.x + 1;
            int y = pos.y;
               
            if (b.isFreePosition(x, y))
                b.putCreature(creature,new Point(x,y));
        }
    }
    
}
