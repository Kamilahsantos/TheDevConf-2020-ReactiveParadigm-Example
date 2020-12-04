package com.thedevconf.reactiveParadigm.repository;

import com.thedevconf.reactiveParadigm.model.Speaker;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakerRepository extends ReactiveMongoRepository<Speaker, String> {

}
