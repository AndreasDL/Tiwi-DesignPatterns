/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.moveBehavior;

import creatures.Board;
import creatures.iCreature;

/**
 *
 * @author Whatever
 */
public interface iMoveBehavior {
    public void move(Board board, iCreature c);
}
