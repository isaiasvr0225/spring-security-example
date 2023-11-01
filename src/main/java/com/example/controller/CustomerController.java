package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private SessionRegistry sessionRegistry;
    @GetMapping("/index")
    public String index(){
        return "Hello Spring Security!";
    }

    @GetMapping("/index2")
    public String index2(){
        return "Hello Spring Security! Not Secured";
    }

    @GetMapping("/session")
    public ResponseEntity<?> getSesionDetails(){

        String sesionId = "";
        User user = null;

        List<Object> userSesionList = sessionRegistry.getAllPrincipals();

        for (Object sesion: userSesionList) {
            if (sesion instanceof User){
                user = (User) sesion;
            }

            List<SessionInformation> sessionInformationList = sessionRegistry.getAllSessions(sesion, false);

            for (SessionInformation sessionInformation : sessionInformationList) {
                sesionId = sessionInformation.getSessionId();
            }
        }

        Map<String, Object> response = new HashMap<>();

        response.put("response", "Hello Spring Security");
        response.put("sesionId", sesionId);
        response.put("userSesion", user);

        return ResponseEntity.ok(response);
    }
}
