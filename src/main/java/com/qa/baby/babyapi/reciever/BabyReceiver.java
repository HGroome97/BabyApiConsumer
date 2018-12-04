package com.qa.baby.babyapi.reciever;

import com.qa.baby.babyapi.persistence.domain.SentBaby;
import com.qa.baby.babyapi.persistence.repository.MongoAccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BabyReceiver {


    @Autowired
    private MongoAccountRepo repo;

    @JmsListener(destination = "BabyQueue", containerFactory = "myFactory")
    public void receiveMessage(SentBaby sentBaby) {
        repo.save(sentBaby);
    }
}
