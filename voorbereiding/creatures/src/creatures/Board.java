package creatures;

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
	private Map<iCreature, Cell> creatureCellMap;
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
			
	
	public iCreature getCreature(int x, int y)
	{
		return cells[x][y].getCreature();
	}

	public void putCreature(iCreature creature, Point p)
	{
		Cell srcCell = creatureCellMap.get(creature);
		if (srcCell != null)
			srcCell.setCreature(null);	// remove from cell
		
          Cell destCell = getCell(p.x,p.y);
		iCreature other = destCell.getCreature();
		if (other != null)
			creatureCellMap.put(other, null); // remove from board
		
		destCell.setCreature(creature);
		creatureCellMap.put(creature, destCell);
	}
	
	private Cell getCell(int x, int y)
	{
		if (isLegalPosition(x, y))
			return cells[x][y];
		else
			return null;
	}
	
	private Cell getCell(iCreature creature)
	{
		return creatureCellMap.get(creature);
	}
	
	public void handleTimerTick()
	{
		for (iCreature creature: creatureCellMap.keySet() )
			creature.move(this);
		repaint();
	}
     
     public Point getPosition(iCreature c){
         Cell ce = getCell(c);
         if (ce == null){
             return null;
         }else{
            return ce.position;
         }
     }

     class Cell extends JPanel
{
	private Point    position;
	private iCreature creature;  // null if none

	public Cell(int x, int y)
	{
		this.position = new Point(x, y);
		this.setBackground(Color.WHITE);
	}

	public Point getPosition()
	{
		return position;
	}
	
	public iCreature getCreature()
	{
		return creature;
	}

	public void setCreature(iCreature creature)
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
     
}