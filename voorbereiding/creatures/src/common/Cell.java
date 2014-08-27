package common;

import java.awt.*;
import javax.swing.JPanel;

public class Cell extends JPanel
{
	private Point    position;
	private Creature creature;  // null if none

	public Cell(int x, int y)
	{
		this.position = new Point(x, y);
		this.setBackground(Color.WHITE);
	}

	public Point getPosition()
	{
		return position;
	}
	
	public Creature getCreature()
	{
		return creature;
	}

	public void setCreature(Creature creature)
	{
		this.creature = creature;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (creature != null)
			creature.draw(g2, getWidth(), getHeight());
	}
}