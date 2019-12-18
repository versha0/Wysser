package com.stackroute.admin.repository;

import com.stackroute.admin.domain.ContactUs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface ContactUsRepository extends MongoRepository<ContactUs, String> {
   public List<ContactUs> findByReplyStatus(Boolean replyStatus);

}
