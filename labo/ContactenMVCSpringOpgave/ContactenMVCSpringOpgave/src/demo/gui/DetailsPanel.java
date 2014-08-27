package demo.gui;

import demo.Contact;
import demo.ContactException;
import demo.ContactList;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author wijnand.schepens@hogent.be
 */
public class DetailsPanel extends JPanel {

    private static final String ADD_TEXT = "Add";
    private static final String UPDATE_TEXT = "Update";
    private static final String CANCEL_TEXT = "Cancel";
    private ContactList model;
    private ContactView parent;
    private JTextField nameField;
    private JTextField emailField;
    private JButton editButton;
    private String name;

    public DetailsPanel(ContactView parent, ContactList model) {
        this.parent = parent;
        this.model = model;

        JPanel p = new JPanel();
        this.add(p);
        p.setLayout(new GridBagLayout());

        addComponent(p, new JLabel("Name:"), 0, 0, 1);
        nameField = new JTextField();
        nameField.setColumns(20);
        addComponent(p, nameField, 1, 0, 2);

        addComponent(p, new JLabel("E-mail address:"), 0, 1, 1);
        emailField = new JTextField();
        emailField.setColumns(20);
        addComponent(p, emailField, 1, 1, 2);

        editButton = new JButton(ADD_TEXT);
        editButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleEdit();
            }
        });
        addComponent(p, editButton, 1, 2, 1);

        JButton cancelButton = new JButton(CANCEL_TEXT);
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleCancel();
            }
        });
        addComponent(p, cancelButton, 2, 2, 1);
    }

    public void selectContact(String name) throws ContactException {
        this.name = name;

        if (name == null) {
            nameField.setText("");
            emailField.setText("");
            editButton.setText(ADD_TEXT);
        } else {
            Contact contact = model.getContact(name);
            if (contact == null) {
                throw new ContactException("Contact '" + name + "' doesn't exist");
            }
            nameField.setText(contact.getName());
            emailField.setText(contact.getEmail());
            editButton.setText(UPDATE_TEXT);
        }

    }

    private void handleEdit() {
        Contact contact = new Contact();
        contact.setName(nameField.getText());
        contact.setEmail(emailField.getText());
        if (name == null) {
            parent.addContact(contact);
        } else {
            parent.updateContact(name, contact);
        }
    }

    private void handleCancel() {
        parent.start();
    }

    private static void addComponent(JPanel p, JComponent comp, int gridx, int gridy, int gridwidth) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridwidth = gridwidth;
        p.add(comp, gbc);
    }
}
