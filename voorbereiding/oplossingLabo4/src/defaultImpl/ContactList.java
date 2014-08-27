package defaultImpl;

import interfaces.ContactException;
import interfaces.IContact;
import interfaces.IContactList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

/**
 * @author wijnand.schepens@hogent.be, Veerle Ongenae
 */
public class ContactList extends Observable implements IContactList {
    
    // vervangen door observers
    // private ContactView view;
    private Map<String, IContact> contacts = new HashMap<String, IContact>();

    // vervangen door observer
    /*public void setView(ContactView view) {
        this.view = view;
    }*/

    @Override
    public IContact getContact(String name) {
        return contacts.get(name);
    }

    @Override
    public void addContact(IContact contact) throws ContactException {
        if (contacts.containsKey(contact.getName())) {
            throw new ContactException("Contact with name '" + contact.getName() + "' already exists");
        }
        contacts.put(contact.getName(), contact);

        /*if (view != null) {
            view.modelChanged();
        }*/
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void removeContact(String name) throws ContactException {
        if (!contacts.containsKey(name)) {
            throw new ContactException("Contact with name '" + name + "' doesn't exist");
        }
        contacts.remove(name);

        /*if (view != null) {
            view.modelChanged();
        }*/
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String[] getContactNames() {
        return contacts.keySet().toArray(new String[contacts.size()]);
    }
}
