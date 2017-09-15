package com.raam.capp.services;

import com.raam.capp.domain.Contact;
import java.util.List;

/**
 * This interface specifies all the business operations for the Contact entity
 * @author Raam
 */
public interface ContactService {
    
    public void save(Contact c);
    public void update(Contact c);
    public void delete(Integer contactId);
    public void delete(Integer[] contactIds);
    public Contact findById(Integer contactId);
    
    /**
     * This method returns all contacts of the user who is logged in
     * @param userId
     * @return 
     */
    public List<Contact> findUserContact(Integer userId);
    
    /**
     * This method returns contacts of the user who is logged in based on a search criteria
     * @param userId
     * @param txt
     * @return 
     */
    public List<Contact> findUserContact(Integer userId, String txt);
    
}
