
import commands.AddDrawableCommand;
import commands.CommandHandler;
import commands.DeleteDrawableCommand;
import commands.MacroCommand;
import commands.MoveCommand;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import ui.draw.SelectionDrawPanel;
import ui.drawables.Drawable;
import ui.drawables.RectangleDrawable;
import ui.drawables.ShapeDrawable;
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
		drawPanel.addEventObserver(this);
		contentPanel.add(drawPanel);	
		
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
		
		handler = new CommandHandler();
		
		drawPanel.requestFocusInWindow();
	}
	
	public void deleteSelected()
	{
		MacroCommand cmd = new MacroCommand();
		for (Drawable dr: drawPanel.getSelectedDrawables())
			cmd.addCommand(new DeleteDrawableCommand(drawPanel, dr));
		handler.addAndExecuteCommand(cmd, drawPanel);
	}
	
	@Override
	public void click(Point p, ModifierKeys modKeys)
	{
		//System.out.println("click at " + p);
		if (modKeys.isShiftDown())
			drawPanel.addToSelection(drawPanel.getDrawableAtLocation(p));
		else
			drawPanel.select(drawPanel.getDrawableAtLocation(p)); // null deselects
	}

	@Override
	public void dragStart(Point p, ModifierKeys modKeys)
	{
		//System.out.println("drag start at " + p);
		if (moveButton.isSelected())
		{
			Drawable dr = drawPanel.getDrawableAtLocation(p);
			if (dr == null || !drawPanel.isSelected(dr))
				drawPanel.select(dr); // null deselects
			
			dragStartPositions = new HashMap<>();
			for (Drawable d: drawPanel.getSelectedDrawables())
				dragStartPositions.put(d, drawPanel.getDrawablePosition(d));
		}
		else if (drawRectButton.isSelected())
		{
			drawPanel.addDrawable( new RectangleDrawable(1, 1), p);
		}
		else if (drawEllipseButton.isSelected())
		{
			drawPanel.addDrawable( new ShapeDrawable(new Ellipse2D.Double(0, 0, 1, 1)), p);
		}
	}

	@Override
	public void drag(Point begin, Point last, Point end, ModifierKeys modKeys)
	{
		//System.out.println("drag started at " + begin + " from " + last + " to " + end);
		if (moveButton.isSelected())
		{
			for (Drawable dr: drawPanel.getSelectedDrawables())
			{
				Point pos = drawPanel.getDrawablePosition(dr);
				drawPanel.setDrawablePosition(dr, new Point(pos.x + end.x - last.x, pos.y + end.y - last.y));
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
				drawPanel.removeLastDrawable();
				drawPanel.addDrawable( new RectangleDrawable(w, h), new Point(x, y) );
			}
			else if (drawEllipseButton.isSelected())
			{
				drawPanel.removeLastDrawable();
				drawPanel.addDrawable( 
					new ShapeDrawable(new Ellipse2D.Double(0, 0, w, h)),
					new Point(x, y)
				);
			}
		}
	}

	@Override
	public void dragStop(Point begin, Point end, ModifierKeys modKeys)
	{
		//System.out.println("drag stop from " + begin + " to " + end);
		if (drawRectButton.isSelected() || drawEllipseButton.isSelected())
		{
			Drawable dr = drawPanel.getLastDrawable();
			drawPanel.removeLastDrawable();
			handler.addAndExecuteCommand(new AddDrawableCommand(dr, begin), drawPanel);
			
			drawPanel.select( drawPanel.getLastDrawable() );
		}
		else if (moveButton.isSelected())
		{
			MacroCommand cmd = new MacroCommand();
			for (Drawable dr: drawPanel.getSelectedDrawables())
			{
				Point startPos = dragStartPositions.get(dr);
				Point pos = drawPanel.getDrawablePosition(dr);
				drawPanel.setDrawablePosition(dr, startPos);
				cmd.addCommand(new MoveCommand(drawPanel, dr, pos));
			}
			handler.addAndExecuteCommand(cmd, drawPanel);
		}
	}
	
	@Override
	public void keyPress(int code, ModifierKeys modKeys)
	{
		//System.out.println("key press " + code);
		if (code == KeyEvent.VK_DELETE)
			deleteSelected();
		else if (code == KeyEvent.VK_Z && modKeys.isControlDown())
		{
			System.out.println("undo");
			handler.performUndo(drawPanel);
		}
		else if (code == KeyEvent.VK_Y && modKeys.isControlDown())
		{
			System.out.println("redo");
			handler.performRedo(drawPanel);
		}
	}
	
	
	private SelectionDrawPanel drawPanel;
	private JRadioButton moveButton;
	private JRadioButton drawRectButton;
	private JRadioButton drawEllipseButton;
	private JButton deleteButton;
	
	private CommandHandler handler;
	
	private Map<Drawable, Point> dragStartPositions;
}
