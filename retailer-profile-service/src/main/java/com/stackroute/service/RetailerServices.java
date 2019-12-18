package com.stackroute.service;

import com.stackroute.domain.RetailerProfile;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface RetailerServices {


    public void updateRetailer(RetailerProfile retailerProfile);

    public List<RetailerProfile> getAllUser();

    public RetailerProfile getRetailerByEmail(String email);

    public void deleteAccount(int id);

    public RetailerProfile getRetailerById(int id);

    public String getAddressById(int id);

}
