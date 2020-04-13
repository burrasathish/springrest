package com.mountblue.SpringSecurityjwt.Controller;


import com.mountblue.SpringSecurityjwt.models.*;
import com.mountblue.SpringSecurityjwt.repository.ConfirmationTokenRepository;
import com.mountblue.SpringSecurityjwt.repository.RoleRepository;
import com.mountblue.SpringSecurityjwt.repository.UserRepository;
import com.mountblue.SpringSecurityjwt.services.EmailSendService;
import com.mountblue.SpringSecurityjwt.services.MyUserDetailsService;
import com.mountblue.SpringSecurityjwt.services.PostService;
import com.mountblue.SpringSecurityjwt.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PostService postService;

    @Autowired
    private MyUserDetailsService myUser;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    private EmailSendService emailSendService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> RegisterUser(@RequestBody UserData user){
        UserData existingUser=userRepo.findByEmailIgnoreCase(user.getEmail());

        if(existingUser!=null){
            return ResponseEntity.ok("email Exist");
        }
        else{
            System.out.println(user.getUsername());
            userRepo.save(user);
            ConfirmationToken confirmationToken=new ConfirmationToken(user);
            confirmationTokenRepository.save(confirmationToken);
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("compleRegisteion");
            mailMessage.setFrom("Javaprojects20@gmail.com ");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8076/confirm-account?token="+confirmationToken.getConfirmationToken());
            emailSendService.sendEmail(mailMessage);
            return ResponseEntity.ok(user.getEmail());

        }
//        return ResponseEntity.ok("registration succes");
    }


    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token") String confirmationToken){

        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token !=null){
            System.out.println(token);
            UserData user = userRepo.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            user.setRole(roleService.get("user"));
            userRepo.save(user);
            return ResponseEntity.ok("accountVerified");
        }
        else {
            return ResponseEntity.ok("\"message\",\"The link is invalid or broken!\"");
        }
    }

    @GetMapping("/user")
    public List<Post> authorHome(Authentication auth){
        return postService.findPostByUser(auth.getName());
    }

    @GetMapping("/adminView")
    public  List<Post> adminShow(Model model, Authentication auth){
      if(auth.getName().equals("adminLogin")) {
          logger.info("adminLo");
           return postService.listAll();
        }
        else{
            return postService.findPostByUser(auth.getName());
        }
    }

    @GetMapping("/userData")
    public List<UserData> userList(Authentication auth){
        return myUser.listAll();
    }

    @PutMapping("editUser/{author}")
    public String DisplayUpdate(@RequestBody UserData User, @PathVariable("author") String author) {
        UserData userData = myUser.findUser(author);
        userData.setEmail(User.getEmail());
        userData.setPassword(User.getPassword());
        Role role = roleRepository.findById(2);
        userData.setRole(role);
        userRepo.save(userData);
        return "UpdateUser";
    }




}
