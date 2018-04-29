package com.university.itis.itisapp.firebase;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseService {


    @Async
    public void send() {
        String topic = "t";
        Message message = Message.builder()
                .putData("scores", "805")
                .putData("fd", "fds")
                .setTopic(topic)
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().sendAsync(message).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
// Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

    }
}
