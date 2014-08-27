package demo;

/**
 * @author wijnand.schepens@hogent.be
 */
public class TestContactDAO {

    public void saveModel(ContactList list) {
        // nog niet geimplementeerd
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public ContactList loadModel() {
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
}
