package creatures;

import creatures.drawBehavior.*;
import creatures.moveBehavior.*;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author wijnand.schepens@hogent.be
 */
public class MainFrame extends JFrame
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run()
			{
				(new MainFrame()).setVisible(true);
			}
		});
	}
	
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		
		Board board = new Board(10, 8);
		
		//board.putCreature(new NormalCreature(), new Point(2, 3));
		
		// ... voeg andere creatures toe ...
          //board.putCreature(new Bug(new RectDraw(),new RandomMove()),new Point(1,1));
          //board.putCreature(new Bug(new Decorator(new NormalDraw()),new RandomMove()),new Point(2,2));
          board.putCreature(new Bug(new BorderDrawBehavior(new NormalDraw()),new NormalMove()),new Point(1,1));
          
		setContentPane(board);
		setLocationRelativeTo(null);
	}
	
}
