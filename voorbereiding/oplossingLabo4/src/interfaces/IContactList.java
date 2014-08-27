/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author vongenae
 */
public interface IContactList {

    void addContact(IContact contact) throws ContactException;

    IContact getContact(String name);

    String[] getContactNames();

    void removeContact(String name) throws ContactException;
    
}
