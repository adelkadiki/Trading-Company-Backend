package com.system.trade.controller;

import com.system.trade.jwt.AuthenticationRequest;
import com.system.trade.jwt.AuthenticationResponse;
import com.system.trade.jwt.JwtUtil;
import com.system.trade.jwt.SysUserDetailsService;
import com.system.trade.model.Client;
import com.system.trade.model.Role;
import com.system.trade.model.User;
import com.system.trade.model.Vendor;
import com.system.trade.repository.ClientRepository;
import com.system.trade.repository.ProductRepository;
import com.system.trade.repository.UserRepository;
import com.system.trade.repository.VendorRepository;
import com.system.trade.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SysUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    // User section

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody User user){

        if(userRepository.findByUsername(user.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setEnabled(true);

        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }


    //Client section

    @GetMapping("/allclients")
    public ResponseEntity<?> getAllClients(){
        return new ResponseEntity<>(userService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping("/addclient")
    public ResponseEntity<?> addClient(@RequestBody Client client){

        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);

    }

    @GetMapping("/findclientbyid/{id}")
    public ResponseEntity<Client> findClinetById(@PathVariable(value = "id") Long cid){

        Client client = userService.findById(cid);

        return new ResponseEntity<>(client, HttpStatus.OK);

    }

    @PutMapping("/updateclient")
    public ResponseEntity<?> updateClient(@RequestBody Client client){

        Client updateClient = userService.findById(client.getId());

        if(updateClient== null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(clientRepository.save(client), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteclient/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){

        clientRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addvendor")
    public ResponseEntity<?> addVendor(@RequestBody Vendor vendor){

        return new ResponseEntity<>(vendorRepository.save(vendor), HttpStatus.CREATED);
    }


    @GetMapping("/allvendors")
    public ResponseEntity<?> getAllVendors(){

        return new ResponseEntity<>(vendorRepository.findAll(), HttpStatus.OK);
    }

}
