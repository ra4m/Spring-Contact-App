package com.raam.capp.dao;

import com.raam.capp.domain.Contact;
import com.raam.capp.rm.ContactRowMapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Raam
 */

@Repository
public class ContactDAOImpl extends BaseDAO implements ContactDAO {

    @Override
    public void save(Contact c) {
        String sql = "INSERT INTO contact (`userId`, `name`, `phone`, `email`, `address`, `remarks`)" + 
                     " VALUES(:userId, :name, :phone, :email, :address, :remarks)";
        
        Map m = new HashMap();
        m.put("userId", c.getUserId());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remarks", c.getRemarks());
              
        KeyHolder kh = new GeneratedKeyHolder();
        SqlParameterSource ps = new MapSqlParameterSource(m);
        super.getNamedParameterJdbcTemplate().update(sql, ps, kh);
        
        Integer contactId = kh.getKey().intValue();
        c.setContactId(contactId);
    }

    @Override
    public void update(Contact c) {
        String sql = "UPDATE contact SET name=:name, phone=:phone, email=:email, address=:address, remarks=:remarks WHERE contactId=:contactId";

        Map m = new HashMap();
        m.put("contactId", c.getContactId());
        m.put("name", c.getName());
        m.put("phone", c.getPhone());
        m.put("email", c.getEmail());
        m.put("address", c.getAddress());
        m.put("remarks", c.getRemarks());
        
        super.getNamedParameterJdbcTemplate().update(sql, m);
    }

    @Override
    public void delete(Contact c) {
        this.delete(c.getContactId());
    }

    @Override
    public void delete(Integer contactId) {
        String sql = "DELETE FROM contact WHERE contactID=?";
        getJdbcTemplate().update(sql, contactId);
    }

    @Override
    public Contact findById(Integer contactId) {
        String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remarks` FROM contact WHERE contactId=?";
        Contact c = super.getJdbcTemplate().queryForObject(sql, new ContactRowMapper(), contactId);
        return c;
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remarks` FROM contact";
        List<Contact> contacts = super.getJdbcTemplate().query(sql, new ContactRowMapper());
        return contacts;
    }

    @Override
    public List<Contact> findByProperty(String propName, Object propValue) {
        String sql = "SELECT `contactId`, `userId`, `name`, `phone`, `email`, `address`, `remarks` FROM contact WHERE " + propName + "=?";
        List<Contact> contacts = super.getJdbcTemplate().query(sql, new ContactRowMapper(), propValue);
        return contacts;
    }
    
    
    
}
