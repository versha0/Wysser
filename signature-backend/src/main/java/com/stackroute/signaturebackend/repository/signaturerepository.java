package com.stackroute.signaturebackend.repository;

import com.stackroute.signaturebackend.domain.signature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface signaturerepository extends MongoRepository<signature, String> {
}
