/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import common.Board;
import common.ICreature;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Wijnand
 */
public class RandomMoveStrategy implements IMoveStrategy
{
	private static Random rnd = new Random();
	
	@Override
	public void move(ICreature creature, Board board)
	{
		Point pos = board.getPosition(creature);
		if (pos != null)
		{
			int x = pos.x + rnd.nextInt(3) - 1;
			int y = pos.y + rnd.nextInt(3) - 1;
			if (board.isFreePosition(x, y))
				board.putCreature(creature, x, y);
		}
	}
	
}
