package demo;

/**
 * @author wijnand.schepens@hogent.be
 */
public class Contact {

    private String name;
    private String email;
    private Address address;

    public Contact() {
    }

    public Contact(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", email=" + email + ", address=" + address + '}';
    }
}
