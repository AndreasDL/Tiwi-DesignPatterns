/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import common.ICreature;
import java.awt.Graphics2D;

/**
 *
 * @author Wijnand
 */
public interface IDrawStrategy
{
	public void draw(ICreature creature, Graphics2D g, int maxx, int maxy);
}
