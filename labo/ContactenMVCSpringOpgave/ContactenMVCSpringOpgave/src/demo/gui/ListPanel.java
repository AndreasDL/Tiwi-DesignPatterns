package demo.gui;

import demo.ContactList;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * @author wijnand.schepens@hogent.be
 */
public class ListPanel extends JPanel {

    private static final String DETAILS_TEXT = "Details";
    private static final String REMOVE_TEXT = "Remove";
    private static final String NEW_TEXT = "New";
    private ContactView parent;
    private ContactList model;
    private JList list;

    public ListPanel(ContactView parent, ContactList model) {
        this.parent = parent;
        this.model = model;

        this.setLayout(new BorderLayout());

        list = new JList();
        this.add(new JScrollPane(list), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        this.add(buttonPanel, BorderLayout.SOUTH);

        JButton detailsButton = new JButton(DETAILS_TEXT);
        detailsButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleDetails();
            }
        });
        buttonPanel.add(detailsButton);

        JButton removeButton = new JButton(REMOVE_TEXT);
        removeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemove();
            }
        });
        buttonPanel.add(removeButton);

        JButton newButton = new JButton(NEW_TEXT);
        newButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                handleNew();
            }
        });
        buttonPanel.add(newButton);

        updateList();
    }

    private void updateList() {
        String[] contactNames = model.getContactNames();
        DefaultListModel listModel = new DefaultListModel();

        for (String name : contactNames) {
            listModel.addElement(name);
        }
        list.setModel(listModel);

        this.revalidate();
    }

    public void handleDetails() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            String name = (String) list.getModel().getElementAt(index);
            System.out.println("edit " + name);
            parent.showContactDetails(name);
        }
    }

    public void handleNew() {
        System.out.println("new");
        parent.showContactDetails(null);
    }

    public void handleRemove() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            String name = (String) list.getModel().getElementAt(index);
            System.out.println("remove " + name);
            parent.removeContact(name);
        }
    }

    public void modelChanged() {
        updateList();
    }
}
