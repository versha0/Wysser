package com.stackroute.signaturebackend.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="signature")
public class signature {
    @Id
    private int id;
    private String signatureimage;

    public signature(int id, String signatureimage) {
        this.id = id;
        this.signatureimage = signatureimage;
    }

    public signature() {
    }

    public int getOrderid() {
        return id;
    }

    public void setOrderid(int id) {
        this.id = id;
    }

    public String getSignatureimage() {
        return signatureimage;
    }

    public void setSignatureimage(String signatureimage) {
        this.signatureimage = signatureimage;
    }
}
