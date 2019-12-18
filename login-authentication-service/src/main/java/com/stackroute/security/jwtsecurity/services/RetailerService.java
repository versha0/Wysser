package com.stackroute.security.jwtsecurity.services;

import com.stackroute.security.jwtsecurity.model.User;
import com.stackroute.security.jwtsecurity.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RetailerService {

    RetailerRepository retailerRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RetailerService(RetailerRepository retailerRepository) {
        this.retailerRepository = retailerRepository;
    }

    public User getRetailerFromEmail(String email) {

        return retailerRepository.findUserByEmail(email);
    }


    public User checkValidateDb(User user) {

        User user1 = retailerRepository.findUserByEmail(user.getEmail());
//
//        System.out.println("user input = " + user.toString());
//         System.out.println("user from register_db = "+user1.toString());

        if (user1 == null) {
            System.out.println("in profile DB user not exist (=null)");
            return null;
        }


        if (user.getEmail().equals(user1.getEmail()) && passwordEncoder.matches(user.getPassword(),user1.getPassword())) {
            return user1;
        } else {
            return null;
        }

    }


    public void saveDummyRetailer(User user) {
//        Retailer retailer = new Retailer();
//        retailer.setEmail("umdk456@gmail.com");
//        retailer.setPass("12345678");
//        retailer.setRole("admin");
//        retailer.setId(25);

        retailerRepository.save(user);
    }

    public void deleteById(Long id){
        retailerRepository.deleteById(id);
    }

}
