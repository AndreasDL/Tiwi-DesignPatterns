
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import ui.Commands.ICommand;
import ui.draw.EventSource;
import ui.draw.SelectionDrawPanel;
import ui.drawables.Drawable;
import ui.drawables.RectangleDrawable;
import ui.events.EventObserver;
import ui.events.ModifierKeys;

/**
 * @author wijnand.schepens@hogent.be
 */
public class TestFrame extends JFrame implements EventObserver
{
	public static void main(String[] args) throws Exception
	{
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run()
			{
				(new TestFrame()).setVisible(true);
			}
		});
	}
	
	public TestFrame()
	{
		JPanel contentPanel = new JPanel(new BorderLayout());
		setContentPane(contentPanel);
		
		drawPanel = new SelectionDrawPanel(800, 800);
		contentPanel.add(drawPanel);	
          
          EventSource event = new EventSource(drawPanel);
          event.addEventObserver(this);
		
		JToolBar toolbar = new JToolBar();
		contentPanel.add(toolbar, BorderLayout.NORTH);
		
		ButtonGroup bg = new ButtonGroup();
		
		moveButton = new JRadioButton("Move");
		moveButton.setFocusable(false);
		toolbar.add(moveButton);
		bg.add(moveButton);
		
		drawRectButton = new JRadioButton("Draw rectangle");
		drawRectButton.setFocusable(false);
		toolbar.add(drawRectButton);
		bg.add(drawRectButton);
		
		drawEllipseButton = new JRadioButton("Draw ellipse");
		drawEllipseButton.setFocusable(false);
		toolbar.add(drawEllipseButton);
		bg.add(drawEllipseButton);
		
		drawRectButton.setSelected(true);
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		
		drawPanel.requestFocusInWindow();
	}
	
	public void deleteSelected()
	{
		for (Drawable dr: drawPanel.getSelectedDrawables())
			drawPanel.remove(dr);
	}
	
	@Override
	public void click(Point p, ModifierKeys modKeys)
	{
		System.out.println("click at " + p);
		if (modKeys.isShiftDown())
			drawPanel.addToSelection(drawPanel.getAtLocation(p));
		else
			drawPanel.select(drawPanel.getAtLocation(p)); // null deselects
	}

	@Override
	public void dragStart(Point p, ModifierKeys modKeys)
	{
		System.out.println("drag start at " + p);
		if (moveButton.isSelected())
		{
			Drawable dr = drawPanel.getAtLocation(p);
			if (dr == null || !drawPanel.isSelected(dr))
				drawPanel.select(dr); // null deselects
		}
		else if (drawRectButton.isSelected())
		{
			drawPanel.add( new RectangleDrawable(1, 1), p);
		}
		else if (drawEllipseButton.isSelected())
		{
			// ...
		}
	}

	@Override
	public void drag(Point begin, Point last, Point end, ModifierKeys modKeys)
	{
		System.out.println("drag started at " + begin + " from " + last + " to " + end);
		if (moveButton.isSelected())
		{
			for (Drawable dr: drawPanel.getSelectedDrawables())
			{
				Point pos = drawPanel.getPosition(dr);
				drawPanel.setPosition(dr, new Point(pos.x + end.x - last.x, pos.y + end.y - last.y));
					// voorlopig
			}
		}
		else 
		{
			int x = Math.min(begin.x, end.x);
			int y = Math.min(begin.y, end.y);
			int w = Math.abs(end.x - begin.x);
			int h = Math.abs(end.y - begin.y);
			if (drawRectButton.isSelected())
			{
				drawPanel.removeLast(); // verwijder voorlopig
				drawPanel.add( new RectangleDrawable(w, h), new Point(x, y) );
					// nieuw voorlopig
			}
			else if (drawEllipseButton.isSelected())
			{
				// ...
			}
		}
	}

	@Override
	public void dragStop(Point begin, Point end, ModifierKeys modKeys)
	{
		System.out.println("drag stop from " + begin + " to " + end);
		if (drawRectButton.isSelected() || drawEllipseButton.isSelected())
		{
			// ... eventueel voorlopig verwijderen en definitief toevoegen
			
			drawPanel.select( drawPanel.getLast() );
		}
	}
	
	@Override
	public void keyPress(int code, ModifierKeys modKeys)
	{
		System.out.println("key press " + code);
		if (code == KeyEvent.VK_DELETE)
			deleteSelected();
	}
	
	private SelectionDrawPanel drawPanel;
	private JRadioButton moveButton;
	private JRadioButton drawRectButton;
	private JRadioButton drawEllipseButton;
     private ArrayList<ICommand> ActionList;
     
}
