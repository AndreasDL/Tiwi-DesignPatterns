package common;

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
		
		board.putCreature(new Creature(), board.getCell(2, 3));
		
		// ... voeg andere creatures toe ...
		
		setContentPane(board);
		setLocationRelativeTo(null);
	}
	
}
