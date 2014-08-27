package demo;

import interfaces.ContactException;
import interfaces.IContactUI;
import interfaces.IController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//
/**
 * @author wijnand.schepens@hogent.be, Veerle Ongenae
 */
public class Test {

    public static void main(String[] args) throws ContactException {
        /*TestContactDAO dao = new TestContactDAO();        
        ContactList list = dao.loadModel();        
        ContactView ui = new ContactView(list);
        list.setView(ui);*/
        
        /* instantiëren en koppelen objecten door het springframework */        
        ApplicationContext context =  new ClassPathXmlApplicationContext("/demo/springConfig.xml");
        IController controller = (IController) context.getBean("contactController");
        IContactUI ui = (IContactUI) context.getBean("contactUI");
        controller.setUi(ui);
        ui.showUI();
        
    }
}
