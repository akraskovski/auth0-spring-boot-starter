package com.kraskovski.auth0.controller;

import com.auth0.AuthenticationController;
import com.auth0.IdentityVerificationException;
import com.auth0.Tokens;
import com.auth0.jwt.JWT;
import com.kraskovski.auth0.security.model.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    private static final String REDIRECT_ON_FAIL = "/login";
    private static final String REDIRECT_ON_SUCCESS = "/home";
    private final AuthenticationController auth0Controller;

    @Value("${auth0.domain}")
    private String domain;

    @Autowired
    public LoginController(final AuthenticationController auth0Controller) {
        this.auth0Controller = auth0Controller;
    }

    @GetMapping("/login")
    protected void login(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        final String redirectUri = String.format("%s://%s:%d/callback", req.getScheme(), req.getServerName(), req.getServerPort());
        final String authorizeUrl = auth0Controller.buildAuthorizeUrl(req, redirectUri)
                .withAudience(String.format("https://%s/userinfo", domain))
                .build();
        res.sendRedirect(authorizeUrl);
    }

    @GetMapping("/callback")
    protected void getCallback(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        handle(req, res);
    }

    @PostMapping(value = "/callback", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        handle(req, res);
    }

    private void handle(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
        try {
            final Tokens tokens = auth0Controller.handle(req);
            final TokenAuthentication tokenAuth = new TokenAuthentication(JWT.decode(tokens.getIdToken()));
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            res.sendRedirect(REDIRECT_ON_SUCCESS);
        } catch (AuthenticationException | IdentityVerificationException e) {
            SecurityContextHolder.clearContext();
            res.sendRedirect(REDIRECT_ON_FAIL);
        }
    }

}
