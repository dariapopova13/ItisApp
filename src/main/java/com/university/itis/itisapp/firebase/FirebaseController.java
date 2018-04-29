package com.university.itis.itisapp.firebase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/firebase")
public class FirebaseController {

    @Autowired
    private FirebaseService firebaseService;

    @GetMapping(value = "/send")
    public void send(){
        firebaseService.send();
    }

}
