package demo.gui;

import demo.Contact;
import demo.ContactException;
import demo.ContactList;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author wijnand.schepens@hogent.be, aangepast Veerle Ongenae
 */
public class ContactView extends JFrame {

    private ContactList list;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ListPanel listPanel;
    private DetailsPanel detailsPanel;

    public ContactView(ContactList list) {
        super("Contacten");
        this.list = list;

        listPanel = new ListPanel(this, list);
        detailsPanel = new DetailsPanel(this, list);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.add("list", listPanel);
        cardPanel.add("details", detailsPanel);
        this.setContentPane(cardPanel);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void showUI() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                setVisible(true);
            }
        });

    }

    public void showContactList() {
        cardLayout.show(cardPanel, "list");
    }

    public void showContactDetails(String name) {
        try {
            detailsPanel.selectContact(name);
            cardLayout.show(cardPanel, "details");
        } catch (ContactException ex) {
            System.out.println(ex);
        }
    }

    public void reportError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void modelChanged() {
        listPanel.modelChanged();
    }

    public void start() {
        showContactList();
    }

    public void addContact(Contact contact) {
        if (contact.getName().equals("") || contact.getEmail().equals("")) {
            reportError("Invalid data");
        } else {
            try {
                list.addContact(contact);
                showContactList();
            } catch (ContactException ex) {
                reportError(ex.getMessage());
            }
        }
    }

    public void removeContact(String name) {
        try {
            list.removeContact(name);
        } catch (ContactException ex) {
            reportError(ex.getMessage());
        }
        showContactList();
    }

    public void updateContact(String name, Contact contact) {
        if (contact.getName().equals("") || contact.getEmail().equals("")) {
            reportError("Invalid data");
        } else {
            Contact checkContact = list.getContact(contact.getName());

            if (!name.equals(contact.getName()) && checkContact != null) {
                reportError("A contact with the name '" + checkContact.getName() + "' already exists");
            } else {
                try {
                    list.removeContact(name);
                    list.addContact(contact);
                } catch (ContactException ex) {
                    System.out.println(ex);
                }
                showContactList();
            }
        }
    }
}
