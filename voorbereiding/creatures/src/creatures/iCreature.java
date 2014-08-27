/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures;

import java.awt.Graphics2D;


/**
 *
 * @author Whatever
 */
public interface iCreature {
    	// Tekent zichzelf op g (van Cell) binnen rechthoek met geg. breedte en hoogte
	public void draw(Graphics2D g, int cellWidth, int cellHeight);

	// Geeft zichzelf (eventueel) een nieuwe positie op het bord.
	public void move(Board board);

}
