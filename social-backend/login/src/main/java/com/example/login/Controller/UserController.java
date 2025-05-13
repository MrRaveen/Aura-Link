package com.example.login.Controller;

import com.example.login.Entity.users;
import com.example.login.Requests.logIn_request;
import com.example.login.Responses.userResponse;
import com.example.login.Service.GetUserID;
import com.example.login.Service.login_service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
public class UserController {
    @Autowired
    private login_service logService;
   // private final AuthService authService;
    private ModelMapper modelMapper;
    private SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
    @Autowired
    private AuthenticationManager authenticationManager;
    private SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    @Autowired
    private GetUserID getUserID;
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody logIn_request loginRequest,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
       try{
           //get user credential for wrapped to token
           UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken
                   .unauthenticated(
                           loginRequest.getUsername(), loginRequest.getPassword_hash()
                   );
           Authentication authentication = authenticationManager.authenticate(token);
           SecurityContext context = securityContextHolderStrategy.createEmptyContext();

           context.setAuthentication(authentication); //set context application from authentication
           securityContextHolderStrategy.setContext(context);

           securityContextRepository.saveContext(context, request, response); //save the auth context
           return ResponseEntity.ok(Map.of(
                   "message", "Login successful",
                   "user", authentication.getPrincipal()
           ));
       }catch(Exception ee){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                   "message", "Invalid username or password"
           ));
       }
    }
    @GetMapping("/getUserID")
    public int getUserID(@RequestParam String username){
        int result = getUserID.getProcess(username);
        return result;
    }
    @GetMapping("/secTest")
    @PreAuthorize("isAuthenticated()")
    public String getMessage() {
        return "PROTECTED ACCESSED";
    }

}