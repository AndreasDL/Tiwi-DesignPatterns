/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultImpl;

import interfaces.*;

/**
 *
 * @author wijnand.schepens@hogent.be, vongenae
 */
public class Controller implements IController {
    private IContactList list;
    private IContactUI ui;
	private IContactDAO dao;

    public Controller(IContactList list, IContactDAO dao) {
        this.list = list;
		this.dao = dao;
    }
    
    @Override
    public void setUi(IContactUI ui) {        
        this.ui = ui;
    }
    
    @Override
    public void start() {
        ui.showContactList();
    }
    
    @Override
    public void addContact(IContact contact) {
        if (contact.getName().equals("") || contact.getEmail().equals("")) {
            ui.reportError("Invalid data");
        } else {
            try {
                list.addContact(contact);
				dao.saveModel(list);
                ui.showContactList();
            } catch (ContactException ex) {
                ui.reportError(ex.getMessage());
            }
        }
    }

    @Override
    public void removeContact(String name) {
        try {
            list.removeContact(name);
			dao.saveModel(list);
        } catch (ContactException ex) {
            ui.reportError(ex.getMessage());
        }
        ui.showContactList();
    }

    @Override
    public void updateContact(String name, IContact contact) {
        if (contact.getName().equals("") || contact.getEmail().equals("")) {
            ui.reportError("Invalid data");
        } else {
            IContact checkContact = list.getContact(contact.getName());

            if (!name.equals(contact.getName()) && checkContact != null) {
                ui.reportError("A contact with the name '" + checkContact.getName() + "' already exists");
            } else {
                try {
                    list.removeContact(name);
                    list.addContact(contact);
					dao.saveModel(list);
                } catch (ContactException ex) {
                    System.out.println(ex);
                }
                ui.showContactList();
            }
        }
    } 

	@Override
	public IContact createContact()
	{
		return dao.createContact();
	}
	
	
    
    @Override
    public void showContactDetails(String name) {
        ui.showContactDetails(name);
    }
}
