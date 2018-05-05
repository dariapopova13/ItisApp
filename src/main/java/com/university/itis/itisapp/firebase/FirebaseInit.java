package com.university.itis.itisapp.firebase;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class FirebaseInit {


    @PostConstruct
    public void firebase() throws IOException {
       String credentialFile = "/itisapp-1522147861835-firebase-adminsdk-boglk-f45cb10126.json";
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(
                        this.getClass().getResourceAsStream(credentialFile)))
                .build();

        try {
            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
        }
    }
}
