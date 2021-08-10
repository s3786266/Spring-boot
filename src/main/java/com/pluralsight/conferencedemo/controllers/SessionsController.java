package com.pluralsight.conferencedemo.controllers;

// have to import annotations to let the spring MVC that these are controllers

import com.pluralsight.conferencedemo.models.Session;
import com.pluralsight.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// this controller will respond to payloads incoming and outcoming as JSON REST endpoints
@RequestMapping("/api/v1/sessions")
// tells the router what the mapping URL will look like

public class SessionsController {
        @Autowired
        private SessionRepository sessionRepository;
        // auto wire will create an instance of the session repo and put it in our class
        // when the session controller is built

        @GetMapping
        public List<Session> list(){
            return sessionRepository.findAll();
        }

        @GetMapping
        @RequestMapping("{id}")
        public Session get(@PathVariable Long id){
            return sessionRepository.getOne(id);
    }

        @PostMapping
        public Session create(@RequestBody final Session session){
            return sessionRepository.saveAndFlush(session);
        }

        @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
        public void delete(@PathVariable Long id){
            // also need to check for children records before deleting
            sessionRepository.deleteById(id);
        }

        @RequestMapping(value = "{id}", method = RequestMethod.PUT)
        public Session update(@PathVariable Long id, @RequestBody Session session){
            // because this is a PUT, we expect all attributes to be passed in
            // add validation that all attributes are passed through otherwise return 400 bad payload
            Session existingSession = sessionRepository.getOne(id);
            BeanUtils.copyProperties(session, existingSession, "session_id");
            return sessionRepository.saveAndFlush(existingSession);
        }
}
