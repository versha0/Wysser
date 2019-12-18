package com.stackroute.bookmydriver.controller;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.stackroute.bookmydriver.domain.User;
import com.stackroute.bookmydriver.service.EmailService;
import com.stackroute.bookmydriver.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@RestController
public class RegisterController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private UserService userService;
    private EmailService emailService;
    private ResponseEntity responseEntity;

    @Autowired
    public RegisterController(BCryptPasswordEncoder passwordEncoder, UserService userService, EmailService emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

//    //Return Registration form Template
//    @CrossOrigin
//    @GetMapping(value = "/register")
//    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
//        System.out.println("inside register getmap");
//        modelAndView.addObject("user",user);
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }

    //Process form Input Data POST method
//    @CrossOrigin
//    @PostMapping(value = "/register")
//    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request){
//
//        //lookup user by e-mail
//        User userExists = userService.findByEmail(user.getEmail());
//        System.out.println(userExists);
//        System.out.println("Email : = "+user.getEmail());
//
//        if(userExists!=null){
//            modelAndView.addObject("alreadyRegisteredMessage","Sorry,There is already a user registered with the email provided");
//            modelAndView.setViewName("register");
//            bindingResult.reject("email");
//        }
//        if(bindingResult.hasErrors()){
//            modelAndView.setViewName("register");
//        }else{
//         //--------------------------email-verification for new user -------------
//            //thus new user, so we need to create a new user and send confirmation email
//            //disable user until the user click on confirmation link in email
//            user.setEnabled(false);
//            //Generate random 36-character string token for confirmation link
//            user.setConfirmationToken(UUID.randomUUID().toString());
//
//            userService.saveUser(user);
//
//            String appUrl=request.getScheme() +"://"+request.getServerName()+":"+request.getServerPort();
//
//
//            SimpleMailMessage registrationEmail = new SimpleMailMessage();
//
//            registrationEmail.setTo(user.getEmail());//to which email we want to send the appUrl ::which is the mail of the user
//           //Email subject , body
//            registrationEmail.setSubject("Confirmation Email from Wysser");
//            registrationEmail.setText("To create password and confirm your e-mail address, please click the link below:\n\n" +
//                    appUrl+"/confirm?token="+user.getConfirmationToken());
//            registrationEmail.setFrom("umdk456@gmail.com");
//
//           emailService.sendEmail(registrationEmail);//sending mail to the user email
//
//
//
//            modelAndView.addObject("confirmationMessage","Confirmation mail has been sent to "+user.getEmail());
//            modelAndView.setViewName("register");
//
//        }
//        return modelAndView;
//    }

    @CrossOrigin("*")
    @PostMapping("/register")
    public ResponseEntity<?> processRegistrationForm(@RequestBody User user, HttpServletRequest request) {
        System.out.println("test");
        User userExists = userService.findByEmail(user.getEmail());
        System.out.println(userExists);
        System.out.println("Email : = " + user.getEmail());

        if (userExists != null) {
            responseEntity = new ResponseEntity<String>("{\"message\":\"User Already Exists\"}", HttpStatus.OK);
        } else {
            try {
                //--------------------------email-verification for new user -------------
                //thus new user, so we need to create a new user and send confirmation email
                //disable user until the user click on confirmation link in email
                user.setEnabled(false);
                //Generate random 36-character string token for confirmation link
                user.setConfirmationToken(UUID.randomUUID().toString());

                userService.saveUser(user);

                String appUrl = request.getScheme() + "://wysser.stackroute.io"  /*request.getServerName() + ":80"*/;


                SimpleMailMessage registrationEmail = new SimpleMailMessage();

                registrationEmail.setTo(user.getEmail());//to which email we want to send the appUrl ::which is the mail of the user
                //Email subject , body
                registrationEmail.setSubject("Confirmation Email from Wysser");
                registrationEmail.setText("Welcome to Wysser!\nThank you for signing up with us. Please click the link below to create your password and confirm your e-mail address\n\n" +
                        appUrl + "/#/confirm/" + user.getConfirmationToken());
                registrationEmail.setFrom("umdk456@gmail.com");

                emailService.sendEmail(registrationEmail);//sending mail to the user email
                responseEntity = new ResponseEntity<String>("{\"message\":\"OK\"}", HttpStatus.OK);
            } catch (Exception e) {
                responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return responseEntity;
    }

    @CrossOrigin("*")
    @PostMapping("/forgot")
    public ResponseEntity<?> processRegistrationForm(@RequestParam("email") String email, HttpServletRequest request) {
        System.out.println("test");
        User userExists = userService.findByEmail(email);
        System.out.println(userExists);
        //System.out.println("Email : = " + user.getEmail());

        if (userExists == null) {
            responseEntity = new ResponseEntity<String>("{\"message\":\"User Doesn't Exist\"}", HttpStatus.OK);
        } else {
            try {
                //--------------------------email-verification for new user -------------
                //thus new user, so we need to create a new user and send confirmation email
                //disable user until the user click on confirmation link in email
                userExists.setEnabled(false);
                //Generate random 36-character string token for confirmation link
                userExists.setConfirmationToken(UUID.randomUUID().toString());

                userService.saveUser(userExists);

                String appUrl = request.getScheme() + "://wysser.stackroute.io" /*request.getServerName() + ":80"*/;


                SimpleMailMessage registrationEmail = new SimpleMailMessage();

                registrationEmail.setTo(email);//to which email we want to send the appUrl ::which is the mail of the user
                //Email subject , body
                registrationEmail.setSubject("Password Reset Email from Wysser");
                registrationEmail.setText("To reset your password please click the link below:\n\n" +
                        appUrl + "/#/confirm/" + userExists.getConfirmationToken());
                registrationEmail.setFrom("umdk456@gmail.com");

                emailService.sendEmail(registrationEmail);//sending mail to the user email
                responseEntity = new ResponseEntity<String>("{\"message\":\"OK\"}", HttpStatus.OK);
            } catch (Exception e) {
                responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return responseEntity;
    }

    //Process to  Confirm link
//    @CrossOrigin
//    @GetMapping(value = "/confirm")
//    public ModelAndView showConfirmationPage(ModelAndView modelAndView,@RequestParam("token") String token){
//        User user=userService.findByConfirmationToken(token);
//        //checking if  token found in db
//        if(user==null){
//            modelAndView.addObject("invalidToken","Sorry , This is an invalid confirmation link.\n");
//        }else {
//            modelAndView.addObject("confirmationToken",user.getConfirmationToken());
//
//        }
//        modelAndView.setViewName("confirm");
//        return modelAndView;
//    }

    @CrossOrigin
    @GetMapping("/confirm")
    public ResponseEntity<?> confirmUser(@RequestParam("token") String token) {
        User user = userService.findByConfirmationToken(token);
        //checking if  token found in db
        if (user == null) {
            responseEntity = new ResponseEntity("{\"message\":\"Invalid Token\"}", HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity("{\"message\":\"OK\"}", HttpStatus.OK);
        }
        return responseEntity;
    }
    //Post process confirmation link
//    @CrossOrigin
//    @PostMapping(value = "/confirm")
//    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParam, RedirectAttributes redir){
//        modelAndView.setViewName("confirm");
//        //using Zxcvbn
//        Zxcvbn passwordCheck=new Zxcvbn();
//        Strength strength=passwordCheck.measure((String) requestParam.get("password"));
//
//        if(strength.getScore()<3){
//            bindingResult.reject("password");
//            redir.addFlashAttribute("errorMessage","Your Password is too weak . Choose a stronger One.");
//
//            modelAndView.setViewName("redirect:confirm?token="+requestParam.get("token"));
//            System.out.println(requestParam.get("token"));
//            return modelAndView;
//        }
//        //find the user , associated with the reset token
//        User user=userService.findByConfirmationToken((String) requestParam.get("token"));
//        //set up new password
//
//        user.setPassword(passwordEncoder.encode((CharSequence) requestParam.get("password")));
//        //set user to enable
//        user.setEnabled(true);
//        //save user
//        userService.saveUser(user);
//
//        modelAndView.addObject("successMessage","Your Password has been set !");
//        return modelAndView;
//    }

    @CrossOrigin
    @PostMapping("/confirm")
    public ResponseEntity<?> setPassword(@RequestParam Map requestParam) {
        try {
            User user = userService.findByConfirmationToken((String) requestParam.get("token"));
            //set up new password


            user.setPassword(passwordEncoder.encode((CharSequence) requestParam.get("password")));
          //  user.setPassword(requestParam.get("password").toString());
            //set user to enable
            user.setEnabled(true);
            //save user
            userService.saveUser(user);
            responseEntity = new ResponseEntity("{\"message\":\"OK\"}", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @CrossOrigin
    @PostMapping("/sendEmail")
    public ResponseEntity<?> emailHandler(@RequestParam("email") String email, @RequestParam("subject") String subject, @RequestParam("body") String body, HttpServletRequest request, HttpServletResponse response){

            try {

                SimpleMailMessage registrationEmail = new SimpleMailMessage();

                registrationEmail.setTo(email);

                registrationEmail.setSubject(subject);
                registrationEmail.setText(body);
                registrationEmail.setFrom("wysserrouting@gmail.com");

                emailService.sendEmail(registrationEmail);//sending mail to the user email
                responseEntity = new ResponseEntity<String>("{\"message\":\"OK\"}", HttpStatus.OK);
            } catch (Exception e) {
                responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return responseEntity;
        }



//    }






}
