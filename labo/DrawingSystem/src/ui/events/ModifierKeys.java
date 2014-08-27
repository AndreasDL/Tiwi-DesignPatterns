package ui.events;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author wijnand.schepens@hogent.be
 */
public class ModifierKeys
{
	private boolean shiftDown;
	private boolean altDown;
	private boolean controlDown;
	
	public ModifierKeys(MouseEvent event)
	{
		this.shiftDown = event.isShiftDown();
		this.altDown = event.isAltDown();
		this.controlDown = event.isControlDown();
	}
	
	public ModifierKeys(KeyEvent event)
	{
		int modifiers = event.getModifiersEx();
		this.shiftDown = (modifiers & KeyEvent.SHIFT_DOWN_MASK) > 0;
		this.altDown = (modifiers & KeyEvent.ALT_DOWN_MASK) > 0;
		this.controlDown = (modifiers & KeyEvent.CTRL_DOWN_MASK) > 0;
	}

	public boolean isAltDown()
	{
		return altDown;
	}

	public boolean isControlDown()
	{
		return controlDown;
	}

	public boolean isShiftDown()
	{
		return shiftDown;
	}
	
				
}
