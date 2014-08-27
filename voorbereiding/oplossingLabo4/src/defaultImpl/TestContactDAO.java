package defaultImpl;

import interfaces.ContactException;
import interfaces.IContact;
import interfaces.IContactDAO;
import interfaces.IContactList;

/**
 * @author wijnand.schepens@hogent.be
 */
public class TestContactDAO implements IContactDAO {

    @Override
    public void saveModel(IContactList list) {
		System.out.println("model saved");
    }

    @Override
    public IContactList loadModel() {
        ContactList list = new ContactList();

        try {
            Address addr = new Address("Voskenslaan", 1, 9000, "Gent");
            list.addContact(new Contact("Jan", "jan@hogent.be", addr));

            list.addContact(new Contact("Koen", "koen@hogent.be", addr));
        } catch (ContactException ex) {
            System.out.println(ex);
        }

        return list;
    }

	@Override
	public IContact createContact()
	{
		return new Contact();
	}
	
	
}
