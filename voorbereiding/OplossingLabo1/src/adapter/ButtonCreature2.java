/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import common.Board;
import common.ICreature;
import java.awt.Graphics2D;
import javax.swing.JButton;

/**
 *
 * @author Wijnand
 */
public class ButtonCreature2 extends JButton implements ICreature
{
	public ButtonCreature2(String text)
	{
		super(text);
	}
	
	@Override
	public void draw(Graphics2D g, int maxx, int maxy)
	{
		this.setSize(maxx, maxy);
		this.setFont(this.getFont().deriveFont((float)Math.min(maxx, maxy)));
		this.paint(g);
	}

	@Override
	public void move(Board board)
	{
		//throw new UnsupportedOperationException("Not supported yet.");
	}
}
