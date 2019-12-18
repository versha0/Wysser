package com.stackroute.signaturebackend.service;

import com.stackroute.signaturebackend.domain.signature;
import com.stackroute.signaturebackend.repository.signaturerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class signatureserviceImpl implements signatureservice {
    @Autowired
    signaturerepository signaturerepository;


    @Override
    public signature savenewsignature(signature signature) {
        signaturerepository.save(signature);
        return signature;
    }
}
