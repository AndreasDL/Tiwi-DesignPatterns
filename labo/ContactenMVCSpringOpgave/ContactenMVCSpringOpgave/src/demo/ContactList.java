package demo;

import demo.gui.ContactView;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wijnand.schepens@hogent.be
 */
public class ContactList {

    private ContactView view;
    private Map<String, Contact> contacts = new HashMap<String, Contact>();

    public void setView(ContactView view) {
        this.view = view;
    }

    public Contact getContact(String name) {
        return contacts.get(name);
    }

    public void addContact(Contact contact) throws ContactException {
        if (contacts.containsKey(contact.getName())) {
            throw new ContactException("Contact with name '" + contact.getName() + "' already exists");
        }
        contacts.put(contact.getName(), contact);

        if (view != null) {
            view.modelChanged();
        }
    }

    public void removeContact(String name) throws ContactException {
        if (!contacts.containsKey(name)) {
            throw new ContactException("Contact with name '" + name + "' doesn't exist");
        }
        contacts.remove(name);

        if (view != null) {
            view.modelChanged();
        }
    }

    public String[] getContactNames() {
        return contacts.keySet().toArray(new String[contacts.size()]);
    }
}
