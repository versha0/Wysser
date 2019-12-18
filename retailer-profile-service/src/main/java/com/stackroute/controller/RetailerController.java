package com.stackroute.controller;

import com.google.gson.Gson;
import com.stackroute.domain.RetailerProfile;
import com.stackroute.service.RetailerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//    @CrossOrigin is not Necessary if webconfig cors is configured

@RestController
@RequestMapping("/retailerProfile")
@CrossOrigin("*")
public class RetailerController {

    private RetailerServices retailerService;

    @Autowired
    public RetailerController(RetailerServices retailerServices) {
        this.retailerService = retailerServices;
    }


    @CrossOrigin
    @GetMapping("/getAllRetailersProfileList")
    public List<RetailerProfile> getUser() {
        return retailerService.getAllUser();
    }


    @CrossOrigin
    @PostMapping("/saveDetailOfRetailer")
    public void updateRetailer(@RequestParam("docPic") MultipartFile file1, @RequestParam("profilePic") MultipartFile file2, @RequestParam("retailer") String jstring) throws IOException //we will be getting retailerEmailDetail in this object with other details
    {


        Gson g = new Gson();
        RetailerProfile profileData = g.fromJson(jstring, RetailerProfile.class);

        RetailerProfile existRetailer = retailerService.getRetailerByEmail(profileData.getEmail()); //checking if user exist

        if (existRetailer != null) {
            profileData.setId(existRetailer.getId());
        }

        profileData.setDocPic(file1.getBytes());
        profileData.setDocPicType(file1.getContentType());
        profileData.setProfilePic(file2.getBytes());
        profileData.setProfilePicType(file2.getContentType());
        retailerService.updateRetailer(profileData);

    }



    @GetMapping("/getRetailerFromEmail")
    public RetailerProfile sendProfileData(@RequestParam("email") String email, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
          System.out.println("email = "+email);
        System.out.println("header in profile = "+httpServletRequest.getHeader("authorization"));

        return retailerService.getRetailerByEmail(email);

    }

    @GetMapping("/getRetailerById")
    public RetailerProfile getRetailer(@RequestParam("id") int id){

        return retailerService.getRetailerById(id);
    }


//
//    // Trash for Rest Template
//    @CrossOrigin
//    @GetMapping("/findRetailerProfilebyEmail")
//    public RetailerProfile getByEmail(@RequestBody RetailerProfile retailerProfile)
//    {
//
//        String  retailerRegistrationDetail = getDetailRegistrationAPI(retailerProfile);
//
//        retailerProfile.setMobileNo(retailerRegistrationDetail);
//
//
//        return service.getRetailerByEmail(retailerProfile.getEmailId());
//    }
//
//    @CrossOrigin
//    @GetMapping("/getMobileNoFromEmail")
//    public String getDetailRegistrationAPI(@RequestBody RetailerProfile retailerProfile)
//    {
//
//        final String uri = "http://172.23.234.99:8080/api/v1/getDetail/"+retailerProfile.getEmailId();
//
//        RestTemplate restTemplate = new RestTemplate();
//
//
////            RetailerRegistrationDetail[] result = null;
//
//
//        String result="";
//
//        try {
//            result = restTemplate.getForObject(uri, String.class);
//
//        }
//        catch ( Exception e ){
//            System.out.println("Exception : "+e);
//        }
//        return result;
//    }


    @DeleteMapping("/deleteAccount")
    @CrossOrigin
    public void deleteHandler(@RequestParam("id") int id ){
        System.out.println("delete this id acc = "+id);

        retailerService.deleteAccount(id);


    }


    @GetMapping("/getAddressbyId")
    @CrossOrigin
    public String getAddress(@RequestParam("id") int id){
        System.out.println("address of id = "+ id);
       return retailerService.getAddressById(id);
    }





}

