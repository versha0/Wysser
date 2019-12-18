package com.stackroute.admin.service;

import com.stackroute.admin.domain.ContactUs;

import java.util.List;

public interface ContactUsService {

     public ContactUs saveNewContactUSRequest(ContactUs contactUs);
     public List<ContactUs> getbyReplySttaus();
}
