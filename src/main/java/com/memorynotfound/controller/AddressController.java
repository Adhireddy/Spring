package com.memorynotfound.controller;

import com.memorynotfound.model.Address;
import com.memorynotfound.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    // =========================================== Get All Users ==========================================


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Address>> getAll(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                             @RequestParam(value = "count", defaultValue = "10") int count) {
        LOG.info("getting all users with offset: {}, and count: {}", offset, count);
        List<Address> addresses = addressService.getAll(offset, count);

        if (addresses == null || addresses.isEmpty()){
            LOG.info("no Address found");
            return new ResponseEntity<List<Address>>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Address>>(addresses, HttpStatus.OK);
    }

    // =========================================== Get User By ID =========================================

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> get(@PathVariable("id") int id){
        LOG.info("getting user with id: {}", id);
        Address user = addressService.findById(id);

        if (user == null){
            LOG.info("user with id {} not found", id);
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Address>(user, HttpStatus.OK);
    }

    // =========================================== Create New User ========================================

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Address address, UriComponentsBuilder ucBuilder){
        LOG.info("creating new address: {}", address);

        if (addressService.exists(address)){
            LOG.info("a city with name " + address.getCity() + " already exists");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        addressService.create(address);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(address.getAid()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // =========================================== Update Existing User ===================================

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Address> update(@PathVariable int id, @RequestBody Address address){
        LOG.info("updating user: {}", address);
        Address currentAddress = addressService.findById(id);

        if (currentAddress == null){
            LOG.info("Address with id {} not found", id);
            return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
        }

        currentAddress.setAid(address.getAid());
        currentAddress.setStreet(address.getStreet());
        currentAddress.setCity(address.getCity());
        currentAddress.setCountry(address.getCountry());
        currentAddress.setState(address.getState());
        

        addressService.update(address);
        return new ResponseEntity<Address>(currentAddress, HttpStatus.OK);
    }

    // =========================================== Delete User ============================================

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        LOG.info("deleting user with id: {}", id);
        Address user = addressService.findById(id);

        if (user == null){
            LOG.info("Unable to delete. User with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

        addressService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
