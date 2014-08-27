package demo;

import demo.gui.ContactView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//
/**
 * @author wijnand.schepens@hogent.be
 */
public class Test {

    public static void main(String[] args) throws ContactException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("SpringConfig.xml");
        
        //TestContactDAO dao = (TestContactDAO) ctx.getBean("TestContactDAO");
                //new TestContactDAO();
        
        //ContactList list = dao.loadModel();
        
        ContactView ui = (ContactView) ctx.getBean("ContactView");
                //new ContactView(list);
        //list.setView(ui);
        
        ui.showUI();
    }
}
