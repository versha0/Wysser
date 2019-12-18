package com.stackroute.admin.service;

import com.stackroute.admin.domain.ContactUs;
import com.stackroute.admin.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsServiceImpl implements ContactUsService{
    @Autowired
    ContactUsRepository contactUsRepository;
    @Override
    public ContactUs saveNewContactUSRequest(ContactUs contactUs) {
        contactUsRepository.save(contactUs);
        return contactUs;
    }

    @Override
    public List<ContactUs> getbyReplySttaus() {

       return  contactUsRepository.findByReplyStatus(false);
    }
}
