/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.moveBehavior;

import creatures.Board;
import creatures.Creature;
import creatures.iCreature;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Whatever
 */
public class RandomMove implements iMoveBehavior {

    @Override
    public void move(Board board, iCreature c) {
        
        Point pos = board.getPosition(c);
		if (pos != null)
		{
              Random rnd = new Random();
              
            int x = pos.x + rnd.nextInt(3) - 1;
            int y = pos.y + rnd.nextInt(3) - 1;
            
            if (board.isFreePosition(x, y))
                board.putCreature(c, new Point(x, y));
		}
	}
}
