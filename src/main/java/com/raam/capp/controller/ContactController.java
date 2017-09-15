package com.raam.capp.controller;

import com.raam.capp.domain.Contact;
import com.raam.capp.services.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Raam
 */

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;
    
    @RequestMapping("/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        
        return "contact_form";
    } 
    
    @RequestMapping("/user/edit_contact")
    public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Integer contactid) {
        session.setAttribute("aContactId", contactid);
        Contact c = contactService.findById(contactid);
        m.addAttribute("command", c);        
        
        return "contact_form";
    }
    
    @RequestMapping("/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {
        Integer contactId = (Integer) session.getAttribute("aContactId");
        
        if (contactId == null) {
            // save task
            try {
                Integer userId = (Integer) session.getAttribute("userId");
                c.setUserId(userId);

                contactService.save(c);

                return "redirect:/user/clist?act=sv"; //redirect user to the contact list
            }
            catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to save contact!");
                return "contact_form";
            }
        }
        else {
            // update task
            try {
                c.setContactId(contactId);
                contactService.update(c);

                return "redirect:/user/clist?act=ed"; //redirect user to the contact list
            }
            catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to edit contact!");
                return "contact_form";
            }
        }
    }
    
    @RequestMapping("/user/clist")
    public String contactList(Model m, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));        
        return "clist";
    }
    
    @RequestMapping("/user/contact_search")
    public String contactSearch(Model m, HttpSession session, @RequestParam("freeText") String freeText) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId, freeText));       
        return "clist";
    }
    
    @RequestMapping("/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId) {
        contactService.delete(contactId);       
        return "redirect:clist?act=del";
    }
    
    @RequestMapping("/user/bulk_cdelete")
    public String bulkDelete(@RequestParam("cid") Integer[] contactIds) {
        contactService.delete(contactIds);       
        return "redirect:/user/clist?act=del";
    }
    
}
