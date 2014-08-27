/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Wijnand
 */
public class StdCreature implements ICreature
{
	@Override
	public void draw(Graphics2D g, int maxx, int maxy)
	{
		g.fillOval(0, 0, maxx - 1, maxy - 1);
	}

	@Override
	public void move(Board board)
	{
		Point pos = board.getPosition(this);
		if (pos != null)
		{
			int x = pos.x + 1;
			int y = pos.y;
			if (board.isFreePosition(x, y))
				board.putCreature(this, x, y);
		}
	}

}
