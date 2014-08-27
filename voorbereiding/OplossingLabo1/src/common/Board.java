/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Wijnand
 */
public class Board extends JPanel
{
	private int dimX;
	private int dimY;
	private Cell[][] cells;
	private Map<ICreature, Point> creaturePositions;

	public Board(int dimX, int dimY)
	{
		this.dimX = dimX;
		this.dimY = dimY;
		this.cells = new Cell[dimX][dimY];
		this.creaturePositions = new HashMap<>();
		this.setLayout(new GridLayout(dimY, dimX));
		
		for (int j = 0; j < dimY; j++)
			for (int i = 0; i < dimX; i++)
			{
				cells[i][j] = new Cell();
				this.add(cells[i][j]);
			}
		
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e)
			{
				handleTimerTick();
			}
		});
		//timer.setRepeats(false);
		timer.start();
	}

	public int getDimX()
	{
		return dimX;
	}

	public int getDimY()
	{
		return dimY;
	}

	
	public boolean isLegalPosition(int x, int y)
	{
		return x >= 0 && x < dimX && y >= 0 && y < dimY;
	}
	
	public boolean isFreePosition(int x, int y)
	{
		return isLegalPosition(x, y) && cells[x][y].getCreature() == null;
	}
			
	
	public ICreature getCreature(int x, int y)
	{
		return cells[x][y].getCreature();
	}

	public void putCreature(ICreature creature, int x, int y)
	{
		// TODO: check x, y
		
		Point pos = creaturePositions.get(creature);
		if (pos != null)
			cells[pos.x][pos.y].setCreature(null);	// remove on old pos
		
		ICreature other = cells[x][y].getCreature();
		if (other != null)
			//creature.onMeet(other);
			creaturePositions.put(other, null); // remove(other); gives concurrentmodificationexception
		
		cells[x][y].setCreature(creature);
		creaturePositions.put(creature, new Point(x, y));
	}
	
	
	public Point getPosition(ICreature creature)
	{
		return creaturePositions.get(creature);
	}
	
	public void handleTimerTick()
	{
		for (ICreature creature: creaturePositions.keySet() )
			creature.move(this);
		repaint();
	}
	
	
	
	
	
	
	class Cell extends JPanel
	{
		private ICreature creature;

		public Cell()
		{
			this.setBackground(Color.WHITE);
		}


		public ICreature getCreature()
		{
			return creature;
		}

		public void setCreature(ICreature creature)
		{
			this.creature = creature;
		}

		@Override
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			if (creature != null)
				creature.draw(g2, getWidth(), getHeight());
		}
	}
}

