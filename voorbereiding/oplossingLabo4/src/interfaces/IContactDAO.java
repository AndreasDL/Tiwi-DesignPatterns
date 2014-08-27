/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author vongenae
 */
public interface IContactDAO {

    IContactList loadModel();

    void saveModel(IContactList list);
    
	IContact createContact();
}
