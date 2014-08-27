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
public interface IMoveBehavior {
    public void move(Board b,ICreature creature);//bewegen en opslaan
}
