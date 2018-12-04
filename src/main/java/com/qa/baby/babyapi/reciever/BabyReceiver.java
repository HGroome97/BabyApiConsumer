package com.qa.baby.babyapi.reciever;

import com.qa.baby.babyapi.persistence.domain.SentBaby;
import com.qa.baby.babyapi.persistence.repository.MongoAccountRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class BabyReceiver {


    @Autowired
    private MongoAccountRepo repo;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    @JmsListener(destination = "BabyQueue", containerFactory = "myFactory")
    public void receiveMessage(SentBaby sentBaby) {
        repo.save(sentBaby);
    }
    
    @JmsListener(destination = "BabyDeleteQueue", containerFactory = "myFactory")
    public void deleteMessage(long babyId) {
    	Query query = new Query();
    	query.addCriteria(Criteria.where("babyId").is(babyId));
    	List<SentBaby> babies = mongoTemplate.find(query, SentBaby.class);
    	repo.deleteById(babyId);
    }
}
