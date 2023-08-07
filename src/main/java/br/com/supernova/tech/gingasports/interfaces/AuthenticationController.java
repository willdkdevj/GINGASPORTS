package br.com.supernova.tech.gingasports.interfaces;

import br.com.supernova.tech.gingasports.domain.player.dto.AuthenticationDTO;
import br.com.supernova.tech.gingasports.domain.user.TokenJWT;
import br.com.supernova.tech.gingasports.domain.user.User;
import br.com.supernova.tech.gingasports.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid AuthenticationDTO authentication){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authentication.login(), authentication.pass());
        Authentication authenticate = manager.authenticate(token);

        String tokenJWT = tokenService.generationToken((User) authenticate.getPrincipal());

        return ResponseEntity.ok(new TokenJWT(tokenJWT));
    }
}
