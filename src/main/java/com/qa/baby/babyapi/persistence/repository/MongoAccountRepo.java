package com.qa.baby.babyapi.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qa.baby.babyapi.persistence.domain.SentBaby;

@Repository
public interface MongoAccountRepo extends MongoRepository<SentBaby, Long>{
}
