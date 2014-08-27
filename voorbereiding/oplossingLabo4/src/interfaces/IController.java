/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author vongenae
 */
public interface IController {

    void addContact(IContact contact);

    void removeContact(String name);

    void setUi(IContactUI ui);

    void showContactDetails(String name);

    void start();

    void updateContact(String name, IContact contact);
    
	IContact createContact();
}
