/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package creatures.drawBehavior;

import common.Board;
import common.ICreature;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Wijnand
 */
public class ButtonCreature1 implements ICreature
{
	private JButton button;

	public ButtonCreature1(String text)
	{
		button = new JButton(text);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("klik!");
			}
		});
	}
	
	
	@Override
	public void draw(Graphics2D g, int maxx, int maxy)
	{
		button.setSize(maxx, maxy);
		button.setFont(button.getFont().deriveFont((float)Math.min(maxx, maxy)));
		button.paint(g);
	}

	@Override
	public void move(Board board)
	{
		//throw new UnsupportedOperationException("Not supported yet.");
	}
	
}
