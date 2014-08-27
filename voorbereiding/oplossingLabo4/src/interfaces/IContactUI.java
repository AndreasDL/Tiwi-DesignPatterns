/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author vongenae
 */
public interface IContactUI {

    void reportError(String message);

    void showContactDetails(String name);

    void showContactList();

    void showUI();
    
}
