package creatures;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 * @author wijnand.schepens@hogent.be
 */
public class NormalCreature implements ICreature
{
	// Tekent zichzelf op g (van Cell) binnen rechthoek met geg. breedte en hoogte
    @Override
	public void draw(Graphics2D g, int cellWidth, int cellHeight)
	{
		g.fillOval(0, 0, cellWidth - 1, cellHeight - 1);
	}

	// Geeft zichzelf (eventueel) een nieuwe positie op het bord.
    @Override
	public void move(Board board)
	{
		Point pos = board.getPosition(this);
         //Cell cell = board.getCell(this);
		if (pos != null)
		{
			//Point pos = cell.getPosition();
			int x = pos.x + 1;
			int y = pos.y;
			
			if (board.isFreePosition(x, y))
				board.putCreature(this,new Point(x,y));//board.getCell(x, y));
		}
	}

}
