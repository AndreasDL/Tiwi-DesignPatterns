/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import common.Board;
import common.ICreature;

/**
 *
 * @author Wijnand
 */
public interface IMoveStrategy
{
	public void move(ICreature creature, Board board);
}
