/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.moveBehavior;

import creatures.Board;
import creatures.ICreature;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Whatever
 */
public class RandomMove implements IMoveBehavior{

    @Override
    public void move(Board b,ICreature creature) {
        Random generator = new Random();
        Point position = b.getPosition(creature);
        
        Point newPos = position;
        newPos.x += generator.nextInt(3)-1;
        newPos.y += generator.nextInt(3)-1;
        
        while (!b.isFreePosition(newPos.x, newPos.y) && (newPos.x == position.x && newPos.y == position.y)){
            newPos = position;
            newPos.x += generator.nextInt(3)-1;
            newPos.y += generator.nextInt(3)-1;
        }
        
        if (b.isFreePosition(newPos.x, newPos.y)) //=> gechecked in while
            b.putCreature(creature,newPos);//board.getCell(x, y));
    }
}
    
