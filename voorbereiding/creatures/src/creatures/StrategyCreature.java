/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures;

import creatures.drawBehavior.iDrawBehavior;
import creatures.moveBehavior.iMoveBehavior;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Whatever
 */
public class StrategyCreature implements iCreature{
    iDrawBehavior drawBe;
    iMoveBehavior moveBe;
    
    public StrategyCreature(iDrawBehavior drawBe, iMoveBehavior moveBe){
        this.drawBe = drawBe;
        this.moveBe = moveBe;
    }
    
    	// Tekent zichzelf op g (van Cell) binnen rechthoek met geg. breedte en hoogte
    @Override
	public void draw(Graphics2D g, int cellWidth, int cellHeight)
	{
         drawBe.draw(g,cellWidth,cellHeight);
		
	}

	// Geeft zichzelf (eventueel) een nieuwe positie op het bord.
    @Override
	public void move(Board board)
	{
         moveBe.move(board, this);
	}
}
