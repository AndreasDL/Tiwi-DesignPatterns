package defaultImpl;

import interfaces.IAddress;
import interfaces.IContact;

/**
 * @author wijnand.schepens@hogent.be
 */
public class Contact implements IContact {

    private String name;
    private String email;
    // IAddress ipv Address
    private IAddress address;

    public Contact() {
    }

    public Contact(String name, String email, IAddress address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IAddress getAddress() {
        return address;
    }

    @Override
    public void setAddress(IAddress address) {
        this.address = address;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", email=" + email + ", address=" + address + '}';
    }
}
