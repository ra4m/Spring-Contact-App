package com.raam.capp.services;

import com.raam.capp.dao.BaseDAO;
import com.raam.capp.dao.ContactDAO;
import com.raam.capp.domain.Contact;
import com.raam.capp.rm.ContactRowMapper;
import com.raam.capp.util.StringUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Raam
 */

@Service
public class ContactServiceImpl extends BaseDAO implements ContactService {
    
    @Autowired
    private ContactDAO contactDAO;

    @Override
    public void save(Contact c) {
        contactDAO.save(c);
    }

    @Override
    public void update(Contact c) {
        contactDAO.update(c);
    }

    @Override
    public void delete(Integer contactId) {
        contactDAO.delete(contactId);
    }

    @Override
    public void delete(Integer[] contactIds) {
        String ids = StringUtil.toCommaSeparatedString(contactIds);
        String sql = "DELETE FROM contact WHERE contactId IN(" + ids + ")";
        getJdbcTemplate().update(sql);
    }

    @Override
    public List<Contact> findUserContact(Integer userId) {
        return contactDAO.findByProperty("userId", userId);
    }

    @Override
    public List<Contact> findUserContact(Integer userId, String txt) {
        String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remarks` FROM contact WHERE userId=? AND (name LIKE '%" + txt + "%' OR email LIKE '%" + txt + "%' OR phone LIKE '%" + txt + "%' OR address LIKE '%" + txt + "%' OR remarks LIKE '%" + txt + "%')";
        List<Contact> contacts = super.getJdbcTemplate().query(sql, new ContactRowMapper(), userId);
        
        return contacts;
    }

    @Override
    public Contact findById(Integer contactId) {
        return contactDAO.findById(contactId);
    }
    
}
