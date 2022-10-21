package com.bsib.jwtdemo.controller;

import com.bsib.jwtdemo.model.JwtRequest;
import com.bsib.jwtdemo.model.JwtResponse;
import com.bsib.jwtdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api")
public class JwtController {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generateToken")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtRequest jwtRequest) {
        // authenticate the user
        var upat = new UsernamePasswordAuthenticationToken(
                jwtRequest.getUsername(),
                jwtRequest.getPassword());
        authenticationManager.authenticate(upat);
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String jwtToken =  jwtUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(jwtToken);
        return ResponseEntity.ok(jwtResponse);
    }
}
