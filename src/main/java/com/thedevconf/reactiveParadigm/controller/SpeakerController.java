package com.thedevconf.reactiveParadigm.controller;

import com.thedevconf.reactiveParadigm.model.Speaker;
import com.thedevconf.reactiveParadigm.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping("/speaker")
    public Flux<Speaker> getAllSpeakers() {
        return speakerRepository.findAll();
    }

    @PostMapping("/speaker")
    public Mono<Speaker> createSpeaker(@Valid @RequestBody Speaker speaker) {
        return speakerRepository.save(speaker);
    }

    @GetMapping("/speaker/{id}")
    public Mono<ResponseEntity<Speaker>> getSpeakersById(@PathVariable(value = "id") String speakerId) {
        return speakerRepository.findById(speakerId)
                .map(savedSpeaker -> ResponseEntity.ok(savedSpeaker))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/speaker/{id}")
    public Mono<ResponseEntity<Speaker>> updateSpeaker(@PathVariable(value = "id") String speakerId,
                                                       @Valid @RequestBody Speaker speaker) {
        return speakerRepository.findById(speakerId)
                .flatMap(existingSpeaker -> {
                    existingSpeaker.setName(speaker.getName());
                    return speakerRepository.save(existingSpeaker);
                })
                .map(updateSpeaker -> new ResponseEntity<>(updateSpeaker, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/speaker/{id}")
    public Mono<ResponseEntity<Void>> deleteSpeaker(@PathVariable(value = "id") String speakerId) {

        return speakerRepository.findById(speakerId)
                .flatMap(existingSpeaker ->
                        speakerRepository.delete(existingSpeaker)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/stream/speaker", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Speaker> streamAllSpeakers() {
        return speakerRepository.findAll();
    }


}
