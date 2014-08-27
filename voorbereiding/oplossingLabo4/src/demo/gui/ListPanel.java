package demo.gui;

import defaultImpl.ContactList;
import defaultImpl.Controller;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author wijnand.schepens@hogent.be, aangepast Veerle Ongenae
 */
public class ListPanel extends JPanel implements Observer {

    private static final String DETAILS_TEXT = "Details";
    private static final String REMOVE_TEXT = "Remove";
    private static final String NEW_TEXT = "New";
    //ContactView vervangen door Controller
    //private ContactView controller;
    private Controller controller;
    private ContactList model;
    private JList list;

    public ListPanel(Controller controller, ContactList model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);

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
            //System.out.println("edit " + name);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "edit {0}", name);
            controller.showContactDetails(name);
        }
    }

    public void handleNew() {
        //System.out.println("new");
        Logger.getLogger(this.getClass().getName()).info("new");
        controller.showContactDetails(null);
    }

    public void handleRemove() {
        int index = list.getSelectedIndex();
        if (index != -1) {
            String name = (String) list.getModel().getElementAt(index);
            //System.out.println("remove " + name);
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "remove {0}", name);
            controller.removeContact(name);
        }
    }

    // listPanel is observer geworden
    /*public void modelChanged() {
        updateList();
    }*/

    @Override
    public void update(Observable o, Object arg) {
        updateList();
    }
}
