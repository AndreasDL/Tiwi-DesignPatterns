/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author vongenae
 */
public interface IContact {

    IAddress getAddress();

    String getEmail();

    String getName();

    void setAddress(IAddress address);

    void setEmail(String email);

    void setName(String name);
    
}
