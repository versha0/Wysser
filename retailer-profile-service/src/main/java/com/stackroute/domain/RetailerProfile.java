package com.stackroute.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class RetailerProfile {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String age;
    private String emergencyContact;
    private String dateOfBirth;
    private String placeOfBirth;
    private String gstIn;
    private String permanentAddress;
    private String shopAddress;
    private String mobileNo;


    private String fullName;
    private String email;
    private String phoneNo;
    private String address;
    private String docName;
    private String companyName;


    @Lob
    private byte[] profilePic;
    private String profilePicType;
    @Lob
    private byte[] docPic;
    private String docPicType;


//    var retailerData = {
//            "fullName":fullName,
//        "email":email,
//        "phoneNo":phone,
//        "address":address,
//        "gstIn":gstIn,
//        "docName":docName,
//        "profilePic":this.url,
//        "docPic":this.docurl,
//}


    public RetailerProfile() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfilePicType() {
        return profilePicType;
    }

    public void setProfilePicType(String profilePicType) {
        this.profilePicType = profilePicType;
    }

    public String getDocPicType() {
        return docPicType;
    }

    public void setDocPicType(String docPicType) {
        this.docPicType = docPicType;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public byte[] getDocPic() {
        return docPic;
    }

    public void setDocPic(byte[] docPic) {
        this.docPic = docPic;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getGstIn() {
        return gstIn;
    }

    public void setGstIn(String gstIn) {
        this.gstIn = gstIn;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "RetailerProfile{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", gstIn='" + gstIn + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", address='" + address + '\'' +
                ", docName='" + docName + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", docPic='" + docPic + '\'' +
                '}';
    }
}