package common;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author wijnand.schepens@hogent.be
 */
public class Board extends JPanel
{
	private Cell[][] cells;
	private Map<Creature, Cell> creatureCellMap;
		// if value is null, then creature is not on board

	public Board(int dimX, int dimY)
	{
		this.cells = new Cell[dimX][dimY];
		this.creatureCellMap = new HashMap<>();
		
		this.setLayout(new GridLayout(dimY, dimX));
		
		for (int j = 0; j < dimY; j++)
		{
			for (int i = 0; i < dimX; i++)
			{
				cells[i][j] = new Cell(i, j);
				this.add(cells[i][j]);
			}
		}
		
		Timer timer = new Timer(1000, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				handleTimerTick();
			}
		});
		timer.start();
	}

	public int getDimX()
	{
		return cells[0].length;
	}

	public int getDimY()
	{
		return cells.length;
	}

	
	public boolean isLegalPosition(int x, int y)
	{
		return x >= 0 && x < getDimX() && y >= 0 && y < getDimY();
	}
	
	public boolean isFreePosition(int x, int y)
	{
		return isLegalPosition(x, y) && cells[x][y].getCreature() == null;
	}
			
	
	public Creature getCreature(int x, int y)
	{
		return cells[x][y].getCreature();
	}

	public void putCreature(Creature creature, Cell destCell)
	{
		Cell srcCell = creatureCellMap.get(creature);
		if (srcCell != null)
			srcCell.setCreature(null);	// remove from cell
		
		Creature other = destCell.getCreature();
		if (other != null)
			creatureCellMap.put(other, null); // remove from board
		
		destCell.setCreature(creature);
		creatureCellMap.put(creature, destCell);
	}
	
	public Cell getCell(int x, int y)
	{
		if (isLegalPosition(x, y))
			return cells[x][y];
		else
			return null;
	}
	
	public Cell getCell(Creature creature)
	{
		return creatureCellMap.get(creature);
	}
	
	public void handleTimerTick()
	{
		for (Creature creature: creatureCellMap.keySet() )
			creature.move(this);
		repaint();
	}

}

