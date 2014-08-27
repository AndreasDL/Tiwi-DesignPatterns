/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import common.Board;
import common.ICreature;
import java.awt.Graphics2D;

/**
 *
 * @author Wijnand
 */
public class StrategyCreature implements ICreature
{
	private IDrawStrategy drawStrategy;
	private IMoveStrategy moveStrategy;

	public void setDrawStrategy(IDrawStrategy drawStrategy)
	{
		this.drawStrategy = drawStrategy;
	}

	public void setMoveStrategy(IMoveStrategy moveStrategy)
	{
		this.moveStrategy = moveStrategy;
	}
	
	
	@Override
	public void draw(Graphics2D g, int maxx, int maxy)
	{
		if (drawStrategy != null)
			drawStrategy.draw(this, g, maxx, maxy);
	}

	@Override
	public void move(Board board)
	{
		if (moveStrategy != null)
			moveStrategy.move(this, board);
	}
	
}
