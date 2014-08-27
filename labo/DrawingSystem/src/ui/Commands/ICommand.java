/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.Commands;

import ui.draw.SelectionDrawPanel;

/**
 *
 * @author Whatever
 */
public interface ICommand {
    public void undo();
    public void execute();
}
